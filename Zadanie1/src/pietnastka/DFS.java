package pietnastka;

import java.util.ArrayList;

public class DFS {

    private Board b;

    public DFS(Board b) {
        this.b = b;
    }


    public void moveUP() throws CloneNotSupportedException {
        System.out.println(b.toString());
        Board t = new Board((ArrayList<ArrayList<Integer>>) b.clone());
        System.out.println(t.toString());
        if (b.moveU()) {
            System.out.println(b.toString());
            System.out.println(t.toString());
        }
    }
}
