package pietnastka;

public class Main {

    public static void main(String[] args) throws CloneNotSupportedException {
        StartController c;
        try {
            c = new StartController(args);
        } catch (ExceptionInInitializerError exception) {
            exception.printStackTrace();
            return;
        }
        Board t = new Board();
        t.loadTabFromFile(c.getLoadFileName());
        Node n = new Node(t, null);

        methodController(c, n);
    }

    public static void methodController(StartController c, Node n) throws CloneNotSupportedException {
        System.out.println(c.toString());
        Statistics st = new Statistics();
        if (c.getMethod().toLowerCase().equals("dfs")) {
            DFS dfs = new DFS(n, c.getOrderHeuristics(), st);
            st.startStopwatch();
            dfs.solveDFS();
            st.stopStopwatch();
            System.out.println(st.toString());
        } else if (c.getMethod().toLowerCase().equals("bfs")) {
            BFS bfs = new BFS(n, c.getOrderHeuristics(), st);
            st.startStopwatch();
            bfs.solveBFS();
            st.stopStopwatch();
            System.out.println(st.toString());
        } else //astr
        {
            if (c.getOrderHeuristics().equals("manh")) {
                //manhattan - manh
                Manhattan manhattan = new Manhattan(n, st);
                manhattan.solveManhattan();

            } else {
                // hamming - hamm
                Hamming hamming = new Hamming(n, st);
                hamming.solveHamming();

            }
        }
    }


}
