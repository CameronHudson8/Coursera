package algorithmsAndDataStructures;

/**
 * This object consists of a weight and a length.
 * 
 * @author Cameron Hudson
 * @date 2018-03-29
 */
public class Job {
    int weight;
    int length;
    int diffScore;
    float ratioScore;
    
    public Job(int weight, int length) {
        this.weight = weight;
        this.length = length;
        this.diffScore = weight - length;
        this.ratioScore = (float) weight / length;
    }
}
