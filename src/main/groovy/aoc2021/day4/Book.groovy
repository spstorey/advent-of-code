package aoc2021.day4

import java.util.stream.Collectors

class Book {

    boolean house = false;
    List<List<Integer>> lines = new ArrayList<>();

    void add(String line) {
        lines.add(Arrays.stream(line.split(' '))
                .filter(s -> s != "")
                .map(Integer::parseInt).collect(Collectors.toList()))
    }

    void complete() {
        List<List<Integer>> verticalLines = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            List<Integer> vline = new ArrayList<>()
            vline.add(lines.get(0).get(i))
            vline.add(lines.get(1).get(i))
            vline.add(lines.get(2).get(i))
            vline.add(lines.get(3).get(i))
            vline.add(lines.get(4).get(i))
            verticalLines.add(vline);
        }
        lines.addAll(verticalLines)
    }

}
