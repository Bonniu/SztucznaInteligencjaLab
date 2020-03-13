package pietnastka;

import java.util.ArrayList;

public class Node {

    private Node parent;
    private Board board;
    private boolean ifVisited;
    private ArrayList<Node> children;
    private char prevMove = ' ';


    public Node(Board board, Node parent) {
        this.board = board;
        this.ifVisited = false;
        this.children = new ArrayList<>();
        this.parent = parent;
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

    public void wykonajRuchBFS(String order) throws CloneNotSupportedException {
        for (int i = 0; i < order.toCharArray().length; i++) {
            if (order.toCharArray()[i] == 'L') {
                if (this.getLeftChild() != null && this.getPrevMove() != 'R') {
                    Node childNode = this.getLeftChild();
                    childNode.setPrevMove('L');
                    this.getChildren().add(childNode);
                }
            }
            if (order.toCharArray()[i] == 'U') {
                if (this.getUpChild() != null && this.getPrevMove() != 'D') {
                    Node childNode = this.getUpChild();
                    childNode.setPrevMove('U');
                    this.getChildren().add(childNode);
                }
            }
            if (order.toCharArray()[i] == 'R') {
                if (this.getRightChild() != null && this.getPrevMove() != 'L') {
                    Node childNode = this.getRightChild();
                    childNode.setPrevMove('R');
                    this.getChildren().add(childNode);
                }
            }
            if (order.toCharArray()[i] == 'D') {
                if (this.getDownChild() != null && this.getPrevMove() != 'U') {
                    Node childNode = this.getDownChild();
                    childNode.setPrevMove('D');
                    this.getChildren().add(childNode);
                }
            }
        }

    }
}
