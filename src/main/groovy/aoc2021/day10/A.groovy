package aoc2021.day10

import static java.util.Arrays.stream
import static java.util.stream.Collectors.toList

File file = new File("input")
List<String> inputLines = file.readLines()
List<String> openings = List.of("(","[","{", "<")
List<String> stack = new ArrayList<>()
Map<String, Integer> points = Map.of(")", 3, "]", 57, "}", 1197, ">", 25137)
Map<String, String> pairings = Map.of(")","(","]","[","}","{",">","<")
Integer score = 0;
for (String inputLine : inputLines) {

    List<String> inputCharacters = stream(inputLine.split("")).collect(toList())

    for (String inputCharacter : inputCharacters) {
        if (openings.contains(inputCharacter)) {
            stack.add(inputCharacter)
        } else if (stack.get(stack.size() -1) == pairings.get(inputCharacter)) {
            stack.remove(stack.size() -1)
        } else {
            println inputLine + "    " + inputCharacter
            score += points.get(inputCharacter)
            break
        }
    }
}
println score
