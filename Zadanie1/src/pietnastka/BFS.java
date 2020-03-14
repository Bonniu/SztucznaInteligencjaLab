package pietnastka;

import java.util.ArrayList;
import java.util.List;

public class BFS {
    private String order;
    private Statistics statistics;

    public BFS(String order, Statistics statistics) {
        this.order = order;
        this.statistics = statistics;
    }

    public List<Node> solveBFS(Node root) throws CloneNotSupportedException {
        List<Node> sciezkaDoRozw = new ArrayList<>();
        List<Node> wezlyDoOdwiedzenia = new ArrayList<>();
        List<Node> odwiedzoneWezly = new ArrayList<>();

        //dodaje pierwsza galez
        wezlyDoOdwiedzenia.add(root);

        while (!statistics.solved) {
            Node aktualnyWezel = wezlyDoOdwiedzenia.get(0);
            odwiedzoneWezly.add(aktualnyWezel);
            wezlyDoOdwiedzenia.remove(0);
            moveBFS(aktualnyWezel, order);
            //dodaje kolejne wezly (ich dzieci do kolejki)
            wezlyDoOdwiedzenia.addAll(aktualnyWezel.getChildren());

            // zeby sprawdzac co chwila czy poprawne
            for (int i = 0; i < odwiedzoneWezly.size(); i++) {
                if (odwiedzoneWezly.get(i).getBoard().checkIfCorrect()) {
                    statistics.solved = true;
                    System.out.println("xd");
                    Node poprawnyNode = odwiedzoneWezly.get(i);
                    System.out.println(poprawnyNode.getBoard().toString());
                    System.out.println("xd1");
                    findWay(sciezkaDoRozw, poprawnyNode);
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
    public void findWay(List<Node> sciezka, Node n) {
        Node aktualnyNode = n;
        sciezka.add(aktualnyNode);

        while (aktualnyNode.getParent() != null) {
            aktualnyNode = aktualnyNode.getParent();
            sciezka.add(aktualnyNode);
        }
        System.out.println("TRASA DO ROZWIAZANIA OD KONCA");

        for (int i = 0; i < sciezka.size(); i++) {
            this.statistics.solution += sciezka.get(i).getPrevMove();
        }
        statistics.reverseSolution();
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
