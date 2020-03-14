package pietnastka;

public abstract class Strategy {
    private Node parentNode;
    private Statistics statistics;

    public Strategy(Node parentNode, Statistics statistics) {
        this.parentNode = parentNode;
        this.statistics = statistics;
    }

    public Node getParentNode() {
        return parentNode;
    }

    public Statistics getStatistics() {
        return statistics;
    }

}
