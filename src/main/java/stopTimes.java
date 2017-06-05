import java.text.SimpleDateFormat;

/**
 * Created by antoine on 05/06/17.
 */
public class stopTimes {
    SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
    private long trip_id;
    private String arrival_date;
    private String departure_date;
    private int stop_id;

    public long getTrip_id() {
        return trip_id;
    }

    public void setTrip_id(long trip_id) {
        this.trip_id = trip_id;
    }

    public String getArrival_date() {
        return arrival_date;
    }

    public void setArrival_date(String arrival_date) {
        this.arrival_date = arrival_date;
    }

    public String getDeparture_date() {
        return departure_date;
    }

    public void setDeparture_date(String departure_date) {
        this.departure_date = departure_date;
    }

    public int getStop_id() {
        return stop_id;
    }

    public void setStop_id(int stop_id) {
        this.stop_id = stop_id;
    }

    @Override
    public String toString() {
        return "\nID=" + getTrip_id() + "::Arrival Date=" + getArrival_date() + "::Departure Date=" + getDeparture_date() + "::Stop ID=" + getStop_id();
    }

}
