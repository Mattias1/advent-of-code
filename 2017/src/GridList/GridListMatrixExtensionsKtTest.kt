package gridList

import junit.framework.TestCase
import org.junit.Assert

class GridListMatrixExtensionsKtTest : TestCase() {

    fun testPlus() {
        val a = gridOf(3, 4, 5, 6)
        val b = gridOf(1, 2, 3, 4)
        val result = a + b
        assertGrid(result, 4, 6, 8, 10)
    }

    fun testMinus() {
        val a = gridOf(4, 3, 2, 1)
        val b = gridOf(1, 2, 3, 4)
        val result = a - b
        assertGrid(result, 3, 1, -1, -3)
    }

    fun testTimes_identityLeft() {
        val id = gridOf(1, 0, 0, 1)
        val m = gridOf(1, 2, 3, 4)
        val result = id * m
        assertGrid(result, 1, 2, 3, 4)
    }

    fun testTimes_identityRight() {
        val m = gridOf(1, 2, 3, 4)
        val id = gridOf(1, 0, 0, 1)
        val result = m * id
        assertGrid(result, 1, 2, 3, 4)
    }

    fun testTimes_theRealThing() {
        // 1 2       5 6        5  8
        // 3 4   *   0 1   =   15 22
        val a = gridOf(1, 2, 3, 4)
        val b = gridOf(5, 6, 0, 1)
        val result = a * b
        assertGrid(result, 5, 8, 15, 22)
    }

    fun testDotProduct() {
        val a = (0..2).toList()
        val b = (0..2).toList()
        val result = dotProduct(a, b)
        Assert.assertEquals(5, result)
    }

    private fun assertGrid(grid: GridList<Int>, vararg v: Int) {
        val expectedGrid = gridOf(*v.toTypedArray())
        Assert.assertEquals(expectedGrid.values.joinToString(","), grid.values.joinToString(","))
    }
}