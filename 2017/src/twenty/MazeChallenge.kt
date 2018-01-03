package twenty

import gridList.GridList

class MazeChallenge {
    fun seenLetters(input: String): String {
        val alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
        val maze = buildGrid(input)
        val initial = initialPacket(input)

        return generateSequence(initial) { it.move(maze) }
                .takeWhile { !it.isFinished(maze) }
                .filter { maze[it.current] in alphabet }
                .map { maze[it.current] }
                .joinToString("")
    }

    fun stepNumber(input: String): Int {
        val maze = buildGrid(input)
        val initial = initialPacket(input)

        return generateSequence(initial) { it.move(maze) }
                .takeWhile { !it.isFinished(maze) }
                .count()
    }

    private fun buildGrid(input: String): GridList<Char> =
            GridList(input.split("\n").filter { it.isNotBlank() }.map { it.toCharArray().toList() }, ' ')

    private fun initialPacket(input: String): Packet =
            Packet(input.indexOf('|') - 1, 0) // -1 because of the initial newline


    class Packet {
        private val up = Pair(0, -1)
        private val right = Pair(1, 0)
        private val down = Pair(0, 1)
        private val left = Pair(-1, 0)

        private val _x: Int
        private val _y: Int
        private val _dir: Pair<Int, Int>

        constructor(x: Int, y: Int, dir: Pair<Int, Int>? = null) {
            _x = x
            _y = y
            _dir = dir ?: down
        }

        val current get() = Pair(_x, _y)

        private val next get() = Pair(_x + _dir.first, _y + _dir.second)

        fun move(maze: GridList<Char>): Packet {
            var dir = _dir
            if (shouldTurn(maze)) {
                dir = otherDirs.first { isOkToTurn(it, maze) }
            }

            return Packet(_x + dir.first, _y + dir.second, dir)
        }

        private fun shouldTurn(maze: GridList<Char>): Boolean =
                maze[current] == '+' && maze.getOrElse(next, { ' ' }) == ' '

        private fun isOkToTurn(turn: Pair<Int, Int>, maze: GridList<Char>): Boolean =
                maze.getOrElse(_x + turn.first, _y + turn.second, { _, _ -> ' ' }) != ' '

        fun isFinished(maze: GridList<Char>): Boolean =
                maze.getOrElse(current, { ' ' }) == ' '

        private val otherDirs: List<Pair<Int, Int>>
            get() = if (_dir == down || _dir == up) listOf(right, left) else listOf(up, down)
    }
}