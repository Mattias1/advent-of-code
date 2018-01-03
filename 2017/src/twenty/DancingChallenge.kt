package twenty

class DancingChallenge {
    fun danceResult(input: String): String {
        val moves = getMoves(input)
        var result = "abcdefghijklmnop".toCharArray()

        moves.forEach { result = it.dance(result) }

        return result.joinToString("")
    }

    fun danceDanceDanceResult(input: String, repeat: Int = 1_000_000_000): String {
        val moves = getMoves(input)
        var result = "abcdefghijklmnop".toCharArray()
        var history = mutableListOf(result.joinToString(""))

        var searchLoop = true
        var n = 0
        while (n < repeat) {
            moves.forEach { result = it.dance(result) }
            n++
            if (searchLoop) {
                val s = result.joinToString("")
                val i = history.indexOf(s)
                if (i > -1) {
                    val loopsize = n - i
                    n = repeat - (repeat % loopsize)
                    searchLoop = false
                }
                history.add(s)
            }
        }

        return result.joinToString("")
    }

    private fun getMoves(input: String): List<Move> =
            input.filter { it != ' ' }.split(',').map { Move(it) }

    class Move {
        val type: Char
        val num1: Int
        val num2: Int
        val name1: Char
        val name2: Char

        constructor(input: String) {
            type = input[0]
            val s = input.substring(1).split('/')
            if (type == 'p') {
                num1 = 0
                num2 = 0
                name1 = s[0][0]
                name2 = s[1][0]
            } else {
                num1 = s[0].toInt()
                num2 = if (type == 'x') s[1].toInt() else 0
                name1 = ' '
                name2 = ' '
            }
        }

        fun dance(result: CharArray): CharArray {
            when (type) {
                's' -> return@dance (result.takeLast(num1) + result.dropLast(num1)).toCharArray()
                'x' -> return@dance result.swap(num1, num2)
                'p' -> {
                    val a = result.indexOf(name1)
                    val b = result.indexOf(name2)
                    return@dance result.swap(a, b)
                }
                else -> throw Exception("Unknown type")
            }
        }

        private fun CharArray.swap(a: Int, b: Int): CharArray {
            val temp = this[a]
            this[a] = this[b]
            this[b] = temp
            return this
        }
    }
}
