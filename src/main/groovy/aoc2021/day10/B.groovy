package aoc2021.day10

import static java.util.Arrays.stream
import static java.util.stream.Collectors.toList

File file = new File("input")
List<String> inputLines = file.readLines()
List<String> openings = List.of("(","[","{", "<")

Map<String, Integer> points = Map.of(")", 1, "]", 2, "}", 3, ">", 4)
Map<String, String> closeToOpen = Map.of(")","(","]","[","}","{",">","<")
Map<String, String> openToClose = Map.of("(",")","[","]","{","}","<",">")
List<Long> scores = new ArrayList<>()
for (String inputLine : inputLines) {

    List<String> stack = new ArrayList<>()
    List<String> inputCharacters = stream(inputLine.split("")).collect(toList())

    boolean valid = true
    for (String inputCharacter : inputCharacters) {
        if (openings.contains(inputCharacter)) {
            stack.add(inputCharacter)
        } else if (stack.get(stack.size() -1) == closeToOpen.get(inputCharacter)) {
            stack.remove(stack.size() -1)
        } else {
            valid = false
            break
        }
    }
    if (valid) {
        stack = stack.reverse()
        Long score = 0;
        for (String remaining : stack) {
            print openToClose.get(remaining)
            score = (score * 5) + points.get(openToClose.get(remaining))
        }
        println " " + score
        scores.add(score)
    }
}

scores.sort()
println scores.get((scores.size() / 2).intValue())
