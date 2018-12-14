package gridList

import junit.framework.TestCase
import org.junit.Assert

class GridListCreateExtensionsTest : TestCase() {
    fun testSplitToSubGrids() {
        val grid = build4x4Grid()
        val subs = grid.splitToSubGrids(2, 2)

        Assert.assertEquals(4, subs.count())
        assertGrid(subs[0, 0], 0, 1, 1, 2)
        assertGrid(subs[1, 0], 2, 3, 3, 4)
        assertGrid(subs[0, 1], 2, 3, 3, 4)
        assertGrid(subs[1, 1], 4, 5, 5, 6)
    }

    fun testSubGrid() {
        val grid = build4x4Grid()
        val sub = grid.subGrid(1, 0, 2, 2)

        assertGrid(sub, 1, 2, 2, 3)
    }

    fun testJoinFromSubGrids() {
        val sub1 = gridOf(0, 1, 1, 2)
        val sub2 = gridOf(2, 3, 3, 4)
        val sub3 = gridOf(2, 3, 3, 4)
        val sub4 = gridOf(4, 5, 5, 6)
        val container = MutableGridList(
            listOf(
                listOf(sub1, sub2),
                listOf(sub3, sub4)
            )
        )

        val result = joinGrids(container)

        val expectedGrid = build4x4Grid()
        Assert.assertEquals(expectedGrid.values.joinToString(","), result.values.joinToString(","))
    }

    private fun build4x4Grid(): GridList<Int> =
        GridList(4, 4) { x, y -> x + y }

    private fun assertGrid(grid: GridList<Int>, vararg v: Int) {
        val expected = gridOf(*v.toTypedArray())
        Assert.assertEquals(expected.values.joinToString(","), grid.values.joinToString(","))
    }
}