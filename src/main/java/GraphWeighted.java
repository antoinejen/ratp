/**
 * Created by antoine on 06/06/17.
 */
import java.io.IOException;
import java.util.*;
public class GraphWeighted {
    private List<Edge>[] adj;

    public List<Edge>[] getAdj() {
        return adj;
    }

    public void setAdj(List<Edge>[] adj) {
        this.adj = adj;
    }

public GraphWeighted(List<String> finalIntraEdges, List<String> transfers, ArrayList<Edge> finalAdjWeighted) throws IOException {
        finalIntraEdges.addAll(transfers);
        int maxIndex = getMaxIndex(finalIntraEdges);
        adj = new ArrayList[maxIndex + 1];
        addEdgesToGraph(adj, finalAdjWeighted);
    }

    public static int getMaxIndex(List<String> lines) {
        int maxIndex = 0;
        for (String line : lines) {
            String[] nodesNumber = line.split(" ");
            for (String nodeNumber : nodesNumber) {
                maxIndex = Math.max(Integer.parseInt(nodeNumber), maxIndex);
            }
        }
        return maxIndex;
    }

    public static double euclidianDistance(double[] a, double[] b) {
        double diff_square_sum = 0.0;
        for (int i = 0; i < a.length; i++) {
            diff_square_sum += (a[i] - b[i]) * (a[i] - b[i]);
        }
        return Math.sqrt(diff_square_sum);
    }

    public void addEdgesToGraph(List<Edge>[] adj, ArrayList<Edge> finalAdjWeighted) {
        for (Edge edge : finalAdjWeighted) {
            addEdgeToNode(edge.getFrom(), edge.getTo(), edge.getWeight());
            addEdgeToNode(edge.getTo(), edge.getFrom(), edge.getWeight()); // Commented when it’s a directed graph as input. We could activate this line via a flag/boolean value.
        }
    }

    public void addEdgeToNode(int node1Index, int node2Index, Double weight) {
        List<Edge> edges = adj[node1Index];
        if (edges == null) {
            edges = new ArrayList<Edge>();
        }
        edges.add(new Edge(node1Index, node2Index, weight));
        adj[node1Index] = edges;
    }

    public void print() {
        for (int i = 0; i < adj.length; i++)
        {
            if(adj[i] != null)
            {
                System.out.println("Station " + i + ": " + adj[i]);

            }
        }
    }

    /**
     *
     * @param sourceNode
     */
    private void printPrevious(int[] previous, int targetNode, int sourceNode) {
        if (targetNode == sourceNode) {
            System.out.print(targetNode);
            return;
        }
        printPrevious(previous, previous[targetNode], sourceNode);
        System.out.print(" => " + targetNode);
    }

    /**
     * @param sourceNode
     * @param targetNode
     */
    public void dijkstra(int sourceNode, int targetNode, ArrayList<Stops> stopsList) {
        Double[] distances = new Double[adj.length];
        int[] previous = new int[adj.length];
        Set<Integer> toVisit = new HashSet<>();
        for (int i = 0; i < distances.length; i++) {
            distances[i] = Double.MAX_VALUE;
            toVisit.add(i);
        }
        distances[sourceNode] = 0.0;
        while (!toVisit.isEmpty()) {
            System.out.println(toVisit);
            int nodeIndex = getMinNode(distances, toVisit);
            toVisit.remove(nodeIndex);
            //System.out.println("Visiting: ");
            //Stops.searchById(stopsList, nodeIndex);
            if (adj[nodeIndex] != null) {
                for (Edge edge : adj[nodeIndex]) {
                    Double tempDistance = distances[nodeIndex] + edge.weight;
                    if (tempDistance < distances[edge.to]) {
                        distances[edge.to] = tempDistance;
                        previous[edge.to] = edge.from;
                    }
                }
            }
        }
        for (int i = 0; i < distances.length; i++) {
            System.out.println("Shortest path to " + i + " is : " + (distances[i] == Integer.MAX_VALUE ?
                    "IMPOSSIBLE" : distances[i]));
        }
    }

    public int getMinNode(Double[] distances, Set<Integer> frontier) {
        int minIndex = 0;
        Double minValue = Double.MAX_VALUE;
        for (int node: frontier) {
            if (distances[node] < minValue) {
                minIndex = node;
                minValue = distances[node];
            }
        }
        return minIndex;
    }
}