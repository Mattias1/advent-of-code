package twenty

import junit.framework.TestCase
import org.junit.Assert
import java.util.*

class DuetChallengeTest : TestCase() {
    private val challenge = DuetChallenge()

    fun testFirstRecoveryFrequency() {
        val input = simpleMusicInput()
        val result = challenge.firstRecoveryFrequency(input)
        Assert.assertEquals(4, result)
    }

    fun testTheCakeIsALie() {
        val input = simpleThreadingInput()
        val result = challenge.theCakeIsALie(input)
        Assert.assertEquals(3, result)
    }

    fun testTheCakeIsALieOnMusicInput() {
        val input = simpleMusicInput()
        val result = challenge.theCakeIsALie(input)
        Assert.assertEquals(1, result)
    }

    fun testQueue() {
        val queue: Queue<Int> = ArrayDeque<Int>()
        queue.add(42)
        queue.add(37)
        Assert.assertEquals(2, queue.count())
        Assert.assertEquals(2, queue.count())

        val fortyTwo = queue.poll()
        Assert.assertEquals(42, fortyTwo)
        Assert.assertEquals(1, queue.count())

        queue.add(12)

        val thirtySeven = queue.poll()
        Assert.assertEquals(37, thirtySeven)
        Assert.assertEquals(1, queue.count())

        val twelve = queue.poll()
        Assert.assertEquals(12, twelve)
        Assert.assertEquals(0, queue.count())
    }

    private fun simpleMusicInput(): List<String> {
        return listOf(
                "set a 1", "add a 2", "mul a a", "mod a 5",
                "snd a",
                "set a 0", "rcv a", "jgz a -1",
                "set a 1",
                "jgz a -2"
        )
    }

    private fun simpleThreadingInput(): List<String> {
        return listOf(
                "snd 1", "snd 2", "snd p",
                "rcv a", "rcv b", "rcv c",
                "rcv d"
        )
    }
}