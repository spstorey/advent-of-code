package aoc2021.day11

import static java.util.Arrays.stream
import static java.util.stream.Collectors.toList

File file = new File("input")
List<String> inputLines = file.readLines()
Grid grid = new Grid()
for (String inputLine : inputLines) {
    grid.octopuses.add(stream(inputLine.split("")).map(s->Integer.parseInt(s)).collect(toList()))
}

boolean allFlashed = false
Integer turn = 0;
while (!allFlashed) {
    turn++
    grid.increment()

    List<Point> flashes = grid.getFlashes()

    while (flashes.size() > 0) {

        for (Point point : flashes) {
            grid.flash(point)
        }
        flashes = grid.getFlashes()
    }

    grid.resetFlashes()
    grid.print(turn)
    allFlashed = grid.allFlashed()
}

println turn

