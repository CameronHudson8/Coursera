package algorithmsAndDataStructures;

import java.math.BigInteger;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * This object consists of a queue of Jobs. The order of the queue depends on the Comparator called
 * during construction.
 * 
 * @author Cameron Hudson
 * @date 2018-03-29
 */
public class JobQueue extends PriorityQueue<Job> {

    /**
     * Initialize a serialVersionUID to satisfy the compiler.
     */
    private static final long serialVersionUID = -1729265910812066570L;
    
    public JobQueue(int initialSize, String scoreMethod) {
        super(initialSize, decideMethod(scoreMethod));
    }
    
    private static Comparator<Job> decideMethod(String scoreMethod) {

        switch (scoreMethod) {
        case "diff":
            return (Comparator<Job>) new diffScoreComparator();
        case "ratio":
            return (Comparator<Job>) new ratioScoreComparator();
        default:
            System.out.println("Error: Could not determine method of job scoring.");
            return null;
        }
    }
    
    public BigInteger getTotalScore() {
        
        JobQueue copyQueue = this;
        Job j;
        BigInteger totalScore = BigInteger.valueOf(0);
        BigInteger totalLength = BigInteger.valueOf(0);
        while (copyQueue.size() > 0) {
            j = copyQueue.remove();
            totalLength = totalLength.add(BigInteger.valueOf(j.length));
            totalScore = totalScore.add((BigInteger.valueOf(j.weight).multiply(totalLength)));
        }
        return totalScore;
    }
    
}
