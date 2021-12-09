package aoc2021.day9

import groovy.transform.EqualsAndHashCode

@EqualsAndHashCode
class Point {
    int row,column,height

    Point(int row, int column, int height) {
        this.row = row
        this.column = column
        this.height = height
    }

    String toString() {
        return "row " + row + ", column " + column + ", height " + height
    }
}
