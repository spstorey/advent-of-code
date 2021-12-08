package aoc2021.day7

import java.util.function.Function
import java.util.stream.Collectors

File file = new File("input")
List<String> inputLines = file.readLines()
Map<Integer, Long> positions = Arrays.stream(inputLines.get(0).split(","))
        .map(s -> Integer.parseInt(s))
        .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))

int maxPosition = positions.keySet().stream().mapToInt(v -> v).max().orElseThrow(NoSuchElementException::new)

int bestPosition = 0
int bestFuelCost = Integer.MAX_VALUE
for (int i = 0; i <= maxPosition; i++) {

    int totalFuel = 0;
    for (Map.Entry<Integer, Long> entry : positions) {
        totalFuel += fuelcost(Math.abs(entry.key - i)) * entry.value
    }
    if (totalFuel < bestFuelCost) {
        bestFuelCost = totalFuel
        bestPosition = i
    }
}

print bestPosition + " " + bestFuelCost


int fuelcost(int distance) {
    return (distance + 1) * distance / 2
}

