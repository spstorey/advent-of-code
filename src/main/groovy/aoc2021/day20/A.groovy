package aoc2021.day20

File file = new File("input")
List<String> inputLines = file.readLines()
String iae = inputLines.get(0)

List<String> lines = new ArrayList<>()
for (int row = 2; row < inputLines.size(); row++) {
    lines.add(inputLines.get(row))
}

String[][] inputGrid = new String[lines.size() + 160][lines.get(0).length() + 160]
for (int row = 0; row < inputGrid.length; row++) {
    for (int column = 0; column < inputGrid[row].length; column++) {
        inputGrid[row][column] = "."
    }
}

for (int row = 0; row < lines.size(); row++) {
    for (int column = 0; column < lines.get(row).size(); column++) {
        inputGrid[row+80][column+80] = lines.get(row).substring(column, column+1)
    }
}

for (int turns = 1; turns <= 50; turns++) {
    inputGrid = enhance(inputGrid, iae)
}

println count(inputGrid)

static String[][] enhance(String[][] inputGrid, String iae) {

    String[][] output = new String[inputGrid.length][inputGrid[0].length]
    for (int row = 0; row < inputGrid.length; row++) {
        for (int column = 0; column < inputGrid[row].length; column++) {
            String binary =
                    get(inputGrid, row - 1, column - 1) +
                    get(inputGrid, row -1, column) +
                    get(inputGrid, row -1, column + 1) +
                    get(inputGrid, row, column - 1) +
                    get(inputGrid, row, column) +
                    get(inputGrid, row, column + 1) +
                    get(inputGrid, row + 1, column + - 1) +
                    get(inputGrid, row + 1, column) +
                    get(inputGrid, row + 1, column + 1)

            Integer result = Integer.parseInt(binary, 2)
            output[row][column] = iae.substring(result, result+1)
        }
    }
    return output
}

static String get(String[][] inputGrid, int row, int column) {

    try {
        if (inputGrid[row][column] == "#") {
            return "1"
        } else {
            return "0"
        }
    } catch (Exception e) {
        return "0"
    }
}

static Integer count(String[][] inputGrid) {

    int count
    for (int row = 0; row < inputGrid.length; row++) {
        for (int column = 0; column < inputGrid[row].length; column++) {
            if (inputGrid[row][column] == "#") {
                count++
            }
        }
    }
    return count
}

println "Done"
