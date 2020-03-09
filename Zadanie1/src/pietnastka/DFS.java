package pietnastka;

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
        if (this.parentNode.checkIfCorrect()) {
            statistics.solved = true;
            System.out.println(statistics.toString());
            return true;
        } else
            addChildren(order, 1, statistics, parentNode);
        System.out.println(statistics.toString());
        return true;
    }

    public void addChildren(String order, int depth, Statistics statistics, Node node) throws CloneNotSupportedException {
        if (depth == 5)
            return;

        if (statistics.solved)
            return;
        else if (node.getParent() != null){
            statistics.visitedNodes++;
            System.out.println(node);
        }

        if (depth > statistics.maxDepth) {
            statistics.maxDepth = depth;
        }

        if (node.checkIfCorrect()) {
            System.out.println("----SOLVED----");
            statistics.solved = true;
            statistics.firstSolve = false;
            makeSolution(node, statistics);
            return;
        }

        for (int i = 0; i < order.toCharArray().length; i++) {
            if (order.toCharArray()[i] == 'L') {
                if (node.getLeftChild() != null && node.getPrevMove() != 'R') {
                    Node childNode = node.getLeftChild();
                    childNode.setPrevMove('L');
                    node.getChildren().add(childNode);
                    addChildren(order, depth + 1, statistics, childNode);
                }
            }
            if (order.toCharArray()[i] == 'U') {
                if (node.getUpChild() != null && node.getPrevMove() != 'D') {
                    Node childNode = node.getUpChild();
                    childNode.setPrevMove('U');
                    node.getChildren().add(childNode);
                    addChildren(order, depth + 1, statistics, childNode);
                }
            }
            if (order.toCharArray()[i] == 'R') {
                if (node.getRightChild() != null && node.getPrevMove() != 'L') {
                    Node childNode = node.getRightChild();
                    childNode.setPrevMove('R');
                    node.getChildren().add(childNode);
                    addChildren(order, depth + 1, statistics, childNode);
                }
            }
            if (order.toCharArray()[i] == 'D') {
                if (node.getDownChild() != null && node.getPrevMove() != 'U') {
                    Node childNode = node.getDownChild();
                    childNode.setPrevMove('D');
                    node.getChildren().add(childNode);
                    addChildren(order, depth + 1, statistics, childNode);
                }
            }

        }
    }

    private void makeSolution(Node node, Statistics statistics) {
        Node tmp = node;
        while (tmp.getParent() != null) {
            statistics.solution += tmp.getPrevMove();
            tmp = tmp.getParent();
        }
        statistics.reverseSolution();
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
