import java.util.*;

public class Graph {

    public Map<Vertex, List<Vertex>> getAdjLists() {
        return adjLists;
    }

    public int getVertices() {
        return vertices;
    }

    private Map<Vertex, List<Vertex>> adjLists = new HashMap<>();
    private int vertices;

    public Graph(int vertices) {
        this.vertices = vertices;

        for (int i = 1; i <= vertices; i++) {
            adjLists.put(new Vertex(i), new LinkedList<>());
        }
    }

    void addEdge(int u, int v) {
        Vertex v1 = new Vertex(u);
        Vertex v2 = new Vertex(v);

        adjLists.get(v1).add(v2);
    }
}
