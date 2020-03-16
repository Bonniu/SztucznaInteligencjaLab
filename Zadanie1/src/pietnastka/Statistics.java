package pietnastka;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Statistics {
    public int maxDepth = 1;
    public boolean solved = false;
    public String solution = "";
    public int visitedNodes = 1;
    public int processedNodes = 1;
    private long startTime;
    public double elapsedTime = -1;

    public void revertSolution() {
        this.solution = solution.trim();
        StringBuilder tmp = new StringBuilder();
        for (int i = solution.length() - 1; i >= 0; i--)
            tmp.append(solution.charAt(i));
        this.solution = tmp.toString();
    }


    @Override
    public String toString() {
        return "Statistics{" +
                "maxDepth=" + maxDepth +
                ", solved=" + solved +
                ", solution='" + solution + '\'' +
                ", visitedNodes=" + visitedNodes +
                ", processedNodes=" + processedNodes +
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

    public void saveSolutionToFile(String saveFile, String additionalFile) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(saveFile));
        if (solution.length() < 1)
            writer.write(String.valueOf(-1));
        else {
            writer.write(String.valueOf(solution.length()));
            writer.write("\n" + solution);
        }
        writer.close();

        writer = new BufferedWriter(new FileWriter(additionalFile));
        if (solution.length() < 1)
            writer.write(String.valueOf(-1));
        else {
            writer.write(String.valueOf(solution.length()));
            writer.write("\n" + visitedNodes);
            writer.write("\n" + processedNodes);
            writer.write("\n" + maxDepth);
            writer.write("\n" + elapsedTime);
        }
        writer.close();

    }
}
