package pietnastka;

import java.util.ArrayList;

public class Main {


    public static void main(String[] args) throws CloneNotSupportedException {
        System.out.println("eluwa");
        StartController c;
        try {
            c = new StartController(args);
        } catch (ExceptionInInitializerError exception) {
            exception.printStackTrace();
            return;
        }
        System.out.println(c.toString());
        Board t = new Board();
        t.loadTabFromFile(c.getLoadFileName());
        Node n = new Node(t, null);
        System.out.println(t.checkIfCorrect());
        DFS dfs = new DFS(n, c.getOrderHeuristics());
        dfs.solve();
//        System.out.println(t.toString());
//        t.moveD();
//        System.out.println(t.toString());
//        t.moveL();
//        System.out.println(t.toString());
//        t.moveU();
//        System.out.println(t.toString());
//        t.moveR();
//        System.out.println(t.toString());

    }


}
