package aoc2021.day11

class Grid {

    List<List<Integer>> octopuses = new ArrayList<>()
    Integer numberOfFlashes = 0;

    List<Point> getFlashes() {
        List<Point> flashes = new ArrayList<>()
        for (int rownNum = 0; rownNum < octopuses.size(); rownNum++) {
            for (int colNum = 0; colNum < octopuses.get(rownNum).size(); colNum++) {
                if (octopuses.get(rownNum).get(colNum) > 9) {
                    flashes.add(new Point(rownNum, colNum))
                }
            }
        }
        return flashes
    }

    boolean allFlashed() {
        Integer flashed = 0
        for (int rownNum = 0; rownNum < octopuses.size(); rownNum++) {
            for (int colNum = 0; colNum < octopuses.get(rownNum).size(); colNum++) {
                if (octopuses.get(rownNum).get(colNum) == 0) {
                    flashed++
                }
            }
        }
        return flashed == (octopuses.size() * octopuses.get(0).size())
    }

    void increment() {
        for (List<Integer> row : octopuses) {
            for (int i = 0; i < row.size(); i++) {
                row.set(i,row.get(i) + 1)
            }
        }
    }

    void incrementPoint(Point point) {
        if (octopuses.get(point.row).get(point.column) != 0) {
            octopuses.get(point.row).set(point.column, octopuses.get(point.row).get(point.column)+1)
        }
    }

    void flash(Point point) {
        numberOfFlashes++

        octopuses.get(point.row).set(point.column, 0)

        if (point.row > 0) {
            if (point.column > 0) {
                incrementPoint(new Point(point.row-1, point.column-1))
            }
            incrementPoint(new Point(point.row-1, point.column))
            if (point.column < octopuses.get(point.row-1).size()-1) {
                incrementPoint(new Point(point.row-1, point.column+1))
            }
        }
        if (point.column > 0) {
            incrementPoint(new Point(point.row, point.column-1))
        }
        if (point.column < octopuses.get(point.row).size()-1) {
            incrementPoint(new Point(point.row, point.column+1))
        }
        if (point.row < octopuses.size() -1) {
            if (point.column > 0) {
                incrementPoint(new Point(point.row+1, point.column-1))
            }
            incrementPoint(new Point(point.row+1, point.column))
            if (point.column < octopuses.get(point.row+1).size()-1) {
                incrementPoint(new Point(point.row+1, point.column+1))
            }
        }
    }

    void resetFlashes() {
        for (List<Integer> row : octopuses) {
            for (int i = 0; i < row.size(); i++) {
                if (row.get(i) > 9) {
                    row.set(i, 0)
                }
            }
        }
    }

    void print(Integer turn) {

        println ""
        println "------  " + turn + "  ------"
        println ""

        for (List<Integer> row : octopuses) {
            for (int i = 0; i < row.size(); i++) {
                if (row.get(i) == 0) {
                    print color(row.get(i), YELLOW)
                } else if (row.get(i) == 9) {
                    print color(row.get(i), RED)
                } else {
                    print color(row.get(i), NORMAL)
                }
            }
            println ""
        }
    }

    static final String NORMAL          = "\u001B[37m"
    static final String	RED             = "\u001B[31m"
    static final String	YELLOW          = "\u001B[33m"

    static String color(Integer text, String ansiValue) {
        ansiValue + text + NORMAL
    }
}
