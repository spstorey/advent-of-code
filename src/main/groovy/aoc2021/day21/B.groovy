package aoc2021.day21

println Math.max(game(1), game(2))

static long game(int player) {
    int p1Position = 7
    int p2Position = 1
    int p1Score = 0
    int p2Score = 0
    long wins = 0
    boolean p1Turn = true

    wins += nextTurn(p1Position, p2Position, p1Score, p2Score, 9, p1Turn, player)
    wins += nextTurn(p1Position, p2Position, p1Score, p2Score, 8, p1Turn, player) * 3
    wins += nextTurn(p1Position, p2Position, p1Score, p2Score, 7, p1Turn, player) * 6
    wins += nextTurn(p1Position, p2Position, p1Score, p2Score, 6, p1Turn, player) * 7
    wins += nextTurn(p1Position, p2Position, p1Score, p2Score, 5, p1Turn, player) * 6
    wins += nextTurn(p1Position, p2Position, p1Score, p2Score, 4, p1Turn, player) * 3
    wins += nextTurn(p1Position, p2Position, p1Score, p2Score, 3, p1Turn, player)

    return wins
}

static long nextTurn(int p1Position, int p2Position, int p1Score, int p2Score, int move, boolean p1Turn, int player) {
    long wins = 0

    if (p1Turn) {
        if ((p1Position + move) % 10 == 0) {
            p1Position = 10
        } else {
            p1Position = (p1Position + move) % 10
        }
        p1Score += p1Position
    } else {
        if ((p2Position + move) % 10 == 0) {
            p2Position = 10
        } else {
            p2Position = (p2Position + move) % 10
        }
        p2Score += p2Position
    }
    p1Turn = !p1Turn

    if (p1Score > 20 || p2Score > 20) {
        if (player == 1) {
            if (p1Score > 20) {
                return 1
            } else {
                return 0
            }
        } else {
            if (p2Score > 20) {
                return 1
            } else {
                return 0
            }
        }
    } else {
        wins += nextTurn(p1Position, p2Position, p1Score, p2Score, 9, p1Turn, player)
        wins += nextTurn(p1Position, p2Position, p1Score, p2Score, 8, p1Turn, player) * 3
        wins += nextTurn(p1Position, p2Position, p1Score, p2Score, 7, p1Turn, player) * 6
        wins += nextTurn(p1Position, p2Position, p1Score, p2Score, 6, p1Turn, player) * 7
        wins += nextTurn(p1Position, p2Position, p1Score, p2Score, 5, p1Turn, player) * 6
        wins += nextTurn(p1Position, p2Position, p1Score, p2Score, 4, p1Turn, player) * 3
        wins += nextTurn(p1Position, p2Position, p1Score, p2Score, 3, p1Turn, player)
    }
    return wins
}
