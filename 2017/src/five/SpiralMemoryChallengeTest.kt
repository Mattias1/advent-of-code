package five

import junit.framework.TestCase
import org.junit.Assert
import org.junit.Test

class SpiralMemoryChallengeTest : TestCase() {
    val challenge = SpiralMemoryChallenge()

    @Test
    fun testCenter() {
        val result = challenge.manhattenDistanceToCenter(1)

        Assert.assertEquals(0, result)
    }

    @Test
    fun test12() {
        val result = challenge.manhattenDistanceToCenter(12)

        Assert.assertEquals(3, result)
    }

    @Test
    fun test23() {
        val result = challenge.manhattenDistanceToCenter(23)

        Assert.assertEquals(2, result)
    }

    @Test
    fun test1024() {
        val result = challenge.manhattenDistanceToCenter(1024)

        Assert.assertEquals(31, result)
    }

    @Test
    fun testFirstStep() {
        val result = challenge.firstLarger(1)

        Assert.assertEquals(2, result)
    }

    @Test
    fun testThirdStep() {
        val result = challenge.firstLarger(2)

        Assert.assertEquals(4, result)
    }

    @Test
    fun testFourthStep() {
        val result = challenge.firstLarger(4)

        Assert.assertEquals(5, result)
    }

    @Test
    fun testTwentyThirdStep() {
        val result = challenge.firstLarger(747)

        Assert.assertEquals(806, result)
    }
}