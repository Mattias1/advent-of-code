package twentyfive

import junit.framework.TestCase
import org.junit.Assert

class PortBridgeChallengeTest : TestCase() {
    private val challenge = PortBridgeChallenge()

    fun testStrongestBridge() {
        val input = listOf("0/2", "2/2", "2/3", "3/4", "3/5", "0/1", "10/1", "9/10")
        val result = challenge.strongestBridge(input)
        Assert.assertEquals(31, result)
    }

    fun testStrongestLongestBridge() {
        val input = listOf("0/2", "2/2", "2/3", "3/4", "3/5", "0/1", "10/1", "9/10")
        val result = challenge.strongestLongestBridge(input)
        Assert.assertEquals(19, result)
    }
}