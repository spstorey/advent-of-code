package aoc2021.day14

File file = new File("input")
List<String> inputLines = file.readLines()

String sequence = inputLines.get(0)

Map<String, String> replacements = new HashMap<>()
for (int i = 2; i < inputLines.size(); i++) {
    replacements.put(inputLines.get(i).split(" -> ")[0], inputLines.get(i).split(" -> ")[1])
}

Map<String, Long> pairs = new HashMap<>()
for (int c = 0; c < sequence.length() -1; c++) {
    addToMap(pairs, sequence.substring(c, c + 2), 1)
}

for (int turn = 0; turn < 40; turn++) {
    pairs = replace(pairs, replacements)
}

println maxMinusMinOccurrences(pairs, sequence)

static Map<String, Long> replace(Map<String, Long> pairs, Map<String, String> replacements) {
    Map<String, Long> results = new HashMap<>()

    for (Map.Entry<String, Long> entry : pairs) {
        if (replacements.containsKey(entry.key)) {
            addToMap(results, entry.key.substring(0,1) + replacements.get(entry.key), +entry.value)
            addToMap(results, replacements.get(entry.key) + entry.key.substring(1,2), +entry.value)
        } else {
            addToMap(results, entry.key, entry.value)
        }
    }
    return results
}

static void addToMap(Map<String, Long> results, String sequence, Long count) {
    Long currentCount = results.get(sequence)
    if (currentCount == null) {
        results.put(sequence, count)
    } else {
        results.put(sequence, currentCount + count)
    }
}

static Long maxMinusMinOccurrences(Map<String, Long> pairs, String originalSequence) {
    Map<String, Long> occurrences = new HashMap<>()
    for (Map.Entry<String, Long> entry : pairs) {
        addToMap(occurrences, entry.key.substring(0, 1), entry.value)
        addToMap(occurrences, entry.key.substring(1, 2), entry.value)
    }

    addToMap(occurrences, originalSequence.substring(0,1), 1)
    addToMap(occurrences, originalSequence.substring(originalSequence.length() -1), 1)

    Long max = occurrences.entrySet().stream().mapToLong(s->s.value).max().orElseThrow(NoSuchElementException::new)
    Long min = occurrences.entrySet().stream().mapToLong(s->s.value).min().orElseThrow(NoSuchElementException::new)

    return (max - min) / 2
}
