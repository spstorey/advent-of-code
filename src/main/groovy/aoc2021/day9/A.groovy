package aoc2021.day9

import static java.util.Arrays.stream
import static java.util.stream.Collectors.toList

File file = new File("input")
List<String> inputLines = file.readLines()

List<List<Integer>> grid = new ArrayList<>()
for (String inputLine : inputLines) {
    grid.add(stream(inputLine.split("(?!^)")).map(s->Integer.parseInt(s)).collect(toList()))
}

List<Integer> lowPoints = new ArrayList<>()
for (int row = 0; row < grid.size(); row++) {

    for (int column = 0; column < grid.get(0).size(); column++) {

        List<Point> surroundingPoints = surroundingPoints(new Point(row,column, 0), grid)
        List<Integer> heights = surroundingPoints.stream().map(s -> s.height).collect(toList()).sort()
        if (grid.get(row).get(column) < heights.get(0)) {
            lowPoints.add(grid.get(row).get(column))
        }
    }
}

println lowPoints.stream().reduce(0, Integer::sum) + lowPoints.size()

static List<Point> surroundingPoints(Point origin, List<List<Integer>> grid) {

    List<Point> surroundingPoints = new ArrayList<>()

    List<Integer> aboveLine = null
    if (origin.row > 0) {
        aboveLine = grid.get(origin.row-1)
    }
    List<Integer> currentLine = grid.get(origin.row)
    List<Integer> belowLine = null
    if (origin.row < grid.size() -1) {
        belowLine = grid.get(origin.row+1)
    }
    if (aboveLine != null) {
        surroundingPoints.add(new Point(origin.row-1, origin.column, aboveLine.get(origin.column)))
    }
    if (origin.column > 0) {
        surroundingPoints.add(new Point(origin.row, origin.column-1, currentLine.get(origin.column-1)))
    }
    if (origin.column < currentLine.size() - 1) {
        surroundingPoints.add(new Point(origin.row, origin.column+1, currentLine.get(origin.column+1)))
    }
    if (belowLine != null) {
        surroundingPoints.add(new Point(origin.row+1, origin.column, belowLine.get(origin.column)))
    }
    return surroundingPoints
}
