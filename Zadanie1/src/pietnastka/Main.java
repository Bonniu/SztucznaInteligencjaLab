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
        DFS dfs = new DFS(t);
        dfs.moveUP();
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
