package aoc2021.day16

import aoc2021.day16.a.Packet

File file = new File("input")
List<String> inputLines = file.readLines()

List<Packet> packets = Packet.parse(inputLines.get(0))

println packets.forEach(s -> println s.getVersion())
println packets.stream().map(s -> s.getVersion()).reduce(0, Integer::sum)
