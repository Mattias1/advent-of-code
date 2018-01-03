package twenty

import junit.framework.TestCase
import org.junit.Assert

class DancingChallengeTest : TestCase() {
    private val challenge = DancingChallenge()

    fun testDanceS() {
        val result = challenge.danceResult("s3")
        Assert.assertEquals("nopabcdefghijklm", result)
    }

    fun testDanceX() {
        val result = challenge.danceResult("x3/2")
        Assert.assertEquals("abdcefghijklmnop", result)
    }

    fun testDanceP() {
        val result = challenge.danceResult("pe/a")
        Assert.assertEquals("ebcdafghijklmnop", result)
    }

    fun testDanceDanceDanceOnce() {
        val result = challenge.danceDanceDanceResult("s3,x3/2,pe/a", 1)
        Assert.assertEquals("noepbcdafghijklm", result)
    }

    fun testDanceDanceDanceTwice() {
        val result = challenge.danceDanceDanceResult("s2,x0/1,pa/b", 2)
        Assert.assertEquals("nmpoabcdefghijkl", result)
    }

    fun testDanceDanceDanceLoopEven() {
        val result = challenge.danceDanceDanceResult("s4", 1000)
        Assert.assertEquals("abcdefghijklmnop", result)
    }

    fun testDanceDanceDanceLoopOdd() {
        val result = challenge.danceDanceDanceResult("s4", 1001)
        Assert.assertEquals("mnopabcdefghijkl", result)
    }

    fun testDanceDanceDanceLoopS5() {
        val result = challenge.danceDanceDanceResult("s7", 100)
        Assert.assertEquals("efghijklmnopabcd", result)
    }
}