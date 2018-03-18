package algorithmsAndDataStructures;

/**
 * This object is an edge connecting to a destination vertex according to some weight.
 * 
 * @author Cameron Hudson
 * @date 2018-03-16
 */
public class Edge {

    // Declare public variables.
    public Vertex dest;
    public double weight;

    /**
     * A constructor for this Edge object.
     * 
     * @param dest The destination Vertex.
     * @param weight The weight (AKA length) of this edge.
     */
    public Edge(Vertex dest, double weight) {
        this.dest = dest;
        this.weight = weight;
    }

    /**
     * Prints the destination Vertex and the weight of this edge.
     */
    public void print() {
        System.out.print("v=" + this.dest.id + "   \tw=" + this.weight + ",  \t");
    }

    /**
     * Returns a new Edge instance with the same properties as this one.
     *
     * @return A deep copy of this Edge.
     */
    public Edge copy() {
        Edge e = new Edge(this.dest.copy(), this.weight);
        return e;
    }

}
