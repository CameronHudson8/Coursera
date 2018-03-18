package algorithmsAndDataStructures;

import java.util.ArrayList;

/**
 * This object is a Vertex connected to other Vertices by Edges.
 * 
 * @author Cameron Hudson
 * @date 2018-03-16
 */
public class Vertex extends ArrayList<Edge> {

    // Declare a serialVersionUID to make the compiler happy.
    private static final long serialVersionUID = 5083837790109150180L;

    // Declare public variables.
    public int id;
    public boolean seen = false;
    public int sccGroup;
    public double dijkstraScore;

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
        for (int i = 0; i < this.size(); i += 1) {
            this.get(i).print();
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
        for (int i = 0; i < this.size(); i += 1) {
            v.add(this.get(i).copy());
        }

        return v;
    }
}
