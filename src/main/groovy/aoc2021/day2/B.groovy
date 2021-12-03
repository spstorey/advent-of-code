package main.groovy.aoc2021.day2

File file = new File("input")
List<String> lines = file.readLines()

int depth = 0
int position = 0
int aim = 0

for (String line : lines) {

    String direction = line.split(" ")[0]
    Integer distance = Integer.parseInt(line.split(" ")[1])

    switch (direction) {
        case "forward":
            position = position + distance
            depth = depth + (aim * distance)
            break
        case "up":
            aim = aim - distance
            break
        case "down":
            aim = aim + distance
            break
    }
}
println depth + " " + position;
println depth * position;

