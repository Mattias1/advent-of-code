package ten

// Day 7
class MemoryBlocksChallenge {
    fun redistributionCycles(block: List<Int>): Int {
        var pastBlocks = mutableListOf(block)

        for (counter in 1..999999) {
            val i = getHighestIndex(pastBlocks.last())
            val b = redistributeBlock(pastBlocks, i)
            if (pastBlocks.any { it contentEquals b }) {
                return counter
            }
            pastBlocks.add(b)
        }

        return -1
    }

    private fun getHighestIndex(block: List<Int>): Int {
        return block.indexOf(block.max())
    }

    private fun redistributeBlock(pastBlocks: MutableList<List<Int>>, i: Int): List<Int> {
        val b = pastBlocks.last().toMutableList() // Let's hope this does a copy, not a cast :P
        var redistributionAmount = b[i]

        var j = i
        b[i] = 0
        while (redistributionAmount > 0) {
            j = (j + 1) % b.count()
            b[j]++
            redistributionAmount--
        }

        return b.toList()
    }

    private infix fun List<Int>.contentEquals(other: List<Int>): Boolean {
        return this.count() == other.count()
                && this.zip(other).all { it.first == it.second }
    }

    fun redistributionCycleSize(block: List<Int>): Int {
        var pastBlocks = mutableListOf(block)

        for (counter in 1..999999) {
            val i = getHighestIndex(pastBlocks.last())
            val b = redistributeBlock(pastBlocks, i)
            val firstIndex = pastBlocks.indexOfFirst { it contentEquals b }
            if (firstIndex >= 0) {
                return counter - firstIndex
            }
            pastBlocks.add(b)
        }

        return -1
    }
}