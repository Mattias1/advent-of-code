package five

import junit.framework.TestCase
import org.junit.Assert
import org.junit.Test

class AlchemicalReductionChallengeTest : TestCase() {
    private val challenge: AlchemicalReductionChallenge = AlchemicalReductionChallenge()

    @Test
    fun testFullyReducing_largerExample() {
        val input = "dabAcCaCBAcCcaDA"
        val result = challenge.fullyReducedPolymerCount(input)
        Assert.assertEquals(10, result)
    }

    @Test
    fun testSmartlyReducing_largerExample() {
        val input = "dabAcCaCBAcCcaDA"
        val result = challenge.smartlyReducedPolymerCount(input)
        Assert.assertEquals(4, result)
    }
}