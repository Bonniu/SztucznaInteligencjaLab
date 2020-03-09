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
        Statistics statistics = new Statistics();
        this.parentNode.setIfVisited(true);
        if (this.parentNode.checkIfCorrect())
            return true;
        else
            this.parentNode.addChildren(order, 0, statistics);
        System.out.println("XD");
        iterate(parentNode);
        System.out.println("maxz depth " + statistics.maxDepth);

        return true;
    }

    public boolean iterate(Node node) {
        if (node.getChildren() != null) {
            for (int i = 0; i < node.getChildren().size(); i++) {
                if (node.getChildren() == null || node.getChildren().size() == 0)
                    return false;
                if (node.getChildren().get(i).checkIfCorrect()) {
                    System.out.println("SOLVED ============================================================");
                    System.out.println(node.getChildren().get(i).toString());
                    //save solution here
                    return true;
                }
                if (iterate(node.getChildren().get(i)))
                    return true;
                //save solution here
            }
        }
        return false;
    }

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
//}

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
