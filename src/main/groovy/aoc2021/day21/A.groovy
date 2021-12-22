package aoc2021.day21

int p1Position = 7
int p2Position = 1
int p1Score = 0
int p2Score = 0
boolean p1Turn = true

int roll = 0

while (p1Score < 1000 && p2Score < 1000) {

    int totalMove = roll * 3 + 6
    roll = roll + 3
    if (p1Turn) {
        p1Position = move(p1Position, totalMove)
        p1Score += p1Position
        p1Turn = false
    } else {
        p2Position = move(p2Position, totalMove)
        p2Score += p2Position
        p1Turn = true
    }
}

println Math.min(p1Score, p2Score) * roll

static int move(int position, int move) {
    if ((position + move) % 10 == 0) {
        return 10
    } else {
        return (position + move) % 10
    }
}
