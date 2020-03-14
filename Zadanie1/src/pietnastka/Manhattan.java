package pietnastka;

public class Manhattan extends Strategy{

    public Manhattan(Node parentNode, Statistics statistics) {
        super(parentNode, statistics);
    }

    public boolean solveManhattan(){
        System.out.println(getParentNode());
        return true;
    }
}
