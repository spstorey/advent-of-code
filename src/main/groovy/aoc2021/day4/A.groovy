package aoc2021.day4

import java.util.stream.Collectors

File file = new File("input")
List<String> lines = file.readLines()

List<Integer> draw = Arrays.stream(lines.get(0).split(","))
        .map(Integer::parseInt).collect(Collectors.toList())

List<Book> books = new ArrayList();

Book book = null
for (String line : lines) {
    if (line.size() < 50) {
        if (line == "") {
            if (book != null) {
                book.complete()
                books.add(book)
            }
            book = new Book();
        } else {
            book.add(line)
        }
    }
}
book.complete()
books.add(book)

int result
for (Integer nextDraw : draw) {
    result = markBook(books, nextDraw)
    if (result != 0) {
        println result * nextDraw
        break
    }
}

println "Hello"

int markBook(List<Book> books, Integer nextDraw) {
    for (Book book : books) {
        for (List<Integer> line : book.getLines()) {
            line.removeElement(nextDraw)
            if (line.size() == 0) {
                return remainingNumbers(book)
            }
        }
    }
    return 0
}

int remainingNumbers(Book book) {
    int total = 0;
    for (int i = 0; i < 5; i++) {
        total += book.getLines().get(i).stream().reduce(0, Integer::sum)
    }

    return total
}
