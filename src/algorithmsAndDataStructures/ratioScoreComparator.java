package algorithmsAndDataStructures;

import java.util.Comparator;

/**
 * A comparator that prioritizes jobs based on weight / length.
 * 
 * @author	Cameron Hudson
 * @date		2018-03-29
 */
public class ratioScoreComparator implements Comparator<Job>{

    @Override
    public int compare(Job job1, Job job2) {
        return (job1.ratioScore > job2.ratioScore) ? -1 : +1;
    }
}