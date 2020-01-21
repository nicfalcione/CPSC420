/**
 * Edge class implementation
 * 
 * @author Nic
 * @version 11/22/2019
 */
public class Edge {
    Integer root;
    Integer child;
    Integer weight;

    public Edge(Integer r, Integer c, Integer w) {
        root = r;
        child = c;
        weight = w;
    }
}
