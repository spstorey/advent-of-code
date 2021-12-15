package aoc2021.day15

import static java.lang.Integer.parseInt

File file = new File("input")
List<String> inputLines = file.readLines()

int[][] inputGrid = new int[inputLines.size()][inputLines.get(0).length()]
for (int row = 0; row < inputLines.size(); row++) {
    inputGrid[row] = Arrays.stream(inputLines.get(row).split("")).mapToInt(s -> parseInt(s)).toArray()
}
int inputWidth = inputGrid[0].length
int inputHeight = inputGrid.length

int repeats = 5

int[][] grid = new int[inputHeight * repeats][inputWidth * repeats]
for (int repeatY = 0; repeatY < repeats; repeatY++) {
    for (int repeatX = 0; repeatX < repeats; repeatX++) {
        int[][] incrementedGrid = incrementBy(inputGrid, repeatY+repeatX)

        int startX = (repeatX * inputWidth)
        int startY = (repeatY * inputHeight)

        for (int y = 0; y < incrementedGrid.length; y++) {
            for (int x = 0; x < incrementedGrid[0].length; x++) {
                grid[startY+y][startX+x] = incrementedGrid[y][x]
            }
        }
    }
}

int[][] distanceGrid = new int[grid.length][grid[0].length]
for (int y = 0; y < distanceGrid.length; y++) {
    for (int x = 0; x < distanceGrid[0].length; x++) {
        distanceGrid[y][x] = Integer.MAX_VALUE
    }
}
distanceGrid[0][0] = 0

PriorityQueue<Point> queue = new PriorityQueue<>(new Comparator<Point>() {
    @Override
    int compare(Point o1, Point o2) {
        return Integer.compare(o1.cost, o2.cost)
    }
})

queue.add(new Point(0,0,0))

while (!queue.isEmpty()) {

    Point currentPoint = queue.poll()

    List<Point> nextPoints = new ArrayList<>()
    if (currentPoint.x != grid.length - 1) {
        nextPoints.add(new Point(currentPoint.x+1, currentPoint.y, grid[currentPoint.y][currentPoint.x+1]))
    }
    if (currentPoint.x != 0) {
        nextPoints.add(new Point(currentPoint.x-1, currentPoint.y, grid[currentPoint.y][currentPoint.x-1]))
    }
    if (currentPoint.y != grid.length - 1) {
        nextPoints.add(new Point(currentPoint.x, currentPoint.y+1, grid[currentPoint.y+1][currentPoint.x]))
    }
    if (currentPoint.y != 0) {
        nextPoints.add(new Point(currentPoint.x, currentPoint.y-1, grid[currentPoint.y-1][currentPoint.x]))
    }

    for (Point nextPoint : nextPoints) {

        int distanceToMove = distanceGrid[currentPoint.y][currentPoint.x] + grid[nextPoint.y][nextPoint.x]
        int previousDistanceToNext = distanceGrid[nextPoint.y][nextPoint.x]

        if (distanceToMove < previousDistanceToNext) {
            queue.remove(nextPoint)
            distanceGrid[nextPoint.y][nextPoint.x] = distanceToMove
            queue.add(new Point(nextPoint.x, nextPoint.y, distanceToMove))
        }
    }
}

println distanceGrid[distanceGrid.length-1][distanceGrid[0].length-1]

static int[][] incrementBy(int[][] inputGrid, Integer increment) {
    int[][] result = inputGrid
    for (int i = 1; i <= increment; i++) {
        result = incrementBy1(result)
    }
    return result
}

static int[][] incrementBy1(int[][] inputGrid) {
    int[][] outputGrid = new int[inputGrid.length][inputGrid[0].length]
    for (int row = 0; row < inputGrid.length; row++) {
        for (int column = 0; column < inputGrid[row].length; column++) {
            if (inputGrid[row][column] + 1 > 9) {
                outputGrid[row][column] = inputGrid[row][column] - 8
            } else {
                outputGrid[row][column] = inputGrid[row][column] + 1
            }
        }
    }
    return outputGrid
}
