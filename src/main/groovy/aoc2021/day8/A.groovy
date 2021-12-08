package aoc2021.day8

import java.util.stream.Collectors

File file = new File("input")
List<String> inputLines = file.readLines()
List<Integer> uniqueSizes = List.of(2,3,4,7)

int occurances = 0;
for (String inputLine : inputLines) {
    List<String> outputs = Arrays.stream(inputLine.substring(inputLine.indexOf("|")+1).split(" ")).filter(s -> "" != s).collect(Collectors.toList())

    for (String digit : outputs) {
        if (uniqueSizes.contains(digit.length())) {
            occurances++
        }
    }
}
println occurances
