package aoc2021.day3

File file = new File("input")
List<String> lines = file.readLines()

int oxygen = find(lines, true)
int co2 = find(lines, false)

println oxygen * co2

int find(List<String> input, boolean mostFrequent) {
    List<String> remainingInput = new ArrayList<>(input)
    int column = 0
    while (remainingInput.size() > 1) {
        int mostCommon = getMostCommon(remainingInput, column)
        if (mostFrequent) {
            remainingInput = filterForValue(remainingInput, column, mostCommon)
        } else {
            remainingInput = filterForValue(remainingInput, column, mostCommon == 1 ? 0 : 1)
        }
        column++
    }
    return Long.parseLong(remainingInput.get(0), 2)
}

static int getMostCommon(List<String> input, int column) {
    int total = 0;
    for (String line : input) {
        total += Integer.parseInt(line.charAt(column) as String)
    }
    if (total >= (input.size()/2)) {
        return 1
    } else {
        return 0
    }
}

static List<String> filterForValue(List<String> input, int column, int value) {
    List<String> result = new ArrayList<>()
    for (String line : input) {
        if (line.charAt(column) == Character.forDigit(value, 10)) {
            result.add(line)
        }
    }
    return result
}
