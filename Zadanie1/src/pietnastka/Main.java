package pietnastka;

public class Main {


    public static void main(String[] args) {
        System.out.println("eluwa");
        StartController c;
        try {
            c = new StartController(args);
        } catch (ExceptionInInitializerError exception) {
            exception.printStackTrace();
            return;
        }
        System.out.println(c.toString());
        Board t = new Board(3, 3, true);
        t.loadTabFromFile(c.getLoadFileName());
        System.out.println(t.toString());
        t.moveD();
        System.out.println(t.toString());
        t.moveL();
        System.out.println(t.toString());
        t.moveU();
        System.out.println(t.toString());
        t.moveR();
        System.out.println(t.toString());

    }


}
