package five

import junit.framework.TestCase
import org.junit.Assert
import org.junit.Test

class GuardChallengeTest : TestCase() {
    private val challenge: GuardChallenge = GuardChallenge()

    @Test
    fun testLongestAsleep_testcase() {
        val input = listOf(
            "[1518-11-01 00:00] Guard #10 begins shift",
            "[1518-11-05 00:03] Guard #99 begins shift", // moved this one up, so that the list is no longer in chronological order
            "[1518-11-01 00:05] falls asleep",
            "[1518-11-01 00:25] wakes up",
            "[1518-11-01 00:30] falls asleep",
            "[1518-11-01 00:55] wakes up",
            "[1518-11-01 23:58] Guard #99 begins shift",
            "[1518-11-02 00:40] falls asleep",
            "[1518-11-02 00:50] wakes up",
            "[1518-11-03 00:05] Guard #10 begins shift",
            "[1518-11-03 00:24] falls asleep",
            "[1518-11-03 00:29] wakes up",
            "[1518-11-04 00:02] Guard #99 begins shift",
            "[1518-11-04 00:36] falls asleep",
            "[1518-11-04 00:46] wakes up",
            "[1518-11-05 00:45] falls asleep",
            "[1518-11-05 00:55] wakes up"
        )
        val result = challenge.longestAsleep(input)
        Assert.assertEquals(240, result)
    }

    @Test
    fun testPopularMinute_testcase() {
        val input = listOf(
            "[1518-11-01 00:00] Guard #10 begins shift",
            "[1518-11-05 00:03] Guard #99 begins shift", // moved this one up, so that the list is no longer in chronological order
            "[1518-11-01 00:05] falls asleep",
            "[1518-11-01 00:25] wakes up",
            "[1518-11-01 00:30] falls asleep",
            "[1518-11-01 00:55] wakes up",
            "[1518-11-01 23:58] Guard #99 begins shift",
            "[1518-11-02 00:40] falls asleep",
            "[1518-11-02 00:50] wakes up",
            "[1518-11-04 00:02] Guard #99 begins shift",
            "[1518-11-03 00:05] Guard #10 begins shift",
            "[1518-11-03 00:24] falls asleep",
            "[1518-11-03 00:29] wakes up",
            "[1518-11-04 00:36] falls asleep",
            "[1518-11-04 00:46] wakes up",
            "[1518-11-05 00:45] falls asleep",
            "[1518-11-05 00:55] wakes up"
        )
        val result = challenge.popularMinute(input)
        Assert.assertEquals(4455, result)
    }

    @Test
    fun testTotalSleepTime_inclExcl() {
        val guard = GuardSleepIntervals(2)
        guard.addInterval(3, 5)
        guard.addInterval(5, 7)
        guard.addInterval(6, 9)
        Assert.assertEquals(7, guard.totalSleepTime)
    }

    @Test
    fun testIntervalCount_inclExcl() {
        val guard = GuardSleepIntervals(2)
        guard.addInterval(3, 5)
        guard.addInterval(5, 7)
        guard.addInterval(6, 9)
        val result = (0..9).map { guard.intervalCount(it) }.toTypedArray()
        val expected = arrayOf(0, 0, 0, 1, 1, 1, 2, 1, 1, 0)
        Assert.assertArrayEquals(expected, result)
    }

    @Test
    fun testSafestMinute_inclExcl() {
        val guard = GuardSleepIntervals(2)
        guard.addInterval(3, 5)
        guard.addInterval(4, 7)
        guard.addInterval(7, 9)
        val result = guard.safestMinute()
        Assert.assertEquals(4, result)
    }

    @Test
    fun testSafestMinuteValue_inclExcl() {
        val guard = GuardSleepIntervals(2)
        guard.addInterval(3, 5)
        guard.addInterval(4, 7)
        guard.addInterval(7, 9)
        val result = guard.safestMinuteValue()
        Assert.assertEquals(2, result)
    }
}