package twenty

import junit.framework.TestCase
import org.junit.Assert
import kotlin.system.measureTimeMillis

class SpinLockChallengeTest : TestCase() {
    private val challenge = SpinLockChallenge()

    fun testValueAfterStop_NineRounds() {
        val result = challenge.valueAfterStop(3, 9)
        Assert.assertEquals(5, result)
    }

    fun testValueAfterStop_AllRounds() {
        val result = challenge.valueAfterStop(3)
        Assert.assertEquals(638, result)
    }

    fun testValueAfterZero() {
        val result = challenge.valueAfterZero(3, 9)
        Assert.assertEquals(9, result)
    }

    fun testValueAfterZeroSpeedTest() {
        val time: Long = measureTimeMillis {
            val result = challenge.valueAfterZero(3, 900_000)
        }
        println("Total milliseconds: $time")
    }
}