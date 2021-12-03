package aoc2021.day4

File file = new File("input")
List<String> lines = file.readLines()

Integer width = lines.get(0).length()

int[] totals = new int[width]
for (String line : lines) {
    for (int i = 0; i < width; i++) {
        totals[i] += Integer.parseInt(line.charAt(i) as String)
    }
}

String gammaRate = ""
String epsilonRate = ""
for (int i = 0; i < totals.length; i++) {
    if (totals[i] >= (lines.size()/2)) {
        gammaRate = gammaRate + "1"
        epsilonRate = epsilonRate + "0"
    } else {
        gammaRate = gammaRate + "0"
        epsilonRate = epsilonRate + "1"
    }
}

print Integer.parseInt(gammaRate,2) * Integer.parseInt(epsilonRate,2)
