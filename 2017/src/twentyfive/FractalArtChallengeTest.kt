package twentyfive

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
}