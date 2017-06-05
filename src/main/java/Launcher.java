import java.io.IOException;
import java.text.ParseException;
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


    public static void main(String[] args) throws IOException, ParseException {
        //ArrayList<Stops> stopsList = Format.stopsFormat();
        //System.out.println(stopsList);
        //ArrayList<Routes> routesList = Format.routesFormat();
        //ArrayList<stopTimes> stopTimesList = Format.stopTimesFormat();
        //System.out.println(stopTimesList);
        ArrayList<Trips> tripsList = Format.tripsFormat();
        System.out.println(tripsList);
        //Stops.searchById(stopsList, 2075);

    }
}
