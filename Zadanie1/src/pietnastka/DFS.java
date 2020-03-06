package pietnastka;

import java.util.ArrayList;

public class DFS {

    private Node parentNode;
    private String order;

    public DFS(Node parentNode, String order) {
        this.parentNode = parentNode;
        this.order = order;
    }

    public boolean solve() throws CloneNotSupportedException {
        System.out.println(order);
        this.parentNode.setIfVisited(true);
        if (this.parentNode.checkIfCorrect())
            return true;
        else
            this.parentNode.addChildren(order, this.parentNode, 1);
        System.out.println(parentNode.getChildren().size());
        for (int i = 0; i < parentNode.getChildren().size(); i++) {
            if (parentNode.getChildren().get(i) != null) {
                System.out.println(parentNode.getChildren().get(i).toString());
                if (parentNode.getChildren().get(i).checkIfCorrect()) {
                    System.out.println("SOLVED ============================================================");
                    System.out.println(parentNode.getChildren().get(i).toString());
                    //save solution here
                    return true;
                }
            }
        }
        return true;
    }


//    public void moveUP() throws CloneNotSupportedException {
//        System.out.println(b.toString());
//        Board t = new Board((ArrayList<ArrayList<Integer>>) b.clone());
//        System.out.println(t.toString());
//        if (b.moveU()) {
//            System.out.println(b.toString());
//            System.out.println(t.toString());
//        }
//    }
}

/*
    wejdz do pierwszego
    wejdz do dziecka wg kolejnosci np L
    wejdz do dziecka wg kolejnosci np L
    .
    .
    .
    ostatnie (20) poprawne ze wzorcem - wyjscie
    nie poprawne- cofnij do rodzenstwa, jak nie ma
    to do rodzica


 */
