package aoc2021.day13

File file = new File("input")
List<String> inputLines = file.readLines()

Grid grid = new Grid(inputLines)
grid.fold("x",655)
grid.fold("y",447)
grid.fold("x",327)
grid.fold("y",223)
grid.fold("x",163)
grid.fold("y",111)
grid.fold("x",81)
grid.fold("y",55)
grid.fold("x",40)
grid.fold("y",27)
grid.fold("y",13)
grid.fold("y",6)

grid.print()
grid.points()
