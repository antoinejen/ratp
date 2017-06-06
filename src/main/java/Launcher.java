import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by antoine on 05/06/17.
 */
public class Launcher {

    public static void main(String[] args) throws IOException, ParseException, InterruptedException {
        ArrayList<Stops> stopsList = Format.stopsFormat();
        //System.out.println(stopsList);
        HashMap<Integer, ArrayList<Integer>> stationsListPerLine = Format.edgeLine();
        //System.out.println(stationsListPerLine);
        List<Integer> intraEdges = new ArrayList<Integer>();
        List<String> finalIntraEdges = new ArrayList<String>();
        for (Integer key : stationsListPerLine.keySet()) {
            List<Integer> oneDirection = Edge.oneDirection(stationsListPerLine.get(key)); // Un sens d'une ligne
            List<String> finalOneDirection = Edge.getLines(oneDirection);
            List<Integer> otherDirection = Edge.otherDirection(stationsListPerLine.get(key));// L'autre sens
            List<String> finalOtherDirection = Edge.getLines(otherDirection);
            Edge.mergetoOneLineString(finalOneDirection, finalOtherDirection); // On combine les deux pour n'avoir qu'une seule liste d'adjacence
            //intraEdges.addAll(oneDirection);
            //System.out.println(finalOneDirection);
            finalIntraEdges.addAll(finalOneDirection);
        }
        //System.out.println(finalIntraEdges);
        ArrayList<Transfers> transfersList = Format.transfersFormat();
        List<String> transfers = Edge.getTransfers(stopsList, transfersList);
        //System.out.println(transfers);
        // Partie 1.7 Weighted graph
        List<Edge> intraListEdge = new ArrayList<>(finalIntraEdges.size()); // Intra-stations
        List<Edge> interListEdge = new ArrayList<>(transfers.size()); // Correspondances

        // intraListEdge
        double[] coordinates1 = new double[2];
        double[] coordinates2 = new double[2];
        for (int i = 0; i < finalIntraEdges.size();i++) {
            String[] nodesNumber = finalIntraEdges.get(i).split(" ");
            coordinates1 = Stops.getCoordinates(stopsList, Integer.parseInt(nodesNumber[0]));
            coordinates2 = Stops.getCoordinates(stopsList, Integer.parseInt(nodesNumber[1]));
            double euclidian = GraphWeighted.euclidianDistance(coordinates1, coordinates2);
            intraListEdge.add(new Edge(Integer.parseInt(nodesNumber[0]), Integer.parseInt(nodesNumber[1]), euclidian));
        }
        //System.out.println(intraListEdge);

        // interListEdge
        for (int i = 0; i < transfers.size();i++) {
            String[] nodesNumber = transfers.get(i).split(" ");
            interListEdge.add(new Edge(Integer.parseInt(nodesNumber[0]), Integer.parseInt(nodesNumber[1]), 0.0));
        }
        //System.out.println(interListEdge);

        //FUUUUUUUUUUUUUUU-SION!
        intraListEdge.addAll(interListEdge);
        ArrayList<Edge> finalAdjWeighted = new ArrayList<>(intraListEdge);
        //System.out.println(finalAdjWeighted);

        //System.out.println(Stops.getNameByid(stopsList, 2035));


        //Graph metro = new Graph(finalIntraEdges, transfers);
        //System.out.println("Métro: ");
        //metro.print(); // Affichage du métro en graphe

        GraphWeighted metroWeighted = new GraphWeighted(finalIntraEdges, transfers, finalAdjWeighted);
        //System.out.println("Métro Weighted: ");
        //metroWeighted.print(); // Affichage du métro en graphe

        //3. Shortest paths - BFS

        //metro.breadFirstSearchForShortestPath(2185,2328, stopsList);

        //3. Shortest paths - Dijkstra

        metroWeighted.dijkstra(2487, 2208, stopsList);




    }
}
