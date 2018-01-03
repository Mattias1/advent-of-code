package twentyfive

import gridList.*

const val CLEAN = 0
const val WEAKENED = 1
const val INFECTED = 2
const val FLAGGED = 3

class SporificaVirusChallenge {
    fun infectionBursts(input: List<String>, steps: Int = 10_000): Int {
        val grid = buildGrid(input)
        val carrier = buildCarrier(grid)

        return (1..steps).fold(carrier, { c, _ -> c.stepSimple(grid) }).infections
    }

    private fun buildGrid(input: List<String>): MutableGridList<Int> {
        val size = 1001
        val start = (size - input.count()) / 2 + 1
        val end = start + input.count() - 1
        val inputGrid: GridList<Int> = GridList(input.map { it.map { if (it == '#') INFECTED else CLEAN } })

        return MutableGridList(size, size, { x, y ->
            if (x in start..end && y in start..end) {
                inputGrid[x - start, y - start]
            } else {
                CLEAN
            }
        })
    }

    private fun buildCarrier(grid: GridList<Int>): Carrier =
            Carrier(grid.width / 2 + 1, grid.height / 2 + 1, 0, 0)

    fun advancedInfectionBursts(input: List<String>, steps: Int = 10_000_000): Int {
        val grid = buildGrid(input)
        val carrier = buildCarrier(grid)

        return (1..steps).fold(carrier, { c, _ -> c.stepAdvanced(grid) }).infections
    }


    class Carrier(private val x: Int, private val y: Int, private val dir: Int, val infections: Int) {
        private val up = Pair(0, -1)
        private val right = Pair(1, 0)
        private val down = Pair(0, 1)
        private val left = Pair(-1, 0)

        fun stepSimple(grid: MutableGridList<Int>): Carrier {
            return if (grid[x, y] == INFECTED) {
                grid[x, y] = CLEAN
                move(turnRight(), false)
            } else {
                grid[x, y] = INFECTED
                move(turnLeft(), true)
            }
        }

        fun stepAdvanced(grid: MutableGridList<Int>): Carrier {
            return when (grid[x, y]) {
                WEAKENED -> {
                    grid[x, y] = INFECTED
                    move(dir, true)
                }
                INFECTED -> {
                    grid[x, y] = FLAGGED
                    move(turnRight(), false)
                }
                FLAGGED -> {
                    grid[x, y] = CLEAN
                    move(reverse(), false)
                }
                else /* CLEAN */ -> {
                    grid[x, y] = WEAKENED
                    move(turnLeft(), false)
                }
            }
        }

        private fun turnRight(): Int = (dir + 1) % 4
        private fun turnLeft(): Int = (dir + 3) % 4
        private fun reverse(): Int = (dir + 2) % 4

        private fun move(newDir: Int, infect: Boolean): Carrier {
            val offset = when (newDir) {
                0 -> up
                1 -> right
                2 -> down
                else -> left
            }
            return Carrier(x + offset.first, y + offset.second, newDir, if (infect) infections + 1 else infections)
        }
    }
}