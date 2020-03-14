package pietnastka;

import java.util.ArrayList;

public class Node {

    private Node parent;
    private Board board;
    private boolean ifVisited;
    private ArrayList<Node> children;
    private char prevMove = ' ';
    private int metricValue;
    private int depth;

    public int getDepth() {
        return depth;
    }

    public Node(Board board, Node parent, int depth) {
        this.board = board;
        this.ifVisited = false;
        this.children = new ArrayList<>();
        this.parent = parent;
        this.depth = depth;
    }

    public void setMetricValue(int metricValue) {
        this.metricValue = metricValue;
    }

    public int getMetricValue() {
        return metricValue;
    }

    public void setPrevMove(char prevMove) {
        this.prevMove = prevMove;
    }

    public void setIfVisited(boolean ifVisited) {
        this.ifVisited = ifVisited;
    }

    public Board getBoard() {
        return board;
    }

    public boolean checkIfCorrect() {
        return getBoard().checkIfCorrect();
    }

    public char getPrevMove() {
        return this.prevMove;
    }

    public boolean ifVisited() {
        return ifVisited;
    }

    public ArrayList<Node> getChildren() {
        return children;
    }

    public Node getLeftChild() throws CloneNotSupportedException {
        Board t = new Board((ArrayList<ArrayList<Integer>>) board.clone());
        if (t.moveL()) {
            return new Node(t, this, depth + 1);
        }
        return null;
    }

    public Node getRightChild() throws CloneNotSupportedException {
        Board t = new Board((ArrayList<ArrayList<Integer>>) board.clone());
        if (t.moveR()) {
            return new Node(t, this, depth + 1);
        }
        return null;
    }

    public Node getUpChild() throws CloneNotSupportedException {
        Board t = new Board((ArrayList<ArrayList<Integer>>) board.clone());
        if (t.moveU()) {
            return new Node(t, this, depth + 1);
        }
        return null;
    }

    public Node getDownChild() throws CloneNotSupportedException {
        Board t = new Board((ArrayList<ArrayList<Integer>>) board.clone());
        if (t.moveD()) {
            return new Node(t, this, depth + 1);
        }
        return null;
    }


    @Override
    public String toString() {
        String tmp = "";
        if (children == null)
            return getBoard().toString();
        tmp += "\nparent[" + getBoard().toString() + "], children: [";
        for (int i = 0; i < children.size(); i++) {
            tmp += children.get(i).toString();
        }
        tmp += "]";
        return tmp;
    }

    public Node getParent() {
        return parent;
    }


}
