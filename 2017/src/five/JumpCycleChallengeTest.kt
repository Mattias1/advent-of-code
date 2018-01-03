package five

import junit.framework.TestCase
import org.junit.Assert
import org.junit.Test

class JumpCycleChallengeTest : TestCase() {
    val challenge = JumpCycleChallenge()

    @Test
    fun testJumpsToExit() {
        val input = arrayOf(0, 3, 0, 1, -3)
        val result = challenge.jumpsToExit(input)

        Assert.assertEquals(5, result)

        val expectedOutput = arrayOf(2, 5, 0, 1, -2)
        Assert.assertArrayEquals(expectedOutput, input)
    }

    @Test
    fun testWeirdJumpsToExit() {
        val input = arrayOf(0, 3, 0, 1, -3)
        val result = challenge.weirdJumpsToExit(input)

        Assert.assertEquals(10, result)

        val expectedOutput = arrayOf(2, 3, 2, 3, -1)
        Assert.assertArrayEquals(expectedOutput, input)
    }
}