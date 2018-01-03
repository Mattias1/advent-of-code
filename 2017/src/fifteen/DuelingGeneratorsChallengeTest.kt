package fifteen

import junit.framework.TestCase
import org.junit.Assert
import org.junit.Test

class DuelingGeneratorsChallengeTest : TestCase() {
    private val challenge = DuelingGeneratorsChallenge()

    @Test
    fun testNumberOfMatches() {
        val result = challenge.numberOfMatches(65, 8921)
        Assert.assertEquals(588, result)
    }

    @Test
    fun testNumberOfAnnoyingMatches() {
        val result = challenge.numberOfAnnoyingMatches(65, 8921)
        Assert.assertEquals(309, result)
    }
}