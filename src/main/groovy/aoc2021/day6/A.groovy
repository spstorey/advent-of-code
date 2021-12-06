package aoc2021.day6

import java.util.function.Function
import java.util.stream.Collectors

File file = new File("input")
List<String> inputLines = file.readLines()
Map<String, Long> laterns = Arrays.stream(inputLines.get(0).split(","))
.collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))

for (int i = 0; i < 256; i++) {

    Map<String, Long> nextLaterns = new HashMap<>();
    for (Map.Entry<String, Long> entry : laterns.entrySet()) {

        if (entry.key == "0") {
            nextLaterns.put("8", entry.value)
            nextLaterns.put("6", entry.value)
        } else {
            String nextKey = Long.toString(Long.parseLong(entry.key) -1)
            Long preExisting = nextLaterns.get(nextKey)
            if (preExisting == null) {
                preExisting = 0
            }
            nextLaterns.put(nextKey, preExisting + entry.value)
        }
    }
    laterns = nextLaterns
}

print laterns.entrySet().stream().map(s->s.value).reduce(0, Long::sum)
