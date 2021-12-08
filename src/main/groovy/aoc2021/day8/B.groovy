package aoc2021.day8

import static java.util.Arrays.stream
import static java.util.stream.Collectors.toList

File file = new File("input")
List<String> inputLines = file.readLines()

Long total = 0

for (String inputLine : inputLines) {

    List<String> inputs = stream(inputLine.substring(0, inputLine.indexOf("|")).split(" "))
            .filter(s -> "" != s)
            .map(s -> order(s))
            .sorted(Comparator.comparingInt(String::length))
            .collect(toList())

    List<String> outputs = stream(inputLine.substring(inputLine.indexOf("|")+1).split(" "))
            .filter(s -> "" != s)
            .map(s -> ordered(s))
            .collect(toList())

    Map<String, Integer> answers = new HashMap<>()
    answers.put(inputs.get(0), 1)
    answers.put(inputs.get(1), 7)
    answers.put(inputs.get(2), 4)
    answers.put(inputs.get(9), 8)

    for (String input : inputs) {
        if (input.length() == 5) {
            if (contains(input, inputs.get(0))) {
                answers.put(input, 3)
            } else if (!contains(input, inputs.get(0)) && overlap(input, inputs.get(2)) == 3) {
                answers.put(input, 5)
            } else {
                answers.put(input, 2)
            }
        } else if (input.length() == 6) {
            if (!contains(input, inputs.get(0))) {
                answers.put(input, 6)
            } else if (contains(input, inputs.get(2))) {
                answers.put(input, 9)
            } else {
                answers.put(input, 0)
            }
        }
    }

    total += Long.parseLong(outputs.stream().map(s -> answers.get(s)).collect(toList()).join())
}
println total

static boolean contains(String input, String signal) {
    List<String> inputChars = stream(input.split("(?!^)")).collect(toList())
    List<String> signalChars = stream(signal.split("(?!^)")).collect(toList())
    return inputChars.containsAll(signalChars)
}

static String order(String input) {
    return stream(input.split("(?!^)")).collect(toList()).sort().join()
}

static int overlap(String input1, String input2) {
    Set<String> inputChars = stream(input1.split("(?!^)")).collect(toList())
    inputChars.retainAll( stream(input2.split("(?!^)")).collect(toList()))
    return inputChars.size()
}
