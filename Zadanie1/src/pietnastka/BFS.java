package pietnastka;

import java.util.ArrayList;
import java.util.List;

public class BFS extends Strategy {

    private String order;

    public BFS(Node parentNode, String order, Statistics statistics) {
        super(parentNode, statistics);
        this.order = order;
    }

    public String getOrder() {
        return order;
    }

    public List<Node> solveBFS() throws CloneNotSupportedException {
        List<Node> nodesToVisit = new ArrayList<>();
        List<Node> visitedNodes = new ArrayList<>();

        nodesToVisit.add(getParentNode()); // adds root to visited

        while (!getStatistics().solved) {
            Node currentNode = nodesToVisit.get(0);
            visitedNodes.add(currentNode);
            nodesToVisit.remove(0);
            moveBFS(currentNode, getOrder());
            nodesToVisit.addAll(currentNode.getChildren()); // adds kids of current node to list

            // for continuously checking whether we've found solution
            for (int i = 0; i < visitedNodes.size(); i++) {
                if (visitedNodes.get(i).getBoard().checkIfCorrect()) {
                    getStatistics().solved = true;
                    getStatistics().visitedNodes = visitedNodes.size();
                    Node correctNode = visitedNodes.get(i);
                    getStatistics().maxDepth = correctNode.getDepth();
                    System.out.println(correctNode.getBoard().toString());
                    findWay(correctNode);
                }
            }
//            for visualisation
//            System.out.println("---- PARENT:");
//            System.out.println("NUMBER OF KIDS:" + currentNode.getChildren().size());
//            System.out.println("NODES TO VISIT:" + nodesToVisit.size());
//            System.out.println("VISITED NODES: " + visitedNodes.size());
        }
        return visitedNodes;
    }

    //finds way to solution
    public void findWay(Node n) {
        Node currentNode = n;
        while (currentNode.getParent() != null) {
            this.getStatistics().solution += currentNode.getPrevMove();
            currentNode = currentNode.getParent();
        }
        getStatistics().revertSolution();
    }

    public void moveBFS(Node node, String order) throws CloneNotSupportedException {
        for (int i = 0; i < order.toCharArray().length; i++) {
            if (order.toCharArray()[i] == 'L') {
                if (node.getLeftChild() != null && node.getPrevMove() != 'R') {
                    Node childNode = node.getLeftChild();
                    childNode.setPrevMove('L');
                    node.getChildren().add(childNode);
                }
            }
            if (order.toCharArray()[i] == 'U') {
                if (node.getUpChild() != null && node.getPrevMove() != 'D') {
                    Node childNode = node.getUpChild();
                    childNode.setPrevMove('U');
                    node.getChildren().add(childNode);
                }
            }
            if (order.toCharArray()[i] == 'R') {
                if (node.getRightChild() != null && node.getPrevMove() != 'L') {
                    Node childNode = node.getRightChild();
                    childNode.setPrevMove('R');
                    node.getChildren().add(childNode);
                }
            }
            if (order.toCharArray()[i] == 'D') {
                if (node.getDownChild() != null && node.getPrevMove() != 'U') {
                    Node childNode = node.getDownChild();
                    childNode.setPrevMove('D');
                    node.getChildren().add(childNode);
                }
            }
        }
    }
}
