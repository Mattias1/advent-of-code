package five

import kotlin.math.absoluteValue
import kotlin.math.max

class SpiralStep constructor(
        val Index: Int,                 // 1, 2, 3, ...
        val Coordinate: Pair<Int, Int>, // (0,0), (1, 0), (1, 1), ...
        val Step: Int,                  // 1, 3, 5, ...
        val Grid: MutableList<Int>?     // A list with the all the values given the (transformed) coordinates
) {
    var Value: Int = 0

    constructor(grid: MutableList<Int>?) : this(1, Pair(0, 0), 1, grid)

    init {
        Value = nextValue()

        if (Grid != null) {
            val index: Int = getGridIndex(X, Y)
            val value = Grid[index]
            if (value != 0) {
                throw Exception("The grid at index $index ($X, $Y) is already set (at $value)")
            }
            Grid[index] = Value
        }
    }

    val Maximum: Int get() = Step * Step
    val X: Int get() = Coordinate.first
    val Y: Int get() = Coordinate.second

    fun Next(): SpiralStep {
        // Reached the end (so right)
        if (Index == Maximum) {
            // if (Grid != null) {
            //     val newMaximum: Int = (Step + 2) * (Step + 2)
            //     Grid.addAll((Maximum + 1..newMaximum).map { 0 })
            // }
            if (Step > 99) {
                throw Exception("Step wordt te groot :)")
            }
            return SpiralStep(Index + 1, Pair(X + 1, Y), Step + 2, Grid)
        }
        // Up
        if (this.isInQuadrant(1)) {
            return SpiralStep(Index + 1, Pair(X, Y + 1), Step, Grid)
        }
        // Left
        if (this.isInQuadrant(2)) {
            return SpiralStep(Index + 1, Pair(X - 1, Y), Step, Grid)
        }
        // Down
        if (this.isInQuadrant(3)) {
            return SpiralStep(Index + 1, Pair(X, Y - 1), Step, Grid)
        }
        // Right
        if (this.isInQuadrant(4)) {
            return SpiralStep(Index + 1, Pair(X + 1, Y), Step, Grid)
        }

        throw Exception("Code shouldn't come here.")
    }

    private fun isInQuadrant(quadrant: Int): Boolean {
        val size = Step - 1
        val lower = 5 - quadrant
        val upper = lower - 1

        val highEnough = Maximum - lower * size <= Index
        val lowEnough = Index < Maximum - upper * size

        return highEnough && lowEnough
    }

    private fun nextValue(): Int {
        if (Grid == null) {
            return 0
        }

        if (Index == 1) {
            return 1
        }

        return (-1..1).map { i ->
            (-1..1).map { j ->
                if (i == 0 && j == 0) {
                    return@map 0
                }
                return@map getGridValueOrZero(X + i, Y + j)
            }.sum()
        }.sum()
    }

    private fun getGridValueOrZero(x: Int, y: Int): Int {
        val index = getGridIndex(x, y)
        if (index < Grid!!.count()) {
            return Grid[index]
        }
        return 0
    }

    private fun getGridIndex(x: Int, y: Int): Int {
        val halfStep: Int = max(x.absoluteValue, y.absoluteValue)
        val step: Int = halfStep * 2 + 1
        val factor: Int = 2

        val xModified = if (x < 0) x.absoluteValue * factor - 1 else x * factor
        val yModified = if (y < 0) y.absoluteValue * factor - 1 else y * factor

        return xModified + yModified * 101 // step // This trick doesn't work, because `step` changes
    }
}
