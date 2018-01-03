package ten

import junit.framework.TestCase
import org.junit.Assert
import org.junit.Test

class BalancingTowersChallengeTest : TestCase() {
    private val challenge = BalancingTowersChallenge()

    @Test
    fun testSimpleBlocks() {
        val input = getSimpleBlocks()
        val result = challenge.getBase(input)

        Assert.assertEquals("tknk", result.Name)
    }

    @Test
    fun testSimpleBlockWeights() {
        val input = getSimpleBlocks()
        val result = challenge.getImbalancedFixWeight(input)

        Assert.assertEquals(60, result)
    }

    private fun getSimpleBlocks(): List<String> {
        return listOf(
                "pbga (66)",
                "xhth (57)",
                "ebii (61)",
                "havc (66)",
                "ktlj (57)",
                "fwft (72) -> ktlj, cntj, xhth",
                "qoyq (66)",
                "padx (45) -> pbga, havc, qoyq",
                "tknk (41) -> ugml, padx, fwft",
                "jptl (61)",
                "ugml (68) -> gyxo, ebii, jptl",
                "gyxo (61)",
                "cntj (57)"
        )
    }
}