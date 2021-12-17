package aoc2021.day16.b

import org.apache.commons.lang3.StringUtils
import org.apache.commons.lang3.builder.ReflectionToStringBuilder
import org.apache.commons.lang3.builder.ToStringStyle

class Packet {

    Integer typeID
    Long literalValue
    Integer lengthTypeID
    Integer totalLength
    String binary
    Integer endPosition
    List<Packet> packets = new ArrayList<>()

    boolean isLiteral() {
        return typeID == 4
    }

    boolean hasTotalLength() {
        return lengthTypeID == 0
    }

    @Override
    String toString() {
        return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE)
                .replaceAll(",", ", ")
                .replaceAll("\\[", " [ ")
                .replaceAll("]", " ]")
    }

    static Packet parseOuterPacket(String message) {
        String binary = toBinary(message)
        List<Packet> outerPacket = parsePackets(binary, 0, 1, Integer.MAX_VALUE)
        return outerPacket.get(0)
    }

    static List<Packet> parsePackets(String binary, int start, int numberOfPackets, int maxPosition) {

        List<Packet> packets = new ArrayList<>()
        int position = start
        String remainingBinary = binary.substring(position)
        //try {
        while (packets.size() < numberOfPackets && position != maxPosition) {
            Packet packet = new Packet()
            position += 3
            packet.typeID = Integer.parseInt(binary.substring(position, position + 3), 2)
            position += 3

            if (packet.isLiteral()) {
                StringBuffer literal = new StringBuffer()
                while (binary.substring(position, position + 1) != "0") {
                    literal.append(binary.substring(position + 1, position + 5))
                    position += 5
                }
                literal.append(binary.substring(position + 1, position + 5))
                position += 5
                packet.literalValue = Long.parseLong(literal.toString(), 2)
            } else {
                packet.lengthTypeID = Integer.parseInt(binary.substring(position, position + 1), 2)
                position += 1

                if (packet.hasTotalLength()) {
                    packet.totalLength = Integer.parseInt(binary.substring(position, position + 15), 2)
                    position += 15
                    packet.packets.addAll(parsePackets(binary, position, Integer.MAX_VALUE, position + packet.totalLength))
                } else {
                    int numberOfSubPackets = Integer.parseInt(binary.substring(position, position + 11), 2)
                    position += 11
                    packet.packets.addAll(parsePackets(binary, position, numberOfSubPackets, Integer.MAX_VALUE))
                }

                position = packet.packets.get(packet.packets.size-1).endPosition

                switch(packet.typeID) {
                    case 0: // sum
                        packet.literalValue = packet.packets.stream().map(s -> s.literalValue).reduce(0, Long::sum)
                        break
                    case 1: // product
                        packet.literalValue = packet.packets.stream().mapToLong(s -> s.literalValue).reduce(1, (a, b) -> a * b)
                        break
                    case 2: // min
                        packet.literalValue = packet.packets.stream().mapToLong(s->s.literalValue).min().orElseThrow(NoSuchElementException::new)
                        break
                    case 3: // max
                        packet.literalValue = packet.packets.stream().mapToLong(s->s.literalValue).max().orElseThrow(NoSuchElementException::new)
                        break
                    case 5: // greater
                        if (packet.packets.get(0).literalValue > packet.packets.get(1).literalValue) {
                            packet.literalValue = 1
                        } else {
                            packet.literalValue = 0
                        }
                        break
                    case 6: // less
                        if (packet.packets.get(0).literalValue < packet.packets.get(1).literalValue) {
                            packet.literalValue = 1
                        } else {
                            packet.literalValue = 0
                        }
                        break
                    case 7: // equal
                        if (packet.packets.get(0).literalValue == packet.packets.get(1).literalValue) {
                            packet.literalValue = 1
                        } else {
                            packet.literalValue = 0
                        }
                        break
                    default:
                        break
                }

            }
            packet.binary = binary.substring(start, position)
            packet.endPosition = position
            println packet
            packets.add(packet)
        }
        return packets
    }

    static String toBinary(String input) {

        StringBuffer binary = new StringBuffer()
        for (int i = 0; i < input.length(); i++) {
            String bin = StringUtils.leftPad(Integer.toBinaryString(Integer.parseInt(input[i], 16)), 4, "0")
            binary.append(bin)
        }
        return binary.toString()
    }
}
