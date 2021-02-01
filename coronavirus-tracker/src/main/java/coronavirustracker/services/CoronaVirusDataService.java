package jatin.mohanty.coronavirustracker.services;

import jatin.mohanty.coronavirustracker.services.models.LocationStats;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.StringReader;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpResponse;
import java.net.http.HttpRequest;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;


@Service
public class CoronaVirusDataService {


    //link to raw csv data
    private static String virus_data_url = "https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/csse_covid_19_time_series/time_series_covid19_confirmed_US.csv";

    //list of all counties are their information
    private List<LocationStats> all_stats = new ArrayList<>();

    //gets private list
    public List<LocationStats> getAll_stats() {
        return all_stats;
    }


    // constructs when class is created
    @PostConstruct

    //updates once every day
    @Scheduled(cron = "* * 1 * * *")
    public void get_virus_data() throws IOException, InterruptedException {
        //we have the earlier definition because we don't want ppl to get error while loading new list
        List<LocationStats> new_stats = new ArrayList<>();

        //sends our http request to get data
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(virus_data_url))
                .build();
        HttpResponse<String> httpResponse = client.send(request, HttpResponse.BodyHandlers.ofString());

        //put http response in string reader type so we can use with csv formatting
        StringReader csv_body_reader = new StringReader(httpResponse.body());

        //make all csv columns iterable
        Iterable<CSVRecord> records = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(csv_body_reader);

        //Get data from the formatted CSV and construct LocationStats class
        for (CSVRecord record : records) {
            String state = record.get("Province_State");
            String country = record.get("Country_Region");
            String county = record.get("Admin2");
            int cases = Integer.parseInt(record.get(record.size()-1));
            int todays_cases = cases - Integer.parseInt(record.get(record.size()-2));
            LocationStats location_stat = new LocationStats(county,state,country,cases, todays_cases);
            //add constructed instance to list
            new_stats.add(location_stat);

        }
        //update all_stats variable after iterating through all records
        this.all_stats=new_stats;
    }


}
