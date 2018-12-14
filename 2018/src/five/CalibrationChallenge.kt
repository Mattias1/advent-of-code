package five

import java.lang.Exception

// Day 1
class CalibrationChallenge {
    fun resultingFrequency(input: List<String>): Int {
        return input.sumBy { difference(it) }
    }

    fun repeatingFrequency(input: List<String>): Int {
        val numbers = hashSetOf(0)
        var result = 0

        var i = 0
        while (true) {
            result += difference(input[i])
            i = (i + 1) % input.size

            if (!numbers.add(result)) {
                return result
            }
        }
    }

    private fun difference(mod: String): Int = when {
        mod[0] == '+' -> mod.substring(1).toInt()
        mod[0] == '-' -> -mod.substring(1).toInt()
        else -> throw Exception("Aaaah")
    }
}