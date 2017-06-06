import sun.misc.Queue;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * Created by antoine on 05/06/17.
 */
public class Graph {
    private List<Integer>[] adj;

    public Graph(List<String> finalIntraEdges, List<String> transfers) throws IOException {
        finalIntraEdges.addAll(transfers);
        int maxIndex = getMaxIndex(finalIntraEdges);
        adj = new ArrayList[maxIndex + 1];
        addEdgesToGraph(adj, finalIntraEdges);
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

    public int breadFirstSearchForShortestPath(int sourceNode,
                                               int targetNode, ArrayList<Stops> stopsList) throws InterruptedException {
        Stops.searchById(stopsList, sourceNode);
        Queue<Integer> queue = new Queue();
        HashSet<Integer> visited = new HashSet<>();
        int[] distances = new int[adj.length];
        int[] previous = new int[adj.length];
        visited.add(sourceNode);
        queue.enqueue(sourceNode);
        while (!queue.isEmpty()) {
            int nodeIndex = queue.dequeue();
            List<Integer> neighbours = adj[nodeIndex];
            if (!visited.contains(nodeIndex)) {
                System.out.print("Visiting: ");
                Stops.searchById(stopsList, nodeIndex);
                visited.add(nodeIndex);
            }
            if (neighbours != null) {
                for (int neighbour : neighbours) {
                    if (!visited.contains(neighbour)) {
                        visited.add(neighbour);
                        queue.enqueue(neighbour);
                        previous[neighbour] = nodeIndex;
                        distances[neighbour] = distances[nodeIndex] + 1;
                    }
                    if (neighbour == targetNode) {
                        printPrevious(previous, targetNode, sourceNode, stopsList);
                        return distances[neighbour];
                    }
                }
            }
        }
        return -1;
    }

    private void printPrevious(int[] previous, int targetNode, int sourceNode, ArrayList<Stops> stopsList) {
        if (targetNode == sourceNode) {
            //System.out.print(targetNode);
            return;
        }
        printPrevious(previous, previous[targetNode], sourceNode, stopsList);
        System.out.println("                    || ");
        System.out.println("                    VV ");
        Stops.searchById(stopsList, targetNode);
    }
}
