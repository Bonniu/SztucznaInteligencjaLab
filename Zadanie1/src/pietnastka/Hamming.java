package pietnastka;

import java.util.ArrayList;

public class Hamming extends Strategy {

    ArrayList<Node> queue;

    public Hamming(Node parentNode, Statistics statistics) {
        super(parentNode, statistics);
        queue = new ArrayList<>();
    }

    public boolean solveHamming() throws CloneNotSupportedException {
        addNodeChildren(getParentNode());
        System.out.println(getParentNode().getChildren().size());
        getStatistics().processedNodes += getParentNode().getChildren().size();
        queue.addAll(getParentNode().getChildren());
        sortQueue();
        iterate(queue.get(queue.size() - 1));
        return true;
    }

    public void iterate(Node node) throws CloneNotSupportedException {
        queue.remove(queue.size() - 1);
        getStatistics().visitedNodes++;
        if (node.getDepth() > Main.MAX_DEPTH || getStatistics().solved)
            return;
        if (node.getDepth() > getStatistics().maxDepth) {
            getStatistics().maxDepth = node.getDepth();
        }
        if (node.getBoard().checkIfCorrect()) {
            getStatistics().solved = true;
            makeSolution(node, getStatistics());
            return;
        }
        addNodeChildren(node);
        getStatistics().processedNodes += node.getChildren().size();
        queue.addAll(node.getChildren());
        sortQueue();
        iterate(queue.get(queue.size() - 1));
    }

    private void addNodeChildren(Node node) throws CloneNotSupportedException {
        if (node.getLeftChild() != null && node.getPrevMove() != 'R') {
            Node childNode = node.getLeftChild();
            childNode.setPrevMove('L');
            node.getChildren().add(childNode);
        }
        if (node.getDownChild() != null && node.getPrevMove() != 'U') {
            Node childNode = node.getDownChild();
            childNode.setPrevMove('D');
            node.getChildren().add(childNode);
        }
        if (node.getRightChild() != null && node.getPrevMove() != 'L') {
            Node childNode = node.getRightChild();
            childNode.setPrevMove('R');
            node.getChildren().add(childNode);
        }
        if (node.getUpChild() != null && node.getPrevMove() != 'D') {
            Node childNode = node.getUpChild();
            childNode.setPrevMove('U');
            node.getChildren().add(childNode);
        }
    }

    private void printValues() {
        for (int i = 0; i < queue.size(); i++) {
            System.out.println(i + " " + functionValue(queue.get(i)));
        }
    }

    private int functionValue(Node node) {
        return node.getBoard().incorrectPlaced() + node.getDepth();
    }

    private void sortQueue() {
        if (queue.size() < 2)
            return;
        if (queue.size() == 2) {
            if (functionValue(queue.get(1)) < functionValue(queue.get(0))) {
                swapPlaces(1, 0);
                return;
            }
        }
        for (int i = 0; i < queue.size() - 1; i++) {
            for (int j = i + 1; j < queue.size(); j++) {
                if (functionValue(queue.get(i)) < functionValue(queue.get(j)))
                    swapPlaces(i, j);
            }
        }
    }

    private void swapPlaces(int a, int b) {
        Node tmp = queue.get(a);
        queue.set(a, queue.get(b));
        queue.set(b, tmp);
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
