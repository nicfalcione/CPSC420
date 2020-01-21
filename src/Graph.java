import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;

/**
 * Graph Implementation
 * 
 * @author Nic Falcione
 * @version 11/22/19
 */
public class Graph {

    HashMap<Vertex, LinkedList<Vertex>> adjacencyMatrix;
    boolean directed;

    public Graph() {
        directed = false;
        adjacencyMatrix = new LinkedHashMap<Vertex, LinkedList<Vertex>>();
    }

    public void addEdge(Vertex root, Vertex child) {
        if (!adjacencyMatrix.keySet().contains(root)) {
            adjacencyMatrix.put(root, null);
        }
        if (!adjacencyMatrix.keySet().contains(child)) {
            adjacencyMatrix.put(child, null);
        }

        LinkedList<Vertex> list = adjacencyMatrix.get(root);

        if (list != null) {
            list.remove(child);
        } else {
            list = new LinkedList<Vertex>();
        }
        list.add(child);
        adjacencyMatrix.put(root, list);

        if (!directed) {
            undirectedConnect(root, child);
        }
    }

    public void undirectedConnect(Vertex root, Vertex child) {
        LinkedList<Vertex> list = adjacencyMatrix.get(root);

        if (list != null) {
            list.remove(child);
        } else {
            list = new LinkedList<Vertex>();
        }
        list.add(child);
        adjacencyMatrix.put(root, list);
    }

    public void traverse(Vertex vertex, Integer value) {
        vertex.visit();
        System.out.print(vertex.name + " ");
        if (vertex.value == value) {
            System.out.print("(Found!) ");
        }

        LinkedList<Vertex> adjacencies = adjacencyMatrix.get(vertex);
        if (adjacencies == null) {
            return;
        }

        for (Vertex child : adjacencies) {
            if (!child.visited)
                traverse(child, value);
        }
    }
}
