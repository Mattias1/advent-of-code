package twentyfive

import junit.framework.TestCase
import org.junit.Assert

class SporificaVirusChallengeTest : TestCase() {
    private val challenge = SporificaVirusChallenge()

    fun testInfectionBursts() {
        val input = listOf("..#", "#..", "...")
        val result = challenge.infectionBursts(input)
        Assert.assertEquals(5587, result)
    }

    fun testInfectionBursts_1() {
        val input = listOf("..#", "#..", "...")
        val result = challenge.infectionBursts(input, 1)
        Assert.assertEquals(1, result)
    }

    fun testInfectionBursts_2() {
        val input = listOf("..#", "#..", "...")
        val result = challenge.infectionBursts(input, 2)
        Assert.assertEquals(1, result)
    }

    fun testInfectionBursts_3() {
        val input = listOf("..#", "#..", "...")
        val result = challenge.infectionBursts(input, 3)
        Assert.assertEquals(2, result)
    }

    fun testInfectionBursts_6() {
        val input = listOf("..#", "#..", "...")
        val result = challenge.infectionBursts(input, 6)
        Assert.assertEquals(5, result)
    }

    fun testInfectionBursts_7() {
        val input = listOf("..#", "#..", "...")
        val result = challenge.infectionBursts(input, 7)
        Assert.assertEquals(5, result)
    }

    fun testInfectionBursts_70() {
        val input = listOf("..#", "#..", "...")
        val result = challenge.infectionBursts(input, 70)
        Assert.assertEquals(41, result)
    }

    fun testInfectionBursts_largerGrid() {
        val input = listOf(".......", ".......", "....#..", "..#....", ".......", ".......", ".......")
        val result = challenge.infectionBursts(input, 70)
        Assert.assertEquals(41, result)
    }

    fun testAdvancedInfectionBursts_100() {
        val input = listOf("..#", "#..", "...")
        val result = challenge.advancedInfectionBursts(input, 100)
        Assert.assertEquals(26, result)
    }

    fun testAdvancedInfectionBursts() {
        val input = listOf("..#", "#..", "...")
        val result = challenge.advancedInfectionBursts(input)
        Assert.assertEquals(2511944, result)
    }
}