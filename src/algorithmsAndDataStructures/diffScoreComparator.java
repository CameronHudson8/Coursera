package algorithmsAndDataStructures;

import java.util.Comparator;

/**
 * A comparator that prioritizes jobs based on weight - length.
 * 
 * @author	Cameron Hudson
 * @date		2018-03-29
 */
public class diffScoreComparator implements Comparator<Job>{

    @Override
    public int compare(Job job1, Job job2) {
        if (job1.diffScore == job2.diffScore) {
            return job2.weight - job1.weight;
        }
        return job2.diffScore - job1.diffScore;
    }
}