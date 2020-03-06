package pietnastka;

import java.util.ArrayList;

public class Node {

    private Board board;
    private Node parent;
    private boolean ifVisited;
    private ArrayList<Node> children;

    public Node(Board board, Node parent) {
        this.board = board;
        this.parent = parent;
        this.ifVisited = false;
        this.children = new ArrayList<>();
    }

    public void setIfVisited(boolean ifVisited) {
        this.ifVisited = ifVisited;
    }

    public Board getBoard() {
        return board;
    }

    public Node getParent() {
        return parent;
    }

    public boolean checkIfCorrect() {
        return getBoard().checkIfCorrect();
    }

    public void addChildren(String order, Node parent, int depth) throws CloneNotSupportedException {
        if (depth == 10)
            return;
        System.out.println(parent);
        for (int i = 0; i < order.toCharArray().length; i++) {
            if (order.toCharArray()[i] == 'L') {
                if (getLeftChild() != null) {
                    parent.getChildren().add(getLeftChild());
                    addChildren(order, this.getChildren().get(getChildren().size() - 1), depth + 1);
                }
            }
            if (order.toCharArray()[i] == 'U') {
                if (getUpChild() != null) {
                    parent.getChildren().add(getUpChild());
                    addChildren(order, this.getChildren().get(getChildren().size() - 1), depth + 1);
                }
            }
            if (order.toCharArray()[i] == 'R') {
                if (getRightChild() != null) {
                    parent.getChildren().add(getRightChild());
                    addChildren(order, this.getChildren().get(getChildren().size() - 1), depth + 1);
                }
            }
            if (order.toCharArray()[i] == 'D') {
                if (getDownChild() != null) {
                    parent.getChildren().add(getDownChild());
                    addChildren(order, this.getChildren().get(getChildren().size() - 1), depth + 1);
                }
            }
        }
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
            return new Node(t, this);
        }
        return null;
    }

    public Node getRightChild() throws CloneNotSupportedException {
        Board t = new Board((ArrayList<ArrayList<Integer>>) board.clone());
        if (t.moveR()) {
            return new Node(t, this);
        }
        return null;
    }

    public Node getUpChild() throws CloneNotSupportedException {
        Board t = new Board((ArrayList<ArrayList<Integer>>) board.clone());
        if (t.moveU()) {
            return new Node(t, this);
        }
        return null;
    }

    public Node getDownChild() throws CloneNotSupportedException {
        Board t = new Board((ArrayList<ArrayList<Integer>>) board.clone());
        if (t.moveD()) {
            return new Node(t, this);
        }
        return null;
    }

    @Override
    public String toString() {
        String tmp = "Node{";
        tmp += "board=" + board;
        if (parent != null)
            tmp += ", parent=" + parent.getBoard();
        else
            tmp += ", parent=null";
        tmp += ", ifVisited=" + ifVisited +
                '}';
        return tmp;
    }
}
