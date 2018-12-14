package five

import junit.framework.Assert
import junit.framework.TestCase
import org.junit.Test

class InventoryManagementChallengeTest : TestCase() {
    private val challenge: InventoryManagementChallenge = InventoryManagementChallenge()

    @Test
    fun testChecksum() {
        val input = listOf(
            "abcdef",
            "bababc",
            "abbcde",
            "abcccd",
            "aabcdd",
            "abcdee",
            "ababab"
        )
        val result = challenge.checksum(input)
        Assert.assertEquals(12, result)
    }

    @Test
    fun testCommonLetters() {
        val input = listOf(
            "abcde",
            "fghij",
            "klmno",
            "pqrst",
            "fguij",
            "axcye",
            "wvxyz"
        )
        val result = challenge.commonLetters(input)
        Assert.assertEquals("fgij", result)
    }
}
