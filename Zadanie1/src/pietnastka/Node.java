package pietnastka;

import java.util.ArrayList;

public class Node {

    private Board board;
    private boolean ifVisited;
    private ArrayList<Node> children;
    private char prevPos = ' ';

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

    public void setPrevPos(char prevPos) {
        this.prevPos = prevPos;
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
        System.out.println("depth: " + depth);
        if (depth == 3)
            return null;
        for (int i = 0; i < order.toCharArray().length; i++) {
            if (order.toCharArray()[i] == 'L') {
                if (getLeftChild() != null && getPrevPos() != 'L') {
                    Node childNode = getLeftChild();
                    childNode.setPrevPos('R');
                    getChildren().add(childNode);
                    childNode.addChildren(order, depth + 1);
                }
            }
            if (order.toCharArray()[i] == 'U') {
                if (getUpChild() != null  && getPrevPos() != 'U') {
                    Node childNode = getUpChild();
                    childNode.setPrevPos('D');
                    getChildren().add(childNode);
                    childNode.addChildren(order, depth + 1);
                }
            }
            if (order.toCharArray()[i] == 'R') {
                if (getRightChild() != null && getPrevPos() != 'R') {
                    Node childNode = getRightChild();
                    childNode.setPrevPos('L');
                    getChildren().add(childNode);
                    childNode.addChildren(order, depth + 1);
                }
            }
            if (order.toCharArray()[i] == 'D') {
                if (getDownChild() != null  && getPrevPos() != 'D') {
                    Node childNode = getDownChild();
                    childNode.setPrevPos('U');
                    getChildren().add(childNode);
                    childNode.addChildren(order, depth + 1);
                }
            }

        }
        return null;
    }

    private char getPrevPos() {
        return this.prevPos;
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
}
