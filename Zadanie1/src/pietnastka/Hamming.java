package pietnastka;

public class Hamming extends Strategy {

    public Hamming(Node parentNode, Statistics statistics) {
        super(parentNode, statistics);
    }

    public boolean solveHamming(){
        System.out.println(getParentNode());
        return true;
    }
}
