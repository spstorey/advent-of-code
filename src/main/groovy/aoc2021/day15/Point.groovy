package aoc2021.day15

class Point {
    int x, y, cost

    Point(int x, int y, int cost) {
        this.x = x
        this.y = y
        this.cost = cost
    }

    @Override
    boolean equals(Object o) {
        Point point = (Point) o
        return x == point.x && y == point.y && cost == point.cost;
    }

    @Override
    int hashCode() {
        return Objects.hash(x, y, cost);
    }
}
