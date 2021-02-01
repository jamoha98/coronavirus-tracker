package jatin.mohanty.coronavirustracker.services.models;

public class LocationStats {
    private  String county;
    private String state;
    private String country;
    private int latest_cases;
    private int todays_cases;

    public LocationStats(String county, String state, String country, int latest_cases, int todays_cases) {
        this.county = county;
        this.state = state;
        this.country = country;
        this.latest_cases = latest_cases;
        this.todays_cases = todays_cases;
    }

    //Getters and setters
    public String getCounty() {
        return county;
    }
    public void setCounty(String county) {
        this.county = county;
    }
    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getLatest_cases() {
        return latest_cases;
    }

    public void setLatest_cases(int latest_cases) {
        this.latest_cases = latest_cases;
    }

    public int getTodays_cases() {
        return todays_cases;
    }
    public void setTodays_cases(int todays_cases) {
        this.todays_cases = todays_cases;
    }

    //helpful print function
    @Override
    public String toString() {
        return "LocationStats{" +
                "county='" + county + '\'' +
                ", state='" + state + '\'' +
                ", country='" + country + '\'' +
                ", latest_cases=" + latest_cases +
                ", todays_cases=" + todays_cases +
                '}';
    }
}
