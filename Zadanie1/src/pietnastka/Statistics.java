package pietnastka;

import java.util.ArrayList;
import java.util.Arrays;

public class Statistics {
    public int maxDepth = 1;
    public boolean solved = false;
    public String solution = "";
    public boolean firstSolve = true;
    public int visitedNodes = 1;

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
                '}';
    }
}
