package aoc2021.day13

import static java.lang.Integer.parseInt

class Grid {

    String[][] grid

    Grid(List<String> input) {

        int maxWidth = 0
        int maxHeight = 0
        for (String inputLine : input) {
            int x = parseInt(inputLine.split(",")[0])
            int y = parseInt(inputLine.split(",")[1])
            if (x > maxWidth) {
                maxWidth = x
            }
            if (y > maxHeight) {
                maxHeight = y
            }
        }

        grid = new String[maxHeight+1][maxWidth+1]

        for (String inputLine : input) {
            int x = parseInt(inputLine.split(",")[0])
            int y = parseInt(inputLine.split(",")[1])
            grid[y][x] = '#'
        }
    }

    void fold(String direction, int point) {
        String[][] newGrid
        if (direction == "x") {
            newGrid = new String[grid.length][point]
        } else {
            newGrid = new String[point][grid[0].length]
        }
        if (direction == "x") {
            for (int i = 0; i < newGrid.length; i++) {
                for (int j = 0; j < newGrid[i].length; j++) {
                    newGrid[i][j] = grid[i][j]
                }
            }
        } else {
            for (int i = 0; i < newGrid.length; i++) {
                for (int j = 0; j < newGrid[i].length; j++) {
                    newGrid[i][j] = grid[i][j]
                }
            }
        }

        if (direction == "x") {
            for (int i = 0; i < grid.length; i++) {
                for (int j = point +1; j < grid[i].length; j++) {
                    if (grid[i][j] != null) {
                        newGrid[i][point - (j - point)] = grid[i][j]
                    }
                }
            }
        } else {
            for (int i = point +1; i < grid.length; i++) {
                for (int j = 0; j < grid[i].length; j++) {
                    if (grid[i][j] != null) {
                        newGrid[point - (i - point)][j] = grid[i][j]
                    }
                }
            }
        }

        grid = newGrid
    }

    void print() {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == null) {
                    print "."
                } else {
                    print grid[i][j]
                }
            }
            println ""
        }
        println ""
    }

    void points() {
        Integer points = 0
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] != null) {
                    points++
                }
            }
        }
        println points
    }
}
