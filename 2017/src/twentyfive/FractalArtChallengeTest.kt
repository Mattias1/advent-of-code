package twentyfive

import gridList.gridOf
import junit.framework.TestCase
import org.junit.Assert

class FractalArtChallengeTest : TestCase() {
    private val challenge = FractalArtChallenge()

    fun testPixelAmount() {
        val input = listOf(
                "../.# => ##./#../...",
                ".#./..#/### => #..#/..../..../#..#"
        )
        val result = challenge.pixelAmount(input, 2)
        Assert.assertEquals(12, result)
    }

    fun testBelongsToGroup_identity() {
        val grid = gridOf(false, false, false, true)
        val rule = "../.#"

        val result = challenge.belongsToGroup(grid, rule)

        Assert.assertTrue(result)
    }

    fun testBelongsToGroup_rotation() {
        val grid = gridOf(false, false, true, false)
        val rule = "../.#"

        val result = challenge.belongsToGroup(grid, rule)

        Assert.assertTrue(result)
    }
}