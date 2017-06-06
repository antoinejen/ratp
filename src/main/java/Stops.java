import java.util.ArrayList;

/**
 * Created by antoine on 04/06/17.
 */
public class Stops {
    private String line;
    private int id;
    private String name;
    private double latitude;
    private double longitude;

    public String getLine() {return line;}

    public void setLine(String line) {this.line = line;}

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
        return "\n Line=" + getLine() + "::ID=" + getId() + "::Name" + getName() + "::Latitude=" + getLatitude() + "::Longitude=" + getLongitude();
    }

    public static void searchById(ArrayList<Stops> stopsList, int id) {
        for (Stops s : stopsList) {
            if (s.getName() != null && s.getId() == id) {
                System.out.println(s);
            }
        }
    }

    public static void searchByName(ArrayList<Stops> stopsList, String name) {
        for (Stops s : stopsList) {
            if (s.getName() != null && s.getName().contains(name)) {
                System.out.println(s);
            }
        }
    }

    public static void searchDuplicates(ArrayList<Stops> stopsList) {
        ArrayList<Stops> noDuplicates = new ArrayList<Stops>();
        for (Stops s: stopsList) {
            boolean isDuplicated = false;
            for (Stops t : noDuplicates) {
                if (t.getName().equals(s.getName())) {
                    if ((s.getLine().equals(t.getLine())))
                        isDuplicated = true;
                }
            }
            if (!isDuplicated) noDuplicates.add(s);
        }
        System.out.println(noDuplicates);
    }
}
