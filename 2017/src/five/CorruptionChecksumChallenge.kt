package five

// Day 2
class CorruptionChecksumChallenge {
    fun differenceChecksum(input: Array<String>): Int {
        val numberList = this.toNumberList(input)
        return numberList
                .map { this.diffMaxMin(it) }
                .sum()
    }

    private fun toNumberList(input: Array<String>): List<List<Int>> {
        return input.map {
            it
                    .split(" ")
                    .filter { it.isNotEmpty() }
                    .map { it.toInt() }
        }
    }

    private fun diffMaxMin(numbers: List<Int>): Int {
        val min: Int = numbers.min()!!
        val max: Int = numbers.max()!!

        return max - min
    }

    fun divisionChecksum(input: Array<String>): Int {
        val numberList = this.toNumberList(input)
        return numberList
                .map { it.sortedDescending() }
                .map { this.firstIntDivision(it) }
                .sum()
    }

    private fun firstIntDivision(numbers: List<Int>): Int {
        // Assumes descending sort
        for (i in 0 until numbers.lastIndex) {
            for (j in i + 1..numbers.lastIndex) {
                val max: Int = numbers[i]
                val min: Int = numbers[j]

                if (max % min == 0) {
                    return max / min
                }
            }
        }
        throw Exception("No int division found")
    }
}
