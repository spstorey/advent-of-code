package aoc2021.day7


import java.util.stream.Collectors

File file = new File("input")
List<String> inputLines = file.readLines()
int[] positions = Arrays.stream(inputLines.get(0).split(","))
        .map(s -> Integer.parseInt(s))
        .collect(Collectors.toList())
        .toArray(new Integer[0])

Arrays.sort(positions)

int middle
if (positions.length % 2 == 0) {
    middle = positions.size() / 2
} else {
    middle = (positions.size() - 1) / 2
}

int median = (int) positions[middle];

print Arrays.stream(positions)
        .map(p -> Math.abs(p-median))
        .reduce(0, Integer::sum)
