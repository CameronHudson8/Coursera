package algorithmsAndDataStructures;

public class ProgressBar {

    int currentProg = 0;
    int completion;
    int currentPercent = 0;
    int priorPercent = 0;

    public ProgressBar(int completion) {
        this.completion = completion;
        System.out.println(
                "|---------------------------------------------PROGRESS---------------------------------------------|");
    }

    public void increment() {
        this.currentProg += 1;
        this.currentPercent = this.currentProg * 100 / this.completion;
        if (this.currentPercent > this.priorPercent) {
            for (int i = this.priorPercent; i < this.currentPercent; i += 1) {
                System.out.print("|");
            }
            this.priorPercent = this.currentPercent;
            if (this.priorPercent == 100) {
                System.out.print("\n");
            }
        }
    }

}
