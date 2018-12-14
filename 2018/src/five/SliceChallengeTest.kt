package five

import junit.framework.Assert
import junit.framework.TestCase
import org.junit.Test

class SliceChallengeTest : TestCase() {
    private val challenge: SliceChallenge = SliceChallenge()

    @Test
    fun testOverlappingInches_testcase() {
        val input = listOf(
            "#1 @ 1,3: 4x4",
            "#2 @ 3,1: 4x4",
            "#3 @ 5,5: 2x2"
        )
        val result = challenge.overlappingInches(input)
        Assert.assertEquals(4, result)
    }

    @Test
    fun testIntactGrid_testcase() {
        val input = listOf(
            "#1 @ 1,3: 4x4",
            "#2 @ 3,1: 4x4",
            "#3 @ 5,5: 2x2"
        )
        val result = challenge.intactGrid(input)
        Assert.assertEquals(3, result)
    }
}