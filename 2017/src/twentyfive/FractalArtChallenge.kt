package twentyfive

import gridList.*

class FractalArtChallenge {
    fun pixelAmount(input: List<String>, iterationAmount: Int = 5): Int {
        val rules = getRules(input)
        val initialGrid: GridList<Boolean> = ruleToGrid(".#./..#/###")

        val grid = (1..iterationAmount).fold(initialGrid, { g, _ -> step(g, rules) })
        return grid.values.count { it }
    }

    private fun getRules(input: List<String>): Map<String, String> =
            input.map { it.split(" => ") }.associate { it[0] to it[1] }

    private fun ruleToGrid(rule: String): GridList<Boolean> =
            GridList(rule.split('/').map { it.map { it == '#' } })

    private fun step(grid: GridList<Boolean>, rules: Map<String, String>): GridList<Boolean> {
        val size = if (grid.width % 2 == 0) 2 else 3
        val subGrids = grid.splitToSubGrids(size, size)
        val convertedSubGrids = subGrids.map { g ->
            val ruleKey = rules.keys.first { belongsToGroup(g, it) }
            ruleToGrid(rules[ruleKey]!!)
        }
        return joinGrids(convertedSubGrids)
    }

    fun belongsToGroup(grid: GridList<Boolean>, rule: String): Boolean {
        val rotatedLeft = grid.rotateLeft()
        val flip = grid.reflectVertical()
        val flipRotatedLeft = flip.rotateLeft()
        val d4 = listOf(
                grid, rotatedLeft, rotatedLeft.rotateLeft(), grid.rotateRight(),
                flip, flipRotatedLeft, flipRotatedLeft.rotateLeft(), flip.rotateRight()
        )
        return d4.any { elementEquals(it, rule) }
    }

    private fun elementEquals(grid: GridList<Boolean>, rule: String): Boolean {
        val stringRepresentation = grid
                .map { if (it) '#' else '.' }
                .rows
                .map { it.joinToString("") }
                .joinToString("/")
        return stringRepresentation == rule
    }
}