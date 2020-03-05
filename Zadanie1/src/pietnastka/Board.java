package pietnastka;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Board {
    private ArrayList<ArrayList<Integer>> tab;
    private int rows;
    private int columns;


    public Board(int rows, int columns, boolean filled) {
        this.rows = rows;
        this.columns = columns;
        this.tab = new ArrayList<>();
        if (filled) {
            for (int j = 0; j < rows; j++) {
                ArrayList<Integer> temp = new ArrayList<>(rows);
                for (int i = 1; i <= columns; i++) {
                    if (i + columns * j != columns * rows)
                        temp.add(i + columns * j);
                    else
                        temp.add(0);
                }
                tab.add(temp);
            }
        }
    }

    public Board() {

    }

    public ArrayList<ArrayList<Integer>> getTab() {
        return tab;
    }

    public int getRows() {
        return rows;
    }

    public int getColumns() {
        return columns;
    }

    public void loadTabFromFile(String fileName) {
        File file = new File(fileName);
        Scanner in = null;
        try {
            in = new Scanner(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        assert in != null;
        String size = in.nextLine();
        int rows = Integer.parseInt(size.split(" ")[0]);
        int columns = Integer.parseInt(size.split(" ")[1]);
        ArrayList<ArrayList<Integer>> newTab = new ArrayList<>();
        while (in.hasNext()) {
            String tempTab = in.nextLine();
            try {
                newTab.add(createArrayFromString(tempTab));
            } catch (NumberFormatException e) {
                e.printStackTrace();
                return;
            }
        }
        this.tab = newTab;
        this.rows = rows;
        this.columns = columns;
    }

    private ArrayList<Integer> createArrayFromString(String s) throws NumberFormatException {
        ArrayList<Integer> tmp = new ArrayList<>();
        try {
            for (int i = 0; i < s.split(" ").length; i++) {
                tmp.add(Integer.valueOf(s.split(" ")[i]));
            }
        } catch (NumberFormatException e) {
            throw new NumberFormatException("Blad podczas wczytywania pliku");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return tmp;
    }

    public void moveD() // w dol czyli nie moze byc ostatni rzad
    {
        if (find0()[0] != getRows() - 1) {
            zamienMiejsca(find0()[0], find0()[1], find0()[0] + 1, find0()[1]);
        }
    }

    public void moveU() // do gory czyli nie moze byc pierwszy rzad
    {
        if (find0()[0] != 0) {
            zamienMiejsca(find0()[0], find0()[1], find0()[0] - 1, find0()[1]);
        }
    }

    public void moveL() // w lewo czyli nie moze byc pierwsza kolumna
    {
        if (find0()[1] != 0) {
            zamienMiejsca(find0()[0], find0()[1], find0()[0], find0()[1] - 1);
        }
    }

    public void moveR() // w prawo czyli nie moze byc ostatnia kolumna
    {
        if (find0()[1] != getRows() + 1) {
            zamienMiejsca(find0()[0], find0()[1], find0()[0], find0()[1] + 1);
        }
    }


    private int[] find0() {
        int[] tmp = new int[2];
        for (int i = 0; i < getRows(); i++) {
            for (int j = 0; j < getColumns(); j++) {
                if (getTab().get(i).get(j) == 0) {
                    tmp[0] = i;
                    tmp[1] = j;
                    return tmp;
                }
            }
        }
        return tmp;
    }

    private void zamienMiejsca(int x1, int y1, int x2, int y2) //zamienia miejscami
    {
        int tmp = getTab().get(x1).get(y1);
        getTab().get(x1).set(y1, getTab().get(x2).get(y2));
        getTab().get(x2).set(y2, tmp);
    }

    @Override
    public String toString() {
        StringBuilder tmp = new StringBuilder();
        tmp.append("---").append(getRows()).append("---").append(getColumns()).append("-------------\n");
        for (ArrayList<Integer> integers : tab) {
            tmp.append(integers.toString()).append("\n");
        }
        tmp.append("-------------------");
        return tmp.toString();
    }
}
