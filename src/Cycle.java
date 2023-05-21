import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Cycle {
    enum Color {
        WHITE,
        GRAY,
        BLACK
    }

    static Map<Vertex, Color> colors = new HashMap<>();

    static boolean explore(int u, Graph g) {
        Vertex v = new Vertex(u);
        colors.put(v, Color.GRAY);
        List<Vertex> adjList = g.getAdjLists().get(v);

        for (Vertex adj : adjList) {
            if (colors.get(adj) == Color.GRAY)
                return false;

            if (colors.get(adj) == Color.WHITE)
                if(!explore(adj.label, g))
                    return false;
        }

        colors.put(v, Color.BLACK);
        return true;
    }

    static boolean isAcyclic(Graph g) {
        for (int i = 1; i <= g.getVertices(); i++) {
            colors.put(new Vertex(i), Color.WHITE);
        }

        for (int i = 1; i < g.getVertices(); i++) {
            if (colors.get(new Vertex(i)) == Color.WHITE) {
                if (!explore(i, g))
                    return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int vertices = scanner.nextInt();
        int edges = scanner.nextInt();

        Graph g = new Graph(vertices);

        for (int i = 0; i < edges; i++) {
            int u = scanner.nextInt();
            int v = scanner.nextInt();
            g.addEdge(u, v);
        }

        boolean result = isAcyclic(g);
        if (result)
            System.out.println("nu are ciclu");
        else
            System.out.println("are ciclu");
    }
}