package algorithmsAndDataStructures;

/**
 * This class contains the main() function for this project.
 * 
 * @author Cameron Hudson
 * @date 2018-03-16
 */
public class Main {

    /**
     * Imports a Graph and then performs some actions on it.
     * 
     * @param args A default variable that is not used.
     */
    public static void main(String[] args) {

        // final String FILENAME = "resources/_f370cd8b4d3482c940e4a57f489a200b_kargerMinCut.txt";
        // final String FILENAME = "resources/_410e934e6553ac56409b2cb7096a44aa_SCC.txt";
        final String FILENAME = "resources/_dcf1d02570e57d23ab526b1e33ba6f12_dijkstraData.txt";
        final String INPUTFORMAT = "adjacency list";
        final boolean DIRECTED = false;

        Graph myGraph = new Graph(FILENAME, INPUTFORMAT, DIRECTED);
        System.out.println("Total number of vertices = " + myGraph.size());
        System.out.println("Total number of edges = " + myGraph.getEdgeCount());

        myGraph.print();

        // System.out.println("Computing minimum cut...");
        // System.out.println("Minimum cut = " + myGraph.getMinCut());

        // int n = 5;
        // System.out.println("Finding the " + n + " largest SCC groups...");
        // myGraph.printSccGroups(n);

    }

}
