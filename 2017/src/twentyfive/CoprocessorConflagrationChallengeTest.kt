package twentyfive

import junit.framework.TestCase
import org.junit.Assert

class CoprocessorConflagrationChallengeTest : TestCase() {
    private val challenge = CoprocessorConflagrationChallenge()

    fun testCountMulInstructions() {
        val input = simpleInput()
        val result = challenge.countMulInstructions(input)
        Assert.assertEquals(5, result)
    }

    // No test for part 2, as I transformed the program manually and inserted the hardcoded results

    private fun simpleInput(): List<String> {
        return listOf("set h 2", "mul h h", "sub h 1", "mul h 1", "jnz h -2")
    }
}