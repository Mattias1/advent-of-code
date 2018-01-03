package fifteen

import junit.framework.TestCase
import org.junit.Assert
import org.junit.Test

class FirewallChallengeTest : TestCase() {
    private val challenge = FirewallChallenge()

    @Test
    fun testCalculateSeverity() {
        val input = getSimpleInput()
        val result = challenge.calculateSeverity(input)

        Assert.assertEquals(24, result)
    }

    @Test
    fun testFirstSafeTrip() {
        val input = getSimpleInput()
        val result = challenge.firstSafeTrip(input)

        Assert.assertEquals(10, result)
    }

    private fun getSimpleInput(): List<String> {
        return listOf("0: 3", "1: 2", "4: 4", "6: 4")
    }
}