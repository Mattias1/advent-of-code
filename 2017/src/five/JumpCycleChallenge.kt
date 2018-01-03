package five

// Day 5
class JumpCycleChallenge {
    fun jumpsToExit(numbers: Array<Int>): Int {
        var counter = 0
        var pos = 0
        while (pos < numbers.size) {
            counter++
            numbers[pos]++
            pos += numbers[pos] - 1
        }
        return counter
    }

    fun weirdJumpsToExit(numbers: Array<Int>): Int {
        var counter = 0
        var pos = 0
        while (pos < numbers.size) {
            counter++
            val offset = numbers[pos]
            numbers[pos] += if (offset >= 3) -1 else 1
            pos += offset
        }
        return counter
    }
}