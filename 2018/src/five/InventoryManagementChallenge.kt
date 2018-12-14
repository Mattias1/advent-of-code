package five

// Day 2
class InventoryManagementChallenge {
    fun checksum(input: List<String>): Int {
        val twos = input.count { hasOccurrences(it, 2) }
        val threes = input.count { hasOccurrences(it, 3) }
        return twos * threes
    }

    private fun hasOccurrences(id: String, numberOfOccurrences: Int): Boolean {
        val chars: List<Char> = id.toCharArray().toList()
        val group = chars.groupingBy { it }.eachCount()
        return group.containsValue(numberOfOccurrences)
    }

    fun commonLetters(input: List<String>): String {
        for ((n, word) in input.withIndex()) {
            for (i in n + 1 until input.size) {
                val result = sameChars(word, input[i])
                if (result.size == word.length - 1) {
                    return result.joinToString("")
                }
            }
        }
        return "ERROR!"
    }

    private fun sameChars(a: String, b: String): List<Char> {
        return a.zip(b).filter { it.first == it.second }.map { it.first }
    }
}