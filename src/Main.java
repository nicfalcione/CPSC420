import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map.Entry;
import java.util.Scanner;

/**
 * Main program to run for the project
 * 
 * @author Nic Falcione
 * @version 11/22/19
 */
public class Main {

    /**
     * Main method to run program
     * 
     * @param args String arguments
     */
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        while (true) {
            System.out.println(
                    "Greetings! This is a program to showcase Kruskal's Algorithm and Depth-First Search. Now we need your input!\n"
                            + "Press the corresponding number and then press enter to run the algorithm of your choice.\n\n1. Depth-First Search\n"
                            + "2. Kruskal's Algorithm\n3. Exit Program");
            String userSelection = scan.next();
            if (userSelection.equals("1")) {
                System.out.println(
                        "This implementation is able to traverse graphical data structures of Integers input by the user.\n"
                                + "You will be asked for each vertex's name and Integer value. When you are done, type `done` and \n"
                                + "press enter. Then you will be asked for the edges between these vertices. When you are done, "
                                + "type `done` and then press enter.\n");
                Graph graph = createGraph(scan);
                String root = "";
                Integer value = -1;
                while (true) {
                    System.out.println(
                            "Which vertex would you like to start the traversal at?");
                    root = scan.next();
                    boolean found = false;
                    System.out.println(
                            "What Integer value are you looking for?");
                    String input = scan.next();
                    if (!input.matches("-?(0|[1-9]\\d*)")) {
                        System.out.println("Input invalid and not an Integer.");
                        continue;
                    }
                    value = Integer.parseInt(input);

                    Iterator<Entry<Vertex, LinkedList<Vertex>>> iter = graph.adjacencyMatrix
                            .entrySet().iterator();
                    while (iter.hasNext()) {
                        Vertex v = iter.next().getKey();
                        if (v.name.equals(root)) {
                            found = true;
                            graph.traverse(v, value);
                            break;
                        }
                    }
                    if (!found) {
                        System.out.println(
                                "Desired root was not found, try again.");
                    } else {
                        break;
                    }
                }
            }
            if (userSelection.equals("2")) {
                Kruskal graph = createWeightedGraph(scan);
                graph.Kruskals();
            } else if (userSelection.equals("3")) {
                System.out.println("Exiting.");
                break;
            } else {
                continue;
            }
        }
        scan.close();
    }

    public static Kruskal createWeightedGraph(
            Scanner scan) {
        Kruskal graph = new Kruskal();
        ArrayList<Integer> vertices = new ArrayList<Integer>();
        System.out.println(
                "This program will use your input to create a weighted graph and find the minimal\n"
                        + "spanning tree using Kruskal's Algorithm. You will be prompted to give the Integer vertex\n"
                        + "names. These must start at `0` and go up one by one. Then you will give the weight for each edge.\n");
        while (true) {
            System.out.println(
                    "What is the Integer name of the first vertex in this edge?");
            String line = scan.next();
            if (line.equals("done")) {
                break;
            }

            if (!line.matches("-?(0|[1-9]\\d*)")) {
                System.out.println("Input invalid and not an Integer.");
                continue;
            }
            Integer value = Integer.parseInt(line);

            Integer vertex1 = value;

            System.out.println(
                    "What is the name of the vertex to be connected to that edge?");
            line = scan.next();

            if (!line.matches("-?(0|[1-9]\\d*)")) {
                System.out.println("Input invalid and not an Integer.");
                continue;
            }

            Integer vertex2 = Integer.parseInt(line);

            if (vertex1 == vertex2) {
                System.out.println(
                        "You can't connect a vertex to itself. Try again.");
                continue;
            }

            System.out.println(
                    "What is the weight of this edge?");
            line = scan.next();

            if (!line.matches("-?(0|[1-9]\\d*)")) {
                System.out.println("Input invalid and not an Integer.");
                continue;
            }

            if (!vertices.contains(vertex2)) {
                vertices.add(vertex2);
            }
            if (!vertices.contains(vertex1)) {
                vertices.add(vertex1);
            }

            Integer weight = Integer.parseInt(line);

            graph.addEdge(vertex1, vertex2, weight);

        }
        graph.size = vertices.size();
        return graph;
    }

    public static Graph createGraph(
            Scanner scan) {
        Graph dfs = new Graph();
        ArrayList<Vertex> vertices = new ArrayList<Vertex>();

        while (true) {
            System.out.println("Is this graph directed? `y` or `n`");
            String line = scan.next();

            if (line.equals("y")) {
                dfs.directed = true;
                break;
            } else if (line.equals("n")) {
                dfs.directed = false;
                break;
            } else {
                System.out.println("Input invalid. Try again.");
                continue;
            }
        }

        while (true) {
            System.out.println("What is the name of this vertex?");
            String line = scan.next();
            boolean duplicate = false;

            for (int i = 0; i < vertices.size(); i++) {
                if (vertices.get(i).name.equals(line)) {
                    System.out.println(
                            "Vertex already created. Try creating another one with a different name.");
                    duplicate = true;
                }
            }

            if (duplicate) {
                continue;
            }

            if (line.equals("done")) {
                break;
            } else {
                String name = line;
                System.out.println("What is the integer value of this vertex?");
                String value = scan.next();
                if (!value.matches("-?(0|[1-9]\\d*)")) {
                    System.out.println("Input invalid and not an Integer.");
                    continue;
                }
                vertices.add(new Vertex(name, value));
            }
        }

        while (true) {
            System.out.println(
                    "What is the name of the first vertex in this edge?");
            String line = scan.next();
            if (line.equals("done")) {
                break;
            } else {
                String vertex1 = line;
                Vertex v1 = null;
                Vertex v2 = null;
                System.out.println(
                        "What is the name of the vertex to be connected to that edge?");
                String vertex2 = scan.next();

                if (vertex1.contentEquals(vertex2)) {
                    System.out.println(
                            "You can't connect a vertex to itself. Try again.");
                    continue;
                }

                for (int i = 0; i < vertices.size(); i++) {
                    if (vertices.get(i).name.equals(vertex1)) {
                        v1 = vertices.get(i);
                    }
                    if (vertices.get(i).name.equals(vertex2)) {
                        v2 = vertices.get(i);
                    }
                }
                if (v1 != null && v2 != null) {
                    dfs.addEdge(v1, v2);
                } else {
                    System.out.println("Your edge had a problem. Try again.");
                }
            }
        }

        return dfs;
    }
}
