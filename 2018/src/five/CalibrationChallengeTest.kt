package five

import junit.framework.TestCase
import org.junit.Assert
import org.junit.Test

class CalibrationChallengeTest : TestCase() {
    private val challenge: CalibrationChallenge = CalibrationChallenge()

    @Test
    fun testResultingFrequency_Case1_Addition() {
        val input = listOf("+1", "+1", "+1")
        val result = challenge.resultingFrequency(input)
        Assert.assertEquals(3, result)
    }

    @Test
    fun testResultingFrequency_Case2_Combination() {
        val input = listOf("+1", "+1", "-2")
        val result = challenge.resultingFrequency(input)
        Assert.assertEquals(0, result)
    }

    @Test
    fun testResultingFrequency_Case3_Substraction() {
        val input = listOf("-1", "-1", "-2")
        val result = challenge.resultingFrequency(input)
        Assert.assertEquals(-4, result)
    }

    @Test
    fun testRepeatingFrequencyCase1_Zero() {
        val input = listOf("+1", "-1")
        val result = challenge.repeatingFrequency(input)
        Assert.assertEquals(0, result)
    }

    @Test
    fun testRepeatingFrequencyCase2_LoopAroundOnce() {
        val input = listOf("+3", "+3", "+4", "-2", "-4")
        val result = challenge.repeatingFrequency(input)
        Assert.assertEquals(10, result)
    }

    @Test
    fun testRepeatingFrequencyCase3_LoopAroundMultipleTimes() {
        val input = listOf("-6", "+3", "+8", "+5", "-6")
        val result = challenge.repeatingFrequency(input)
        Assert.assertEquals(5, result)
    }

    @Test
    fun testRepeatingFrequencyCase4_LoopAroundMultipleTimes() {
        val input = listOf("+7", "+7", "-2", "-7", "-4")
        val result = challenge.repeatingFrequency(input)
        Assert.assertEquals(14, result)
    }
}