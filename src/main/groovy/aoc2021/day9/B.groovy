package aoc2021.day9

import static java.util.Arrays.stream
import static java.util.stream.Collectors.toList

File file = new File("input")
List<String> inputLines = file.readLines()

List<List<Integer>> grid = new ArrayList<>()
for (String inputLine : inputLines) {
    grid.add(stream(inputLine.split("(?!^)")).map(s->Integer.parseInt(s)).collect(toList()))
}

List<Point> lowPoints = new ArrayList<>()
for (int row = 0; row < grid.size(); row++) {
    for (int column = 0; column < grid.get(0).size(); column++) {
        List<Point> surroundingPoints = surroundingPoints(new Point(row, column,0), grid)

        List<Integer> heights = surroundingPoints.stream().map(s -> s.height).collect(toList()).sort()
        if (grid.get(row).get(column) < heights.get(0)) {
            lowPoints.add(new Point(row, column, grid.get(row).get(column)))
        }
    }
}
List<Integer> basinSizes = new ArrayList<>()
for (Point lowPoint : lowPoints) {
    Set<Point> basin = calcBasin(lowPoint, grid)
    basinSizes.add(basin.size()+1)
}

basinSizes = basinSizes.sort().reverse()

println basinSizes.get(0) * basinSizes.get(1) * basinSizes.get(2)


static Set<Point> calcBasin(Point point, List<List<Integer>> grid) {
    Set<Point> higherPoints = new HashSet<>()
    calcBasin(point, grid, higherPoints)
    return higherPoints
}

static void calcBasin(Point point, List<List<Integer>> grid, Set<Point> higherPoints) {
    List<Point> surroundingPoints = surroundingPoints(point, grid)
    for (Point surroundingPoint : surroundingPoints) {
        if (surroundingPoint.height > point.height && surroundingPoint.height < 9) {
            higherPoints.add(surroundingPoint)
            calcBasin(surroundingPoint, grid, higherPoints)
        }
    }
}

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
