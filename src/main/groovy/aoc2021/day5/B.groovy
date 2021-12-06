package aoc2021.day5

import aoc2021.day4.Book

import java.util.stream.Collectors

File file = new File("input")
List<String> lines = file.readLines()

List<Integer> draw = Arrays.stream(lines.get(0).split(","))
        .map(Integer::parseInt).collect(Collectors.toList())

List<Book> books = getBooks(lines)
Iterator it = draw.iterator();
int result
Integer answer = null
while (answer == null) {
    int nextDraw = it.next()
    for (Book book : books) {
        if (!book.house) {
            result = markBook(book, nextDraw)
            if (result != 0) {
                if (winners(books) == books.size() -1) {
                    answer = result * nextDraw
                } else {
                    book.house = true
                }
            }
        }
    }
}
println answer

int winners(List<Book> books) {
    return books.stream().filter(s->s.house).count()
}

int markBook(Book book, Integer nextDraw) {

    for (List<Integer> line : book.getLines()) {
        line.removeElement(nextDraw)
        if (line.size() == 0) {
            return remainingNumbers(book)
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

List<Book> getBooks(List<String> input) {
    List<Book> books = new ArrayList<>()
    Book book = null
    for (String line : input) {
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
    return books;
}
