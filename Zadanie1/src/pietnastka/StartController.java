package pietnastka;


public class StartController {
    private String method = "";
    private String orderHeuristics = "";
    private String loadFileName = "";
    private String saveFileName = "";
    private String additionalFileName = "";

    public StartController(String[] args) {
        if (args.length < 5)
            throw new ExceptionInInitializerError("Brak 5 argumentow");
        else {
            this.method = args[0];
            this.orderHeuristics = args[1];
            this.loadFileName = args[2];
            this.saveFileName = args[3];
            this.additionalFileName = args[4];
        }
    }

    public String getMethod() {
        return method;
    }

    public String getOrderHeuristics() {
        return orderHeuristics;
    }

    public String getLoadFileName() {
        return loadFileName;
    }

    public String getSaveFileName() {
        return saveFileName;
    }

    public String getAdditionalFileName() {
        return additionalFileName;
    }

    @Override
    public String toString() {
        return "StartController{" +
                "method='" + method + '\'' +
                ", orderHeuristics='" + orderHeuristics + '\'' +
                ", loadFileName='" + loadFileName + '\'' +
                ", saveFileName='" + saveFileName + '\'' +
                ", additionalFileName='" + additionalFileName + '\'' +
                '}';
    }
}
