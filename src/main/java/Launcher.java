import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by antoine on 05/06/17.
 */
public class Launcher {

    public static void main(String[] args) throws IOException, ParseException {
        ArrayList<Stops> stopsList = Format.stopsFormat();
        //System.out.println(stopsList);
        HashMap<Integer, ArrayList<Integer>> stationsListPerLine = Format.edgeLine();
        //System.out.println(stationsListPerLine);
        List<Integer> intraEdges = new ArrayList<Integer>();
        for (Integer key : stationsListPerLine.keySet()) {
            List<Integer> oneDirection = Edge.oneDirection(stationsListPerLine.get(key)); // Un sens d'une ligne

            List<Integer> otherDirection = Edge.otherDirection(stationsListPerLine.get(key));// L'autre sens

            Edge.mergetoOneLine(oneDirection, otherDirection); // On combine les deux pour n'avoir qu'une seule liste d'adjacence
            intraEdges.addAll(oneDirection);

        }
        ArrayList<Transfers> transfersList = Format.transfersFormat();
        List<String> transfers = Edge.getTransfers(stopsList, transfersList);

        Graph metro = new Graph(intraEdges, transfers);
        System.out.println("Métro: ");
        metro.print(); // Affichage du métro en graphe
        Stops.searchById(stopsList, 3343777);
        Stops.searchById(stopsList, 3343775);
        Stops.searchById(stopsList, 3343779);

    }
}
