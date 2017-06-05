/**
 * Created by antoine on 05/06/17.
 */
public class Trips {
    private int route_id;
    private int service_id;
    private long trip_id;

    public int getRoute_id() {
        return route_id;
    }

    public void setRoute_id(int route_id) {
        this.route_id = route_id;
    }

    public int getService_id() {return service_id;}

    public void setService_id(int service_id) {
        this.service_id = service_id;
    }

    public long getTrip_id() {
        return trip_id;
    }

    public void setTrip_id(long trip_id) {
        this.trip_id = trip_id;
    }

    @Override
    public String toString() {
        return "\nID=" + getRoute_id() + "::Service=" + getService_id() + "::Trip=" + getTrip_id();
    }
}
