package five

import parse
import java.lang.Exception

// Day 4
class GuardChallenge {
    fun longestAsleep(input: List<String>): Int {
        val sleepIntervals = buildSleepIntervals(input)

        val biggusDickus: GuardSleepIntervals = sleepIntervals.maxBy { it.totalSleepTime }!!
        val minute = biggusDickus.safestMinute()
        return biggusDickus.id * minute
    }

    fun popularMinute(input: List<String>): Int {
        val sleepIntervals = buildSleepIntervals(input)

        val biggusDickus = sleepIntervals.maxBy { it.safestMinuteValue() }!!
        val minute = biggusDickus.safestMinute()
        return biggusDickus.id * minute
    }

    private fun buildSleepIntervals(input: List<String>): List<GuardSleepIntervals> {
        val sleepIntervals = mutableListOf<GuardSleepIntervals>()
        var currentGuard: Int = -1
        var startInterval: Int = -1
        for (record in input.sorted()) {
            val parsed = parse(record, "\\[....-..-.. ..:{}\\] {} {}")
            val min = parsed[0].toInt()
            when (parsed[1]) {
                "Guard" -> {
                    val id = parsed[2].substring(1).toInt()
                    currentGuard = id
                    if (sleepIntervals.none { it.id == id }) {
                        sleepIntervals.add(GuardSleepIntervals(id))
                    }
                }
                "falls" -> {
                    if (currentGuard == -1) {
                        throw Exception("Aaaah!")
                    }
                    startInterval = min
                }
                "wakes" -> {
                    if (currentGuard == -1 || startInterval == -1) {
                        throw Exception("Aaaah!")
                    }
                    sleepIntervals.single { it.id == currentGuard }.addInterval(startInterval, min)
                    startInterval = -1
                }
            }
        }

        return sleepIntervals
    }
}

class GuardSleepIntervals {
    val id: Int
    private val intervals: MutableList<Pair<Int, Int>>
    var totalSleepTime: Int; private set

    constructor(id: Int) {
        this.id = id
        this.intervals = mutableListOf()
        this.totalSleepTime = 0
    }

    fun addInterval(min: Int, max: Int) {
        this.intervals.add(Pair(min, max))
        this.totalSleepTime += max - min
    }

    fun intervalCount(minute: Int): Int =
        intervals.count { minute in it.first until it.second }

    fun safestMinute(): Int =
        (0..59).maxBy { intervalCount(it) }!!

    fun safestMinuteValue(): Int =
        (0..59).map { intervalCount(it) }.max()!!
}
