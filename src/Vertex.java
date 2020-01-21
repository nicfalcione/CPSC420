/**
 * Vertex class for Depth-First Search Implementation
 * 
 * @author Nic Falcione
 * @version 11/22/2019
 */
public class Vertex {

    String name;
    Integer value;
    boolean visited;

    public Vertex(String n, String val) {
        name = n;
        value = Integer.parseInt(val);
        visited = false;
    }
    
    public void visit() {
        visited = true;
    }
}
