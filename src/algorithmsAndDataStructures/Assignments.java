package algorithmsAndDataStructures;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Each method corresponds to a Coursera assignment.
 * 
 * @author Cameron Hudson
 * @date 2018-03-31
 */
public class Assignments {

    /**
     * Assignment 1.4
     * 
     * Task: Given the adjacency list, compute the minimum cut of the Graph.
     */
    public static void assignment1_4() {
        final String FILENAME = "resources/_f370cd8b4d3482c940e4a57f489a200b_kargerMinCut.txt";
        final String INPUTFORMAT = "adjacency list";
        final boolean DIRECTED = false;

        Graph myGraph = new Graph(FILENAME, INPUTFORMAT, DIRECTED, true);
        System.out.println("Total number of vertices = " + myGraph.size());
        System.out.println("Total number of edges = " + myGraph.getEdgeCount());

        // myGraph.print();

        System.out.println("Computing minimum cut...");
        System.out.println("Minimum cut = " + myGraph.getMinCut());
    }

    /**
     * Assignment 2.1
     * 
     * Task: Given the edge list, compute the largest Strongly Connected Components of the Graph.
     */
    public static void assignment2_1() {
        System.out.println("Heads up: You may need to increase your default stack size"
                + "to run this.\nTry adding the \"-Xss128M \" flag to your command line"
                + "or virtual machine arguments list.");

        final String FILENAME = "resources/_410e934e6553ac56409b2cb7096a44aa_SCC.txt";
        final String INPUTFORMAT = "edge list";
        final boolean DIRECTED = true;

        Graph myGraph = new Graph(FILENAME, INPUTFORMAT, DIRECTED, false);
        System.out.println("Total number of vertices = " + myGraph.size());
        System.out.println("Total number of edges = " + myGraph.getEdgeCount());

        // myGraph.print();

        int n = 5;
        System.out.println("Finding the " + n + " largest SCC groups...");
        myGraph.printSccGroups(n);
    }

    /**
     * Assignment 2.2
     * 
     * Task: Given the adjacency list, compute the path lengths from vertex 1 to each other
     * specified vertex.
     */
    public static void assignment2_2() {

        final String FILENAME = "resources/_dcf1d02570e57d23ab526b1e33ba6f12_dijkstraData.txt";
        final String INPUTFORMAT = "adjacency list";
        final boolean DIRECTED = false;
        int[] VERTEXPATHSTOOUTPUT = new int[] { 7, 37, 59, 82, 99, 115, 133, 165, 188, 197 };

        Graph myGraph = new Graph(FILENAME, INPUTFORMAT, DIRECTED, true);
        System.out.println("Total number of vertices = " + myGraph.size());
        System.out.println("Total number of edges = " + myGraph.getEdgeCount());

        // myGraph.print();

        int startVId = 1;
        int destVId;
        Map<Integer, Double> shortestPaths = myGraph.getShortestPaths(startVId);
        for (int i = 0; i < VERTEXPATHSTOOUTPUT.length; i += 1) {
            destVId = VERTEXPATHSTOOUTPUT[i];
            System.out.println("Distance from Vertex " + startVId + " to Vertex " + destVId + " = "
                    + shortestPaths.get(destVId));
        }
    }

    /**
     * Assignment 2.3
     * 
     * Task: Given a list of integers, use a binary tree to compute the cumulative sum of the
     * medians, computing a new median each time an integer is loaded from the file.
     */
    public static void assignment2_3() {

        System.out.println("This script will produce warnings. Do not be alarmed.");

        final String FILENAME = "resources/_6ec67df2804ff4b58ab21c12edcb21f8_Median.txt";
        final int MODAMOUNT = 10000;

        BufferedReader br;
        TreeSetExtended<Integer> tree = new TreeSetExtended<Integer>();
        int sum = 0;
        try {
            br = new BufferedReader(new FileReader(new File(FILENAME)));
            String line;
            int value, statistic, median;
            while ((line = br.readLine()) != null) {
                value = Integer.parseInt(line);
                tree.add(value);

                // We want the median (IE, the ((.size() + 1) / 2)th-order statistic).
                statistic = (tree.size() + 1) / 2;
                median = tree.getStatistic(statistic);
                sum += median;
            }
        } catch (IOException e) {
            System.out.println("ERROR: IO Exception!");
            e.printStackTrace();
        }

        System.out.println("sumOfMedians % " + MODAMOUNT + " = " + (sum % MODAMOUNT));
    }

    /**
     * Assignment 2.4
     * 
     * Task: Given a list of integers, solve the 2SUM problem for sums on the interval
     * [-10000,10000], excluding 2*(the same number). In other words, for each sum on the interval,
     * determine whether or not there exist two integers in the data set that, when added together,
     * equal the sum being considered.
     */
    public static void assignment2_4() {
        System.out.println("Heads up: This script takes an extremely long time to run");

        final String FILENAME = "resources/_6ec67df2804ff4b58ab21c12edcb21f8_algo1-programming_prob-2sum.txt";
        final int SUMLOWERBOUND = -10000;
        final int SUMUPPERBOUND = 10000;

        Set<BigInteger> addends = new HashSet<BigInteger>();

        BufferedReader br;
        try {
            br = new BufferedReader(new FileReader(new File(FILENAME)));
            String line;
            while ((line = br.readLine()) != null) {
                addends.add(new BigInteger(line));
            }
        } catch (IOException e) {
            System.out.println("ERROR: IO Exception!");
            e.printStackTrace();
        }

        BigInteger i, complement;
        boolean found;
        int result = 0;

        // For each potential sum,
        for (int t = SUMLOWERBOUND; t <= SUMUPPERBOUND; t += 1) {
            found = false;

            // Iterate through the HashSet once.
            // Compute the complement that would create a sum of t.
            // Stop early if a valid complemented is located.
            Iterator<BigInteger> it = addends.iterator();
            while (!found && it.hasNext()) {
                i = it.next();
                complement = BigInteger.valueOf(t).subtract(i);

                // The rules state that it is not valid to have 2*(number) = sum.
                if (!complement.equals(i) && addends.contains(complement)) {
                    result += 1;
                    found = true;
                    System.out.println(Integer.toString(t) + " = " + i.toString() + " + "
                            + complement.toString());
                }
            }
        }
        System.out.println("\n");
        System.out.println("Found " + result + " solutions.");
    }

    /**
     * Assignment 3.1
     * 
     * Task1: Given a list of jobs with weights and lengths, order the jobs by (weight - length)
     * (descending) and report the resulting sum of weighted completion times. If two jobs have the
     * same (weight - length), prioritize the job with the higher weight.
     * 
     * Task2: Given the same list of jobs, order the jobs by (weight / length) (descending) and
     * report the resulting sum of weighted completion times.
     * 
     * Task3: Given an undirected graph with weighted edges, compute the total edge cost of the
     * Minimum Spanning Tree (MST) using Jarnik's (AKA Prim's) algorithm.
     */
    public static void assignment3_1() {

        // ------ Tasks 1 and 2 ------ //

        String FILENAME = "resources/_642c2ce8f3abe387bdff636d708cdb26_jobs.txt";

        JobQueue jqDiff = null, jqRatio = null;

        try {
            BufferedReader br = new BufferedReader(new FileReader(new File(FILENAME)));
            int jobCount = Integer.parseInt(br.readLine());
            jqDiff = new JobQueue(jobCount, "diff");
            jqRatio = new JobQueue(jobCount, "ratio");
            String jobString;
            String delim = "[ +]";
            int[] jobStringParts;
            int weight, length;
            while ((jobString = br.readLine()) != null) {
                jobStringParts = Arrays.stream(jobString.split(delim)).mapToInt(Integer::parseInt)
                        .toArray();
                weight = jobStringParts[0];
                length = jobStringParts[1];
                jqDiff.add(new Job(weight, length));
                jqRatio.add(new Job(weight, length));
            }
            br.close();

        } catch (FileNotFoundException e) {
            System.out.println("Encountered File Not Found exception!");
            e.printStackTrace();
        } catch (NumberFormatException e) {
            System.out.println("Encountered Number Format exception!");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("IO exception!");
            e.printStackTrace();
        }

        System.out.println("totalScore when prioritizing by (weight - length) = "
                + jqDiff.getTotalScore().toString());
        System.out.println("totalScore when prioritizing by (weight / length) = "
                + jqRatio.getTotalScore().toString());

        // ------ Task 3 ------ //

        FILENAME = "resources/_d4f3531eac1d289525141e95a2fea52f_edges.txt";

        Graph myGraph = new Graph(FILENAME, "edge list with header", false, false);
        Graph mst = myGraph.getMST();
        System.out.println("Total edge weight = " + mst.getEdgeWeight());

    }

    /**
     * Assignment 3.2
     * 
     * Task1: Given an edge list with lengths, find the maximum spacing of 4 clusters.
     * 
     * Task2: Given an edge list in Hamming format, compute the largest number of clusters that
     * still results in a spacing of at least 3.
     */
    public static void assignment3_2() {

        String FILENAME;
        Graph myGraph;

        // ------ Task 1 ------ //

        System.out.println("// ------ Task 1 ------ //");
        FILENAME = "resources/_fe8d0202cd20a808db6a4d5d06be62f4_clustering1.txt";
        myGraph = new Graph(FILENAME, "edge list with header", false, false);
        double spacing = myGraph.getClusterSpacing(4);
        System.out.println("Spacing = " + spacing);

        // ------ Task 2 ------ //
        
        System.out.println("// ------ Task 2 ------ //");
        FILENAME = "resources/_fe8d0202cd20a808db6a4d5d06be62f4_clustering_big.txt";
        myGraph = new Graph(FILENAME, "hamming list with header", false, false);
        List<Integer> clusters = myGraph.clusterHamming(2);
        System.out.println("Found " + clusters.size() + " clusters.");
        int total = 0;
        for (int e : clusters) {
            total += e;
        }
        System.out.println("Total vertices processed = " + total + ".");

    }
}
