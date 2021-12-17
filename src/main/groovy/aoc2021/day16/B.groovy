package aoc2021.day16

import aoc2021.day16.b.Packet

File file = new File("input")
List<String> inputLines = file.readLines()

Packet packet = Packet.parseOuterPacket(inputLines.get(0))

println ""

println packet.literalValue
