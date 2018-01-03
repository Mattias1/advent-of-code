package fifteen

const val multiplierA = 16807
const val multiplierB = 48271

class DuelingGeneratorsChallenge {
    fun numberOfMatches(startA: Long, startB: Long): Int {
        var a: Long = startA
        var b: Long = startB
        var counter = 0
        for (i in 0..40_000_000) {
            if (matches(a, b)) {
                counter++
            }
            a = (a * multiplierA) % Int.MAX_VALUE
            b = (b * multiplierB) % Int.MAX_VALUE
        }
        return counter
    }

    fun numberOfAnnoyingMatches(startA: Long, startB: Long): Int {
        var a: Long = startA
        var b: Long = startB
        var counter = 0
        for (i in 0..5_000_000) {
            while (a and 3L != 0L) {
                a = (a * multiplierA) % Int.MAX_VALUE
            }
            while (b and 7L != 0L) {
                b = (b * multiplierB) % Int.MAX_VALUE
            }
            if (matches(a, b)) {
                counter++
            }
            a = (a * multiplierA) % Int.MAX_VALUE
            b = (b * multiplierB) % Int.MAX_VALUE
        }
        return counter
    }

    private fun matches(a: Long, b: Long): Boolean {
        return (0..15).all { (a shr it) and 1 == (b shr it) and 1 }
    }
}