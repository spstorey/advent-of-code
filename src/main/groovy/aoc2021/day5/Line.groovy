package aoc2021.day5

import static java.lang.Math.abs

class Line {
    int x1, x2, y1, y2

    Line(String x1, String y1, String x2, String y2) {
        this.x1 = Integer.parseInt(x1)
        this.y1 = Integer.parseInt(y1)
        this.x2 = Integer.parseInt(x2)
        this.y2 = Integer.parseInt(y2)
    }

    boolean isValid() {
        return x1 == x2 || y1 == y2 || abs(y1 - y2) == abs(x1 - x2)
    }

    List<String> getCoordinates() {
        List<String> coordinates = new ArrayList<>()
        if (x1 == x2) {             // vertical
            if (y1 < y2) {
                for (int i = y1; i <= y2; i++) {
                    coordinates.add(Integer.toString(x1) + "," + Integer.toString(i))
                }
            } else {
                for (int i = y2; i <= y1; i++) {
                    coordinates.add(Integer.toString(x1) + "," + Integer.toString(i))
                }
            }
        } else if (y1 == y2) {      // horizontal
            if (x1 < x2) {
                for (int i = x1; i <= x2; i++) {
                    coordinates.add(Integer.toString(i)+","+Integer.toString(y1))
                }
            } else {
                for (int i = x2; i <= x1; i++) {
                    coordinates.add(Integer.toString(i)+","+Integer.toString(y1))
                }
            }
        } else {                    // diagonal
            if (y1 < y2 && x1 < x2) {
                for (int i = 0; i <= y2 - y1; i++) {
                    coordinates.add(Integer.toString(x1+i) + "," + Integer.toString(y1+i))
                }
            } else if (y1 > y2 && x1 < x2) {
                for (int i = 0; i <= y1 - y2; i++) {
                    coordinates.add(Integer.toString(x1+i) + "," + Integer.toString(y1-i))
                }
            } else if (y1 < y2 && x1 > x2) {
                for (int i = 0; i <= y2 - y1; i++) {
                    coordinates.add(Integer.toString(x1-i) + "," + Integer.toString(y1+i))
                }
            } else if (y1 > y2 && x1 > x2) {
                for (int i = 0; i <= y1 - y2; i++) {
                    coordinates.add(Integer.toString(x2+i) + "," + Integer.toString(y2+i))
                }
            }
        }

        return coordinates
    }
}
