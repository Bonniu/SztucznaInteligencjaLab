package pietnastka;

import java.io.IOException;

public class Main {

    public static final int MAX_DEPTH = 20;

    public static void main(String[] args) throws CloneNotSupportedException, IOException {
        StartController c;
        try {
            c = new StartController(args);
        } catch (ExceptionInInitializerError exception) {
            exception.printStackTrace();
            return;
        }
        Board t = new Board();
        t.loadTabFromFile(c.getLoadFileName());
        Node n = new Node(t, null, 0);
        methodController(c, n);
    }

    public static void methodController(StartController c, Node n) throws CloneNotSupportedException, IOException {
        System.out.println(c.toString());
        Statistics st = new Statistics();
        if (c.getMethod().toLowerCase().equals("dfs")) {
            DFS dfs = new DFS(n, c.getOrderHeuristics(), st);
            st.startStopwatch();
            dfs.solveDFS();
            st.stopStopwatch();
        } else if (c.getMethod().toLowerCase().equals("bfs")) {
            BFS bfs = new BFS(n, c.getOrderHeuristics(), st);
            st.startStopwatch();
            bfs.solveBFS();
            st.stopStopwatch();
        } else {
            //astr +
            if (c.getOrderHeuristics().equals("manh")) {
                //astr manh
                Manhattan manhattan = new Manhattan(n, st);
                st.startStopwatch();
                manhattan.solveManhattan();
                st.stopStopwatch();
            } else {
                // astr hamm
                Hamming hamming = new Hamming(n, st);
                st.startStopwatch();
                hamming.solveHamming();
                st.stopStopwatch();



            }
        }
        System.out.println(st.toString());
        st.saveSolutionToFile(c.getSaveFileName(), c.getAdditionalFileName());
    }


}
