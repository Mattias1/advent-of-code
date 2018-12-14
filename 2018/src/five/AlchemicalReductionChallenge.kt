package five

import kotlin.math.absoluteValue
import kotlin.math.max

class AlchemicalReductionChallenge {
    fun fullyReducedPolymerCount(input: String): Int {
        val polymer: MutableList<Char> = input.toCharArray().toMutableList()
        return reducePolymerCount(polymer)
    }

    fun smartlyReducedPolymerCount(input: String): Int {
        val counts = "abcdefghijklmnopqrstuvwxyz".map {
            val reducedInput = input.replace("[$it${it.toUpperCase()}]".toRegex(), "").toCharArray().toMutableList()
            return@map reducePolymerCount(reducedInput)
        }
        return counts.min()!!
    }

    private fun reducePolymerCount(polymer: MutableList<Char>): Int {
        val difference = 'a' - 'A'
        var i = 0
        while (i < polymer.size - 1) {
            if ((polymer[i] - polymer[i + 1]).absoluteValue == difference) {
                polymer.removeAt(i + 1)
                polymer.removeAt(i)
                i = max(0, i - 1)
            } else {
                i++
            }
        }
        return polymer.size
    }

    private fun reduceOneType(input: String, c: Char): MutableList<Char> {
        val lowercaseInput = input.toLowerCase()
        val polymer = input.toCharArray().toMutableList()
        for (i in polymer.size - 1..0) {
            if (lowercaseInput[i] == c) {
                polymer.removeAt(i)
            }
        }
        return polymer
    }
}