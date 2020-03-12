package pietnastka;

public class Statistics {
    public int maxDepth = 1;
    public boolean solved = false;
    public String solution = "";
    public boolean firstSolve = true;
    public int visitedNodes = 1;
    private long startTime;
    public double elapsedTime = -1;

    public boolean reverseSolution() {
        char[] tmp = solution.toCharArray();
        for (int i = 0; i < tmp.length / 2; i++) {
            char t = tmp[i];
            tmp[i] = tmp[tmp.length - i - 1];
            tmp[tmp.length - i - 1] = t;
        }
        solution = "";
        for (char c : tmp) solution += c;
        return false;
    }

    @Override
    public String toString() {
        return "Statistics{" +
                "maxDepth=" + maxDepth +
                ", solved=" + solved +
                ", solution='" + solution + '\'' +
                ", firstSolve=" + firstSolve +
                ", visitedNodes=" + visitedNodes +
                ", elapsedTime=" + elapsedTime +
                '}';
    }

    public void startStopwatch() {
        this.startTime = System.nanoTime();
        this.elapsedTime = -1;
    }

    public void stopStopwatch() {
        long timeElapsed = System.nanoTime() - startTime;
        timeElapsed /= 1000;
        double milisecs = ((double) timeElapsed) / 1000;
        System.out.println("Execution time in milliseconds : " + milisecs);
        this.elapsedTime = milisecs;
    }
}
