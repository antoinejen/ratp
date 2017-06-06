import java.util.*;

/**
 * Created by antoine on 05/06/17.
 */
public class Edge {
    private int from;
    private int to;
    private Double weight;

    public Edge(int from, int to, Double weight) {
        this.from = from;
        this.to = to;
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "Edge{" +
                "from=" + from +
                ", to=" + to +
                ", weight=" + weight +
                '}';
    }

    public static List<Integer> oneDirection(ArrayList<Integer> stopTimesList) {
        int half = (int) Math.ceil(stopTimesList.size() / 2);
        List<Integer> oneDirection = stopTimesList.subList(0, half);
        return oneDirection;
    }

    public static List<Integer> otherDirection(ArrayList<Integer> stopTimesList) {
        int half = (int) Math.ceil(stopTimesList.size() / 2);
        List<Integer> otherDirection = stopTimesList.subList(half, stopTimesList.size());
        return otherDirection;
    }

    public static void mergetoOneLine (List<Integer> oneDirection, List<Integer> otherDirection) {
        oneDirection.addAll(otherDirection);
    }

    public static List<String> getLines(List<Integer> direction) {
        List<String> lines = new ArrayList<>();
        for (int i = 0; i < direction.size(); i++) {
            if (i == direction.size() - 1)
                break;
            else
                lines.add(direction.get(i) + " " + direction.get(i+1));
        }
        return lines;
    }

    public static List<String> getTransfers(ArrayList<Stops> stopsList, ArrayList<Transfers> transfers) {
        List<String> transferList = new ArrayList<>();
        for (int i = 0; i < transfers.size(); i++) {
            if (i == transfers.size() -1)
                break;
            else
                if (Stops.isMetroStation(stopsList, transfers.get(i).getFrom()) && Stops.isMetroStation(stopsList, transfers.get(i).getTo()))
                transferList.add(String.valueOf(transfers.get(i).getFrom()) + " " + transfers.get(i).getTo());
        }
        return transferList;
    }

}
