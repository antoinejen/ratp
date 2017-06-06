import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by antoine on 05/06/17.
 */
public class Graph {
    private List<Integer>[] adj;

    public Graph(List<Integer> direction, List<String> transfers) throws IOException {
        List<String> thisisntevenmyfinalform = Edge.getLines(direction);
        thisisntevenmyfinalform.addAll(transfers);
        int maxIndex = getMaxIndex(thisisntevenmyfinalform);
        adj = new ArrayList[maxIndex + 1];
        addEdgesToGraph(adj, thisisntevenmyfinalform);
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

    private void addEdgesToGraph(List<Integer>[] adj, List<String> lines) {
        for (String line : lines) {
            String[] nodesNumber = line.split(" ");
            int node1Index = Integer.parseInt(nodesNumber[0]);
            int node2Index = Integer.parseInt(nodesNumber[1]);
            addEdgeToNode(node1Index, node2Index);
            addEdgeToNode(node2Index, node1Index); // Commented when it’s a directed graph as input. We could activate this line via a flag/boolean value.
        }
    }

    public void addEdgeToNode(int node1Index, int node2Index) {
        List<Integer> edges = adj[node1Index];
        if (edges == null) {
            edges = new ArrayList<Integer>();
        }
        edges.add(node2Index);
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
}
