package aoc2021.day5

File file = new File("input")
List<String> inputLines = file.readLines()

List<Line> lines = new ArrayList<>()
for (String inputLine : inputLines) {

    inputLine = inputLine.replaceAll(' ', '').replace('->', ',')
    String[] values = inputLine.split(",")
    Line line = new Line(values[0],values[1],values[2], values[3])
    lines.add(line)
}

Map<String, Integer> occurrences = new HashMap<>()
for (Line line : lines) {
    if (line.isValid()) {
        List<String> coordinates = line.getCoordinates()
        for (String coordinate : coordinates) {
            if (occurrences.containsKey(coordinate)) {
                occurrences.put(coordinate, occurrences.get(coordinate) + 1)
            } else {
                occurrences.put(coordinate, 1)
            }
        }
    }
}

int counter = 0
Map<String, Integer> poi = new HashMap<>()
for (Map.Entry<String, Integer> entry : occurrences.entrySet()) {
    if (entry.value > 1) {
        poi.put(entry.key, entry.value)
        counter++
    }
}

println counter
println " 0123456789"
for (int i = 0; i < 10; i++) {
    print Integer.toString(i)
    for (int j = 0; j < 10; j++) {
        if (occurrences.containsKey(Integer.toString(j)+"," + Integer.toString(i))) {
            print occurrences.get(Integer.toString(j)+"," + Integer.toString(i))
        } else {
            print '.'
        }
    }
    println ""
}
