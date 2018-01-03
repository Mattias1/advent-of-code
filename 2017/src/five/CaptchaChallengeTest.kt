package five

import junit.framework.TestCase

import org.junit.Assert
import org.junit.Test

class CaptchaChallengeTest : TestCase() {
    private val captchaChallenge: CaptchaChallenge = CaptchaChallenge()

    @Test
    fun testNextCharCaptcha_sumAll() {
        testNextCharCaptcha(777777, 42)
    }

    @Test
    fun testNextCharCaptcha_doubles() {
        testNextCharCaptcha(1122, 3)
    }

    @Test
    fun testNextCharCaptcha_allDifferent() {
        testNextCharCaptcha(1234, 0)
    }

    @Test
    fun testNextCharCaptcha_wrapAround() {
        testNextCharCaptcha(91212129, 9)
    }

    private fun testNextCharCaptcha(numbers: Int, expectedResult: Int) {
        val result = this.captchaChallenge.nextCharCaptcha(numbers.toString())
        Assert.assertEquals(expectedResult, result)
    }

    @Test
    fun testHalfwayCharCaptcha_sumAll() {
        testHalfwayCharCaptcha(777777, 42)
    }

    @Test
    fun testHalfwayCharCaptcha_interleave_right() {
        testHalfwayCharCaptcha(1212, 6)
    }

    @Test
    fun testHalfwayCharCaptcha_interleave_wrong() {
        testHalfwayCharCaptcha(1221, 0)
    }

    @Test
    fun testHalfwayCharCaptcha_twos() {
        testHalfwayCharCaptcha(123425, 4)
    }

    @Test
    fun testHalfwayCharCaptcha_ones() {
        testHalfwayCharCaptcha(12131415, 4)
    }

    private fun testHalfwayCharCaptcha(numbers: Int, expectedResult: Int) {
        val result = this.captchaChallenge.halfwayCharCaptcha(numbers.toString())
        Assert.assertEquals(expectedResult, result)
    }
}
