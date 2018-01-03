package five

import junit.framework.TestCase
import org.junit.Assert
import org.junit.Test

class CorruptionChecksumChallengeTest : TestCase() {
    private val challenge: CorruptionChecksumChallenge = CorruptionChecksumChallenge()

    @Test
    fun testDifferenceChecksum_givenGrid() {
        val input = arrayOf(
                "5 1 9 5",
                "7 5   3",
                "2 4 6 8"
        )
        val result = challenge.differenceChecksum(input)

        Assert.assertEquals(18, result)
    }

    @Test
    fun testDivisionChecksum_givenGrid() {
        val input = arrayOf(
                "5 9 2 8",
                "9 4 7 3",
                "3 8 6 5"
        )
        val result = challenge.divisionChecksum(input)

        Assert.assertEquals(9, result)
    }
}