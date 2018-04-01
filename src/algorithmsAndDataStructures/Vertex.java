package algorithmsAndDataStructures;

import java.util.HashSet;

/**
 * This object is a Vertex connected to other Vertices by Edges.
 * 
 * @author Cameron Hudson
 * @date 2018-03-31
 */
public class Vertex extends HashSet<Edge> {

    // Declare a serialVersionUID to make the compiler happy.
    private static final long serialVersionUID = 5083837790109150180L;

    // Declare public variables.
    public int id;
    public boolean seen = false;
    public int sccGroup;
    public double dijkstraScore;
    public double mstScore;

    /**
     * Constructs a Vertex with the specified id.
     * 
     * @param id The id of this Vertex.
     */
    public Vertex(int id) {
        this.id = id;
    }

    /**
     * Prints this Vertex's id and [outgoing] edges.
     */
    public void print() {
        System.out.print("Vertex " + this.id + ": ");
        for (Edge e : this) {
            e.print();
        }
        System.out.println("");
    }

    /**
     * Returns a new Vertex instance with the same properties as this one.
     *
     * @return A deep copy of this Vertex.
     */
    public Vertex copy() {
        Vertex v = new Vertex(this.id);
        v.seen = this.seen;
        v.sccGroup = this.sccGroup;
        for (Edge e : this) {
            v.add(e.copy());
        }

        return v;
    }
}
