package aoc2021.day1

File file = new File("input")
List<String> lines = file.readLines()

int counter = 0
int prev = Integer.MAX_VALUE

for (String line : lines) {
    int cur = Integer.parseInt(line)
    if (cur > prev) {
        counter++
    }
    prev = cur
}
println counter
