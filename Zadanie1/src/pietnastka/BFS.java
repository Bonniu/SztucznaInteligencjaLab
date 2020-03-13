package pietnastka;

import java.util.ArrayList;
import java.util.List;

public class BFS {
    private String order;
    boolean isSolved;

    public BFS(String order) {
        this.order = order;
        this.isSolved = false;
    }

    public List<Node> poszukiwanieBFS(Node root) throws CloneNotSupportedException {
        List<Node> sciezkaDoRozw = new ArrayList<>();
        List<Node> wezlyDoOdwiedzenia = new ArrayList<>();
        List<Node> odwiedzoneWezly = new ArrayList<>();

        //dodaje pierwsza galez
        wezlyDoOdwiedzenia.add(root);

        while (!this.isSolved) {
            Node aktualnyWezel = wezlyDoOdwiedzenia.get(0);
            odwiedzoneWezly.add(aktualnyWezel);
            wezlyDoOdwiedzenia.remove(0);
            aktualnyWezel.wykonajRuchBFS(order);
            //dodaje kolejne wezly (ich dzieci do kolejki)
            for (int i = 0; i < aktualnyWezel.getChildren().size(); i++) {
                wezlyDoOdwiedzenia.add(aktualnyWezel.getChildren().get(i));
            }

            // zeby sprawdzac co chwila czy poprawne
            for (int i = 0; i < odwiedzoneWezly.size(); i++) {
                if (odwiedzoneWezly.get(i).getBoard().checkIfCorrect()) {
                    this.isSolved = true;
                    System.out.println("xd");
                    Node poprawnyNode = odwiedzoneWezly.get(i);
                    System.out.println(poprawnyNode.getBoard().toString());
                    System.out.println("xd1");
                    znajdz_droge(sciezkaDoRozw, poprawnyNode);
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
    public void znajdz_droge(List<Node> sciezka, Node n) {
        Node aktualnyNode = n;
        sciezka.add(aktualnyNode);

        while (aktualnyNode.getParent() != null) {
            aktualnyNode = aktualnyNode.getParent();
            sciezka.add(aktualnyNode);
        }
        System.out.println("TRASA DO ROZWIAZANIA OD KONCA");
        for (int i = 0; i < sciezka.size(); i++) {
            System.out.println(sciezka.get(i).getPrevMove());
        }
    }
}
