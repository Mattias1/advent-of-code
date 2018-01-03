package twenty

import junit.framework.TestCase
import org.junit.Assert

class MazeChallengeTest : TestCase() {
    private val challenge = MazeChallenge()

    fun testSeenLetters() {
        val input = getSimpleMaze()
        val result = challenge.seenLetters(input)
        Assert.assertEquals("ABCDEF", result)
    }

    fun testStepNumber() {
        val input = getSimpleMaze()
        val result = challenge.stepNumber(input)
        Assert.assertEquals(38, result)
    }

    private fun getSimpleMaze(): String {
        return """
     |
     |  +--+
     A  |  C
 F---|----E|--+
     |  |  |  D
     +B-+  +--+
        """
    }
}