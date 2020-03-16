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
        List<Node> wezlyDoOdwiedzenia = new ArrayList<>();
        List<Node> odwiedzoneWezly = new ArrayList<>();

        //dodaje pierwsza galez
        wezlyDoOdwiedzenia.add(getParentNode());

        while (!getStatistics().solved) {
            Node aktualnyWezel = wezlyDoOdwiedzenia.get(0);
            odwiedzoneWezly.add(aktualnyWezel);
            wezlyDoOdwiedzenia.remove(0);
            moveBFS(aktualnyWezel, getOrder());
            //dodaje kolejne wezly (ich dzieci do kolejki)
            wezlyDoOdwiedzenia.addAll(aktualnyWezel.getChildren());

            // zeby sprawdzac co chwila czy poprawne
            for (int i = 0; i < odwiedzoneWezly.size(); i++) {
                if (odwiedzoneWezly.get(i).getBoard().checkIfCorrect()) {
                    getStatistics().solved = true;
                    System.out.println("xd");
                    Node poprawnyNode = odwiedzoneWezly.get(i);
                    System.out.println(poprawnyNode.getBoard().toString());
                    System.out.println("xd1");
                    findWay(poprawnyNode);
                }
            }

            System.out.println("---- RODZIC:");
            System.out.println("ilosc dzieci:" + aktualnyWezel.getChildren().size());
            System.out.println("wezly do odwiedzenia:" + wezlyDoOdwiedzenia.size());
            System.out.println("odwiedzone wezly: " + odwiedzoneWezly.size());
        }
//        System.out.println("odwiedzone!!!!");
//        for (int i = 0; i < odwiedzoneWezly.size(); i++) {
//            System.out.println(odwiedzoneWezly.get(i).getBoard().toString());
//        }
        return odwiedzoneWezly;
    }

    //znajduje droge do rozwiazania
    public void findWay(Node n) {
        Node aktualnyNode = n;
        while (aktualnyNode.getParent() != null) {
            this.getStatistics().solution += aktualnyNode.getPrevMove();
            aktualnyNode = aktualnyNode.getParent();
        }
        getStatistics().reverseSolution();
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
