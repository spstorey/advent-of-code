package aoc2021.day1

File file = new File("input")
List<Integer> lines = file.readLines().stream().map(Integer::parseInt).collect()

int counter = 0

for (int i = 3; i < lines.size(); i++) {
    if (lines.get(i-3) + lines.get(i-2) + lines.get(i-1) <  lines.get(i-2) + lines.get(i-1) + lines.get(i-0)) {
        counter++
    }
}
println counter
