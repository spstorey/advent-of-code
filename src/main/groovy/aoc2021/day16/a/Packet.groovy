package aoc2021.day16.a

import org.apache.commons.lang3.StringUtils
import org.apache.commons.lang3.builder.ReflectionToStringBuilder
import org.apache.commons.lang3.builder.ToStringStyle

class Packet {

    Integer version
    Integer typeID
    Integer literalValue
    Integer lengthTypeID
    Integer totalLength
    String binary

    boolean isLiteral() {
        return typeID == 4
    }

    boolean hasTotalLength() {
        return lengthTypeID == 0
    }

    Integer getVersion() {
        return version
    }

    @Override
    String toString() {
        return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE)
                .replaceAll(",", ", ")
                .replaceAll("\\[", " [ ")
                .replaceAll("]", " ]")
    }

    static String toBinary(String input) {

        StringBuffer binary = new StringBuffer()
        for (int i = 0; i < input.length(); i++) {
            String bin = StringUtils.leftPad(Integer.toBinaryString(Integer.parseInt(input[i], 16)), 4, "0")
            binary.append(bin)
        }
        return binary.toString()
    }

    static List<Packet> parse(String message) {
        List<Packet> packets = new ArrayList<>()
        int position = 0
        String binary = toBinary(message)
        try {
            while (true) {
                int start = position
                Packet packet = new Packet()
                packet.version = Integer.parseInt(binary.substring(position, position + 3), 2)
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
                    //position += (position - start) % 4
                    packet.literalValue = Long.parseLong(literal.toString(), 2)
                } else {
                    packet.lengthTypeID = Integer.parseInt(binary.substring(position, position + 1), 2)
                    position += 1

                    if (packet.hasTotalLength()) {
                        packet.totalLength = Integer.parseInt(binary.substring(position, position + 15), 2)
                        position += 15
                    } else {
                        position += 11
                    }
                }
                packet.binary = binary.substring(start, position)
                println packet
                packets.add(packet)
            }
        } catch (StringIndexOutOfBoundsException e) {

        }
        return packets
    }
}
