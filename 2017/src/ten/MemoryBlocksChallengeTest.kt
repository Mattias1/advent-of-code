package ten

import junit.framework.TestCase
import org.junit.Assert
import org.junit.Test

class MemoryBlocksChallengeTest : TestCase() {
    val challenge = MemoryBlocksChallenge()

    @Test
    fun testRedistributionCycles() {
        val result = challenge.redistributionCycles(listOf(0, 2, 7, 0))

        Assert.assertEquals(5, result)
    }

    @Test
    fun testRedistributionCycleSize() {
        val result = challenge.redistributionCycleSize(listOf(0, 2, 7, 0))

        Assert.assertEquals(4, result)
    }
}