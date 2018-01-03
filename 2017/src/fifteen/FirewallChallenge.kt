package fifteen

// Day 13
class FirewallChallenge {
    fun calculateSeverity(input: List<String>): Int {
        val ranges = getRanges(input)

        return ranges.map { getPenalty(it.key, it.value) }.sum()
    }

    private fun getRanges(input: List<String>): Map<Int, Int> {
        return input.map {
            val split = it.split(": ").map { it.toInt() }
            Pair(split[0], split[1])
        }.toMap()
    }

    private fun getPenalty(depth: Int, range: Int): Int {
        return if (isHit(depth, range)) depth * range else 0
    }

    private fun isHit(depth: Int, range: Int, delay: Int = 0): Boolean {
        val picosecondsBeforeBackToZero = (range - 1) * 2
        return (depth + delay) % picosecondsBeforeBackToZero == 0
    }

    fun firstSafeTrip(input: List<String>): Int {
        val ranges = getRanges(input)

        var delay = 0
        while (isNotSafe(ranges, delay)) {
            delay++
        }
        return delay
    }

    private fun isNotSafe(ranges: Map<Int, Int>, delay: Int): Boolean {
        return ranges.any { isHit(it.key, it.value, delay) }
    }
}