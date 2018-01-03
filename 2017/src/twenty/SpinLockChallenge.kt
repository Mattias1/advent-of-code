package twenty

class SpinLockChallenge {
    fun valueAfterStop(steps: Int, repeat: Int = 2017): Int {
        val list = mutableListOf(0)
        var pos = 0
        for (n in 1..repeat) {
            pos = (pos + steps) % list.count()
            list.add(pos + 1, n)
            pos++
        }
        return list[(pos + 1) % list.count()]
    }

    fun valueAfterZero(steps: Int, repeat: Int = 50_000_000): Int {
        var valueAfterZero = 0
        var indexOfZero = 0

        var pos = 0
        for (n in 1..repeat) {
            pos = (pos + steps) % n
            if (pos == indexOfZero) {
                valueAfterZero = n
            }
            if (pos < indexOfZero || pos == n) {
                indexOfZero++
            }
            pos++
        }
        return valueAfterZero
    }
}