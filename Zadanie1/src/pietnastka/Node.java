package pietnastka;

import java.util.ArrayList;

public class Node {

    private Board board;
    private boolean ifVisited;
    private ArrayList<Node> children;

    public Node(Board board, ArrayList<Node> children) {
        this.board = board;
        this.ifVisited = false;
        this.children = children;
    }

    public Node(Board board) {
        this.board = board;
        this.ifVisited = false;
        this.children = new ArrayList<>();
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

    public Node addChildren(String order, int depth) throws CloneNotSupportedException {
        System.out.println("depth: "+ depth);
        if (depth == 5)
            return null;
        for (int i = 0; i < order.toCharArray().length; i++) {
            if (order.toCharArray()[i] == 'L') {
                System.out.println("WESZLO DO L");
                if (getLeftChild() != null) {
                    Node childNode = getLeftChild();
                    getChildren().add(childNode);
                    System.out.println("child- " + childNode);
                    childNode.addChildren(order, depth + 1);
                }
            }
            if (order.toCharArray()[i] == 'U') {
                System.out.println("WESZLO DO U");
                if (getUpChild() != null) {
                    Node childNode = getUpChild();
                    getChildren().add(childNode);
                    System.out.println("child- " + childNode);
                    childNode.addChildren(order, depth + 1);
                }
            }
            if (order.toCharArray()[i] == 'R') {
                System.out.println("WESZLO DO R");
                if (getRightChild() != null) {
                    Node childNode = getRightChild();
                    getChildren().add(childNode);
                    System.out.println("child- " + childNode);
                    childNode.addChildren(order, depth + 1);
                }
            }
            if (order.toCharArray()[i] == 'D') {
                System.out.println("WESZLO DO D");
                if (getDownChild() != null) {
                    Node childNode = getDownChild();
                    getChildren().add(childNode);
                    System.out.println("child- " + childNode);
                    childNode.addChildren(order, depth + 1);
                }
            }

        }
        return null;
    }

    public boolean isIfVisited() {
        return ifVisited;
    }

    public ArrayList<Node> getChildren() {
        return children;
    }

    public Node getLeftChild() throws CloneNotSupportedException {
        Board t = new Board((ArrayList<ArrayList<Integer>>) board.clone());
        if (t.moveL()) {
            return new Node(t);
        }
        return null;
    }

    public Node getRightChild() throws CloneNotSupportedException {
        Board t = new Board((ArrayList<ArrayList<Integer>>) board.clone());
        if (t.moveR()) {
            return new Node(t);
        }
        return null;
    }

    public Node getUpChild() throws CloneNotSupportedException {
        Board t = new Board((ArrayList<ArrayList<Integer>>) board.clone());
        if (t.moveU()) {
            return new Node(t);
        }
        return null;
    }

    public Node getDownChild() throws CloneNotSupportedException {
        Board t = new Board((ArrayList<ArrayList<Integer>>) board.clone());
        if (t.moveD()) {
            return new Node(t);
        }
        return null;
    }


    @Override
    public String toString() {
        return board.toString();
    }
}
