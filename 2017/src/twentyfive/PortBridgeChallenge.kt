package twentyfive

fun portBridgeInput(): List<String> = listOf(
        "31/13", "34/4", "49/49", "23/37", "47/45", "32/4", "12/35", "37/30", "41/48", "0/47",
        "32/30", "12/5", "37/31", "7/41", "10/28", "35/4", "28/35", "20/29", "32/20", "31/43",
        "48/14", "10/11", "27/6", "9/24", "8/28", "45/48", "8/1", "16/19", "45/45", "0/4",
        "29/33", "2/5", "33/9", "11/7", "32/10", "44/1", "40/32", "2/45", "16/16", "1/18",
        "38/36", "34/24", "39/44", "32/37", "26/46", "25/33", "9/10", "0/29", "38/8", "33/33",
        "49/19", "18/20", "49/39", "18/39", "26/13", "19/32"
)

class PortBridgeChallenge {
    fun strongestBridge(input: List<String>): Int {
        val components = getComponents(input)
        return maxBridge(components, 0L, 0, 0, { it.strength })
    }

    fun strongestLongestBridge(input: List<String>): Int {
        val components = getComponents(input)
        val maxLength = maxBridge(components, 0L, 0, 0, { 1 })
        return maxBridge(components, 0L, 0, maxLength, { it.strength })
    }

    private fun getComponents(input: List<String>): List<Component> = input
            .map { it.split('/').map { it.toInt() } }
            .mapIndexed { i, s -> Component(s[0], s[1], 1L shl i) }

    private fun maxBridge(components: List<Component>, bits: Long, port: Int, minLength: Int, transformer: (Component) -> Int): Int {
        val possibleComponents = components.filter { it.isUnmarked(bits) && it.hasPort(port) }
        if (minLength > 0 && possibleComponents.isEmpty()) {
            return if (countMarks(bits) < minLength) Int.MIN_VALUE else 0
        }
        return possibleComponents
                .map {
                    transformer(it) + maxBridge(components, it.mark(bits), it.otherPort(port), minLength, transformer)
                }.max() ?: 0
    }

    private fun countMarks(bits: Long): Int = (0..62).count { bits and (1L shl it) != 0L }


    class Component(private val portA: Int, private val portB: Int, private val bitmask: Long) {
        fun isUnmarked(bits: Long): Boolean = bits and bitmask == 0L

        fun mark(bits: Long): Long = bits or bitmask

        val strength: Int get() = portA + portB

        fun hasPort(port: Int): Boolean = portA == port || portB == port

        fun otherPort(port: Int): Int = if (port == portA) portB else portA
    }
}