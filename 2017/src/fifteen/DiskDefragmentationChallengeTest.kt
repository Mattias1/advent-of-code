package fifteen

import junit.framework.TestCase
import org.junit.Assert
import org.junit.Test

class DiskDefragmentationChallengeTest : TestCase() {
    private val challenge = DiskDefragmentationChallenge()

    @Test
    fun testUsedSquares() {
        val result = challenge.usedSquares("flqrgnkx")
        Assert.assertEquals(8108, result)
    }

    @Test
    fun testRegionCount() {
        val result = challenge.regionCount("flqrgnkx")
        Assert.assertEquals(1242, result)
    }
}