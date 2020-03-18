package pietnastka;

public class DFS extends Strategy {

    private String order;

    public DFS(Node parentNode, String order, Statistics statistics) {
        super(parentNode, statistics);
        this.order = order;
    }

    public String getOrder() {
        return order;
    }

    public boolean solveDFS() throws CloneNotSupportedException {
        this.getParentNode().setIfVisited(true);
        if (this.getParentNode().checkIfCorrect()) {
            getStatistics().solved = true;
            return true;
        } else
            iterate(getOrder(), getParentNode());
        return true;
    }

    public void iterate(String order, Node node) throws CloneNotSupportedException {
        getStatistics().visitedNodes++;
        if (node.getDepth() > Main.MAX_DEPTH || getStatistics().solved) {
            return;
        }
        getStatistics().processedNodes++;


        if (node.getDepth() > getStatistics().maxDepth) {
            getStatistics().maxDepth = node.getDepth();
        }

        if (node.checkIfCorrect()) {
            getStatistics().solved = true;
            makeSolution(node, getStatistics());
            return;
        }
        //adding children and recursion
        for (int i = 0; i < order.toCharArray().length; i++) {
            if (order.toCharArray()[i] == 'L') {
                if (node.getLeftChild() != null && node.getPrevMove() != 'R') {
                    Node childNode = node.getLeftChild();
                    childNode.setPrevMove('L');
                    node.getChildren().add(childNode);
                    iterate(order, childNode);
                }
            }
            if (order.toCharArray()[i] == 'U') {
                if (node.getUpChild() != null && node.getPrevMove() != 'D') {
                    Node childNode = node.getUpChild();
                    childNode.setPrevMove('U');
                    node.getChildren().add(childNode);
                    iterate(order, childNode);
                }
            }
            if (order.toCharArray()[i] == 'R') {
                if (node.getRightChild() != null && node.getPrevMove() != 'L') {
                    Node childNode = node.getRightChild();
                    childNode.setPrevMove('R');
                    node.getChildren().add(childNode);
                    iterate(order, childNode);
                }
            }
            if (order.toCharArray()[i] == 'D') {
                if (node.getDownChild() != null && node.getPrevMove() != 'U') {
                    Node childNode = node.getDownChild();
                    childNode.setPrevMove('D');
                    node.getChildren().add(childNode);
                    iterate(order, childNode);
                }
            }
            if (getStatistics().solved) {
                getStatistics().visitedNodes++;
                break;
            }

        }
        node.getChildren().clear();
    }

    private void makeSolution(Node node, Statistics statistics) {
        Node tmp = node;
        while (tmp.getParent() != null) {
            statistics.solution += tmp.getPrevMove();
            tmp = tmp.getParent();
        }
        statistics.revertSolution();
    }

}



