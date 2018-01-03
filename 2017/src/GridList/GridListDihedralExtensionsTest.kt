package gridList

import junit.framework.TestCase
import org.junit.Assert

class GridListDihedralExtensionsTest : TestCase() {
    fun testReflectHorizontal() {
        val grid = buildTestGrid()
        val result = grid.reflectHorizontal()
        assertGrid(result, 3, 4, 1, 2)
    }

    fun testReflectVertical() {
        val grid = buildTestGrid()
        val result = grid.reflectVertical()
        assertGrid(result, 2, 1, 4, 3)
    }

    fun testRotateRight_size2() {
        // 1 2   ->   3 1
        // 3 4        4 2
        val grid = buildTestGrid()
        val result = grid.rotateRight()
        assertGrid(result, 3, 1, 4, 2)
    }

    fun testRotateRight_size3() {
        // 1 2 3        7 4 1
        // 4 5 6   ->   8 5 2
        // 7 8 9        9 6 3
        val grid = gridOf(1, 2, 3, 4, 5, 6, 7, 8, 9)
        val result = grid.rotateRight()
        assertGrid(result, 7, 4, 1, 8, 5, 2, 9, 6, 3)
    }

    fun testRotateLeft() {
        // 1 2   ->   2 4
        // 3 4        1 3
        val grid = buildTestGrid()
        val result = grid.rotateLeft()
        assertGrid(result, 2, 4, 1, 3)
    }

    private fun buildTestGrid(): GridList<Int> = gridOf(1, 2, 3, 4)

    private fun assertGrid(grid: GridList<Int>, vararg v: Int) {
        val expected = gridOf(*v.toTypedArray())
        Assert.assertEquals(expected.values.joinToString(","), grid.values.joinToString(","))
    }
}
