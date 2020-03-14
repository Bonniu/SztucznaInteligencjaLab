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

    public boolean reverseSolution() {
        this.solution = solution.trim();
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
                ", visitedNodes=" + visitedNodes +
                ", processedNodes=" + processedNodes +
                ", startTime=" + startTime +
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
