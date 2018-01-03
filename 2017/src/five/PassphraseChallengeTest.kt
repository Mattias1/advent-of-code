package five

import junit.framework.TestCase
import org.junit.Assert
import org.junit.Test

class PassphraseChallengeTest : TestCase() {
    val challenge = PassphraseChallenge()

    @Test
    fun testValidPassword() {
        val result = challenge.isValidPassphrase("aa bb cc dd ee")

        Assert.assertTrue(result)
    }

    @Test
    fun testValidPasswordWithContains() {
        val result = challenge.isValidPassphrase("aa bb cc dd aaa")

        Assert.assertTrue(result)
    }

    @Test
    fun testInValidPassword() {
        val result = challenge.isValidPassphrase("aa bb cc dd aa")

        Assert.assertFalse(result)
    }

    @Test
    fun testIsNoAnagram() {
        val result = challenge.isValidPassphraseAnagramWise("abcde fghij")

        Assert.assertTrue(result)
    }

    @Test
    fun testPartialAnagramsAreValid() {
        val result = challenge.isValidPassphraseAnagramWise("a ab abc abd abf abj")

        Assert.assertTrue(result)
    }

    @Test
    fun testIsAnagram() {
        val result = challenge.isValidPassphraseAnagramWise("abcde xyz ecdab")

        Assert.assertFalse(result)
    }
}