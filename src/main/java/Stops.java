import java.util.ArrayList;

/**
 * Created by antoine on 04/06/17.
 */
public class Stops {
    private int id;
    private String name;
    private double latitude;
    private double longitude;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    @Override
    public String toString() {
        return "\nID=" + getId() + "::Name" + getName() + "::Latitude=" + getLatitude() + "::Longitude=" + getLongitude();
    }

    public static void searchById(ArrayList<Stops> stopsList, int id) {
        for (Stops s : stopsList) {
            if (s.getName() != null && s.getId() == id) {
                System.out.println(s);
            }
        }
    }
}
