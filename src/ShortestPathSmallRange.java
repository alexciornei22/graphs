import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class ShortestPathSmallRange {
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

        List<List<Integer>> buckets = new ArrayList<>();
        int nrBuckets = 30 * (g.getVertices() - 1);
        for (int i = 0; i <= nrBuckets; i++) {
            buckets.add(new ArrayList<>());
        }

        buckets.get(0).add(source);
        distances.set(source, 0);

        for (int i = 0; i <= nrBuckets; i++) {
            List<Integer> currentBucket = buckets.get(i);
            while (!currentBucket.isEmpty()) {
                int u = currentBucket.get(0);
                currentBucket.remove(0);

                for (Edge edge : g.getEdges().get(u)) {
                    if (distances.get(edge.getV()) > distances.get(u) + edge.getWeight()) {
                        distances.set(edge.getV(), distances.get(u) + edge.getWeight());
                        buckets.get(distances.get(u) + edge.getWeight()).add(edge.getV());
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
        writer.close();
    }
}
