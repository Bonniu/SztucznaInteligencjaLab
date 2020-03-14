package pietnastka;

public class DFS {

    private Node parentNode;
    private String order;
    private Statistics statistics;

    public DFS(Node parentNode, String order, Statistics statistics) {
        this.parentNode = parentNode;
        this.order = order;
        this.statistics = statistics;
    }

    public boolean solveDFS() throws CloneNotSupportedException {
        this.parentNode.setIfVisited(true);
        if (this.parentNode.checkIfCorrect()) {
            statistics.solved = true;
            return true;
        } else
            addChildren(order, 1, parentNode);
        return true;
    }

    public void addChildren(String order, int depth, Node node) throws CloneNotSupportedException {
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
                    addChildren(order, depth + 1, childNode);
                }
            }
            if (order.toCharArray()[i] == 'U') {
                if (node.getUpChild() != null && node.getPrevMove() != 'D') {
                    Node childNode = node.getUpChild();
                    childNode.setPrevMove('U');
                    node.getChildren().add(childNode);
                    addChildren(order, depth + 1, childNode);
                }
            }
            if (order.toCharArray()[i] == 'R') {
                if (node.getRightChild() != null && node.getPrevMove() != 'L') {
                    Node childNode = node.getRightChild();
                    childNode.setPrevMove('R');
                    node.getChildren().add(childNode);
                    addChildren(order, depth + 1, childNode);
                }
            }
            if (order.toCharArray()[i] == 'D') {
                if (node.getDownChild() != null && node.getPrevMove() != 'U') {
                    Node childNode = node.getDownChild();
                    childNode.setPrevMove('D');
                    node.getChildren().add(childNode);
                    addChildren(order, depth + 1, childNode);
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



