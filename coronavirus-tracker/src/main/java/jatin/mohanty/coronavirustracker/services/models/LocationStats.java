package jatin.mohanty.coronavirustracker.services.models;

public class LocationStats {
    private String state;
    private String country;
    private int latest_cases;

    public LocationStats(String state, String country, int latest_cases) {
        this.state = state;
        this.country = country;
        this.latest_cases = latest_cases;
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

    @Override
    public String toString() {
        return "LocationStats{" +
                "state='" + state + '\'' +
                ", country='" + country + '\'' +
                ", latest_cases=" + latest_cases +
                '}';
    }

    public int getLatest_cases() {
        return latest_cases;
    }

    public void setLatest_cases(int latest_cases) {
        this.latest_cases = latest_cases;
    }
}
