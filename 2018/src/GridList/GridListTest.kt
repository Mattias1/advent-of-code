package gridList

import junit.framework.TestCase
import org.junit.Assert

class GridListTest : TestCase() {
    fun testBasicOperations_xy_immutable() {
        val list = GridList(3, 2) { x, y -> x + y }

        Assert.assertEquals(3, list.width)
        Assert.assertEquals(2, list.height)
        Assert.assertEquals(6, list.count())

        Assert.assertTrue(list.isInRange(0, 0))
        Assert.assertTrue(list.isInRange(0, 1))
        Assert.assertTrue(list.isInRange(2, 0))
        Assert.assertFalse(list.isInRange(3, 1))

        Assert.assertEquals(1, list[1, 0])
        Assert.assertTrue(3 in list)
        Assert.assertFalse(4 in list)

        Assert.assertNull(list.getOrNull(5, 5))
        Assert.assertEquals(42, list.getOrElse(5, 5) { _, _ -> 42 })

        Assert.assertEquals(9, list.values.sum())
    }

    fun testBasicOperations_pair_mutable() {
        val list = MutableGridList(3, 2) { p -> p.first + p.second }

        Assert.assertEquals(3, list.width)
        Assert.assertEquals(2, list.height)
        Assert.assertEquals(6, list.count())

        Assert.assertTrue(list.isInRange(0, 0))
        Assert.assertTrue(list.isInRange(0, 1))
        Assert.assertTrue(list.isInRange(2, 0))
        Assert.assertFalse(list.isInRange(3, 1))

        Assert.assertEquals(1, list[Pair(0, 1)])
        Assert.assertTrue(2 in list)
        Assert.assertFalse(4 in list)

        Assert.assertNull(list.getOrNull(Pair(5, 5)))
        Assert.assertEquals(42, list.getOrElse(Pair(5, 5)) { 42 })

        Assert.assertEquals(9, list.values.sum())

        list.indices.forEach { list[it] *= 2 }

        Assert.assertEquals(2, list[Pair(0, 1)])
        Assert.assertTrue(4 in list)
        Assert.assertFalse(1 in list)

        Assert.assertEquals(18, list.values.sum())
    }

    fun testSomeBasicOperations_xy_mutable() {
        val list = MutableGridList(3, 2) { x, y -> x + y }

        list.indices.forEach { list[it.first, it.second] *= 2 }

        Assert.assertEquals(18, list.values.sum())
    }

    fun testFromDoubleListConstructor() {
        val a = listOf(0, 1)
        val b = listOf(1, 2, 3)
        val list = GridList(listOf(a, b), 2)

        Assert.assertTrue(list.isInRange(2, 1))

        Assert.assertEquals(2, list[2, 0])
        Assert.assertEquals(3, list[2, 1])

        Assert.assertEquals(9, list.values.sum())
    }

    fun testMap() {
        val grid = gridOf(1, 2, 3, 4)
        val result = grid.map { it * 2 }
        Assert.assertEquals(20, result.values.sum())
    }

    fun testAllAnyIndexed() {
        val grid = gridOf(0, 1, 1, 2)
        val all = grid.allIndexed { x, y, v -> v == x + y }
        val notAll = grid.allIndexed { (x, y), v -> v == 1 }
        val any = grid.anyIndexed { x, y, v -> v == 1 }
        val notAny = grid.anyIndexed { (x, y), v -> v != x + y }

        Assert.assertTrue(all)
        Assert.assertFalse(notAll)
        Assert.assertTrue(any)
        Assert.assertFalse(notAny)
    }
}