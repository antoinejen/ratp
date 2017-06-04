import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by antoine on 05/06/17.
 */
public class Launcher {
    public static void call() throws IOException {
        try {
            Format.main(null);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    // Search


    public static void main(String[] args) throws IOException {
        ArrayList<Stops> stopsList = Format.stopsFormat();
        //System.out.println(stopsList);
        ArrayList<Routes> routesList = Format.routesFormat();
        Stops.searchById(stopsList, 2075);
    }
}
