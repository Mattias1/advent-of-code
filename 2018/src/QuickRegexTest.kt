import junit.framework.TestCase
import org.junit.Assert
import org.junit.Test

class QuickRegexTest : TestCase() {
    @Test
    fun testNotFound_emptyList() {
        val result = parse("abc", "def")
        Assert.assertTrue(result.isEmpty())
    }

    @Test
    fun testNoGroups_emptyList() {
        val result = parse("abc", "abc")
        Assert.assertTrue(result.isEmpty())
    }

    @Test
    fun testGroups_matches() {
        val result = parse("abcdefghij", "ab(.*)ef(.*)ij").toTypedArray()
        Assert.assertArrayEquals(arrayOf("cd", "gh"), result)
    }

    @Test
    fun testBracesShortcut_matches() {
        val result = parse("abcdefghij", "ab{}ef{}ij").toTypedArray()
        Assert.assertArrayEquals(arrayOf("cd", "gh"), result)
    }

    @Test
    fun testBracesShortcut_ignoresSpaces() {
        val result = parse("abc def ghi", "{} {}").toTypedArray()
        Assert.assertArrayEquals(arrayOf("abc", "def"), result)
    }
}
