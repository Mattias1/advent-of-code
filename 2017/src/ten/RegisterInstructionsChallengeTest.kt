package ten

import junit.framework.TestCase
import org.junit.Assert
import org.junit.Test

class RegisterInstructionsChallengeTest : TestCase() {
    private val challenge = RegisterInstructionsChallenge()

    @Test
    fun testGetHighestRegister() {
        val input = getSimpleInput()
        val result = challenge.getHighestRegister(input)

        Assert.assertEquals(1, result)
    }

    @Test
    fun testGetHighestRegisterEver() {
        val input = getSimpleInput()
        val result = challenge.getHighestRegisterEver(input)

        Assert.assertEquals(10, result)
    }

    private fun getSimpleInput(): List<String> {
        return listOf(
                "b inc 5 if a > 1",
                "a inc 1 if b < 5",
                "c dec -10 if a >= 1",
                "c inc -20 if c == 10"
        )
    }
}