import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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
        HashMap<Integer, ArrayList<Integer>> stationsListPerLine = Format.edgeLine();
        //System.out.println(stationsListPerLine);
        List<String> intraEdges = new ArrayList<String>();
        for (Integer key : stationsListPerLine.keySet()) {
            List<Integer> oneDirection = Edge.oneDirection(stationsListPerLine.get(key)); // Un sens d'une ligne
            System.out.println(oneDirection);
            //List<String> oneLine = Edge.getLines(oneDirection); // Création du texte pour être lu
            //System.out.println(oneLine);

            List<Integer> otherDirection = Edge.otherDirection(stationsListPerLine.get(key));// L'autre sens
            System.out.println(otherDirection);
            //List<String> otherLine = Edge.getLines(otherDirection); // Création du texte pour être lu
            //System.out.println(otherLine);

            //Edge.mergetoOneLine(oneLine, otherLine); // On combine les deux pour n'avoir qu'une seule liste d'adjacence
            //intraEdges.addAll(oneLine);

        }
        System.out.println(intraEdges);

        /*Graph line1 = new Graph(intraEdges);
        System.out.println("Ligne 1: ");
        line1.print(); // Affichage de la ligne 1 en graphe */
        //System.out.println(otherDirection);
        //ArrayList<Trips> tripsList = Format.tripsFormat();
        //System.out.println(tripsList);
        //ArrayList<Transfers> transfersList = Format.transfersFormat();
        //System.out.println(transfersList);
        //Stops.searchDuplicates(stopsList);

    }
}
