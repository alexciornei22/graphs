import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class WeightedGraph {
    private int vertices = 0;
    private Map<Integer, List<Edge>> edges = new HashMap<>();

    public WeightedGraph(int vertices) {
        this.vertices = vertices;
        for (int i = 0; i < vertices; i++) {
            edges.put(i, new LinkedList<>());
        }
    }

    public int getVertices() {
        return vertices;
    }

    public Map<Integer, List<Edge>> getEdges() {
        return edges;
    }

    public void addEdge(int u, int v, int weight) {
        edges.get(u).add(new Edge(v, weight));
    }

    public void topologicalSortUtil(int u, Stack<Integer> stack, List<Cycle.Color> colors) {
        colors.set(u, Cycle.Color.GRAY);

        for (Edge edge : edges.get(u)) {
            if (colors.get(edge.getV()) == Cycle.Color.WHITE) {
                topologicalSortUtil(edge.getV(), stack, colors);
            }
        }

        colors.set(u, Cycle.Color.BLACK);
        stack.push(u);
    }

    public List<Integer> topologicalSort() {
        List<Integer> sorted = new ArrayList<>();
        Stack<Integer> stack = new Stack<>();
        List<Cycle.Color> colors = new ArrayList<>();

        for (int i = 0; i < vertices; i++) {
            colors.add(Cycle.Color.WHITE);
        }

        for (int i = 0; i < vertices; i++) {
            if (colors.get(i) == Cycle.Color.WHITE) {
                topologicalSortUtil(i, stack, colors);
            }
        }

        while (!stack.empty()) {
            sorted.add(stack.pop());
        }
        return sorted;
    }
}

class Edge {
    private int v;
    private int weight;

    public Edge(int v, int weight) {
        this.v = v;
        this.weight = weight;
    }

    public int getV() {
        return v;
    }

    public void setV(int v) {
        this.v = v;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }
}