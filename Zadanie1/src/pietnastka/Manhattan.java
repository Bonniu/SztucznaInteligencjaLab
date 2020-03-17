package pietnastka;

import java.util.ArrayList;

public class Manhattan extends Strategy {

    ArrayList<Node> queue;

    public Manhattan(Node parentNode, Statistics statistics) {
        super(parentNode, statistics);
        queue = new ArrayList<>();
    }

    public boolean solveManhattan() throws CloneNotSupportedException {
        addNodeChildren(getParentNode());
        queue.addAll(getParentNode().getChildren());
        getStatistics().processedNodes += getParentNode().getChildren().size();
        sortQueue();
        iterate(queue.get(queue.size() - 1));
        return true;
    }

    public void iterate(Node node) throws CloneNotSupportedException {
        queue.remove(queue.size() - 1);
        if (node.getDepth() > Main.MAX_DEPTH || getStatistics().solved)
            return;
        else if (node.getParent() != null) {
            getStatistics().visitedNodes++;
        }

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
        return distancesFromCorrectPlaces(node.getBoard()) + node.getDepth();
    }

    private int distancesFromCorrectPlaces(Board board) {
        ArrayList<ArrayList<Integer>> correct = new ArrayList<>(); // tablica wzorcowa i poniżej jej wypełnianie
        for (int j = 0; j < board.getRows(); j++) {
            ArrayList<Integer> temp = new ArrayList<>(board.getRows());
            for (int i = 1; i <= board.getColumns(); i++) {
                if (i + board.getColumns() * j != board.getColumns() * board.getRows())
                    temp.add(i + board.getColumns() * j);
                else
                    temp.add(0);
            }
            correct.add(temp);
        }
        // obliczanie dystansu
        int sum = 0;
        for (int i = 0; i < board.getRows(); i++) {
            for (int j = 0; j < board.getColumns(); j++) {
                int tmpValue = board.getTab().get(i).get(j); // element

                for (int x = 0; x < board.getRows(); x++) {
                    for (int y = 0; y < board.getColumns(); y++) {
                        if (correct.get(x).get(y) == tmpValue) {
                            sum += Math.abs(x - i) + Math.abs(y - j);
                        }

                    }
                }

            }
        }
        return sum;
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
