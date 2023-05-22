import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ShortestPathDAG {
    public static void main(String[] args) throws IOException {
        File input = new File("date.in");
        Scanner scanner = new Scanner(input);

        int vertices = scanner.nextInt();
        int edges = scanner.nextInt();
        int source = scanner.nextInt() - 1;

        WeightedGraph g = new WeightedGraph(vertices);
        for (int i = 0; i < edges; i++) {
            int u = scanner.nextInt() - 1;
            int v = scanner.nextInt() - 1;
            int weight = scanner.nextInt();

            g.addEdge(u, v, weight);
        }

        List<Integer> distances = new ArrayList<>();
        for (int i = 0; i < vertices; i++) {
            distances.add(Integer.MAX_VALUE);
        }
        distances.set(source, 0);

        for (Integer u : g.topologicalSort()) {
            if (distances.get(u) != Integer.MAX_VALUE) {
                List<Edge> adjacent = g.getEdges().get(u);

                for (Edge edge : adjacent) {
                    if (distances.get(edge.getV()) > distances.get(u) + edge.getWeight()) {
                        distances.set(edge.getV(), distances.get(u) + edge.getWeight());
                    }
                }
            }
        }

        FileWriter writer = new FileWriter("date.out");
        for (Integer distance : distances) {
            if (distance == Integer.MAX_VALUE) {
                distance = -1;
            }
            writer.write(distance + " ");
        }

        scanner.close();
        writer.close();
    }
}
