import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * Kruskal's Algorithm implementation
 * 
 * @author Nic Falcione
 * @version 11/22/19
 */
public class Kruskal {
    ArrayList<Edge> edges;
    Integer size;

    public Kruskal() {
        edges = new ArrayList<Edge>();
        size = 0;
    }

    public void addEdge(Integer root, Integer child, Integer weight) {
        edges.add(new Edge(root, child, weight));
    }

    public Integer find(ArrayList<Integer> set, Integer vertex) {
        if (set.get(vertex) != vertex) {
            return find(set, set.get(vertex));
        }
        return vertex;
    }

    public void makeUnion(ArrayList<Integer> set, Integer u, Integer v) {
        Integer uSet = find(set, u);
        Integer vSet = find(set, v);

        set.set(vSet, uSet);
    }

    public void Kruskals() {
        PriorityQueue<Edge> greedyList = new PriorityQueue<Edge>(edges.size(),
                Comparator.comparingInt(edge -> edge.weight));
        for (Edge edge : edges) {
            greedyList.add(edge);
        }

        ArrayList<Integer> set = new ArrayList<Integer>();
        for (int i = 0; i < size; i++) {
            set.add(i);
        }

        ArrayList<Edge> minimumSpanning = new ArrayList<Edge>();

        int i = 0;
        while (i < size - 1) {
            Edge e = greedyList.remove();
            Integer u = find(set, e.root);
            Integer v = find(set, e.child);

            if (u != v) {
                minimumSpanning.add(e);
                i++;
                makeUnion(set, u, v);
            }
        }

        Integer sum = 0;
        Integer index = 0;
        for (Edge e : minimumSpanning) {
            System.out.println("Edge " + index + " is between Vertex " + e.root
                    + " and Vertex " + e.child);
            sum += e.weight;
            index++;
        }
        System.out.println("The weight of this minimum spanning tree found is: "
                + sum + "\n");
    }
}
