package pietnastka;

import java.util.List;

public class Main {


    public static void main(String[] args) throws CloneNotSupportedException {
//        StartController c;
//        try {
//            c = new StartController(args);
//        } catch (ExceptionInInitializerError exception) {
//            exception.printStackTrace();
//            return;
//        }
//        Board t = new Board();
//        t.loadTabFromFile(c.getLoadFileName());
//        Node n = new Node(t, null);
//
//        methodController(c, n);

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

        BFS bfs = new BFS(c.getOrderHeuristics());
        bfs.poszukiwanieBFS(n);


    }

    public static void methodController(StartController c, Node n) throws CloneNotSupportedException {
        System.out.println(c.toString());
        if (c.getMethod().toLowerCase().equals("dfs")) {
            DFS dfs = new DFS(n, c.getOrderHeuristics());
            Statistics st = new Statistics();
            st.startStopwatch();
            dfs.solve(st);
            st.stopStopwatch();
            System.out.println(st.toString());
        } else if (c.getMethod().toLowerCase().equals("bfs")) {
            //      BFS bfs = new BFS(n, c.getOrderHeuristics()); albo cos takiego
            Statistics st = new Statistics();
            st.startStopwatch();
            //   bfs.solve(st); czy cos takiego
            st.stopStopwatch();
            System.out.println(st.toString());
        } else //astr
        {

        }
    }


}
