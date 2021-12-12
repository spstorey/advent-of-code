package aoc2021.day12

import static java.util.stream.Collectors.toList

File file = new File("input")
List<String> inputLines = file.readLines()

List<Path> paths = new ArrayList<>()

for (String inputLine : inputLines) {
    if (inputLine.contains("start")) {
        Path path = new Path(inputLine)
        paths.add(path)
    }
}

List<Path> ended = new ArrayList<>()
while (paths.size() > 0) {
    List<Path> nextPaths = new ArrayList<>()
    for (Path path : paths) {
        List<String> nextLinks = inputLines.stream()
                .filter(s->!s.contains("start"))
                .filter(s->s.contains(path.lastCave()))
                .collect(toList())
        for (String nextLink : nextLinks) {

            Path newPath = new Path(nextLink, path.routes)
            if (!newPath.visitedSmallCaveThreeTimes()) {
                if (newPath.atEnd()) {
                    ended.add(newPath)
                } else if (newPath.routes.size() > path.routes.size()) {
                    nextPaths.add(newPath)
                }
            }
        }
    }
    paths = nextPaths
}

println ended.size()
