package twentyfive

import twenty.DuetProgram

class CoprocessorConflagrationChallenge {
    companion object {
        fun coprocessorConflagrationInput() = listOf(
                "set b 84", "set c b", "jnz a 2", "jnz 1 5", "mul b 100", "sub b -100000", "set c b", "sub c -17000", "set f 1", "set d 2",
                "set e 2", "set g d", "mul g e", "sub g b", "jnz g 2", "set f 0", "sub e -1", "set g e", "sub g b", "jnz g -8",
                "sub d -1", "set g d", "sub g b", "jnz g -13", "jnz f 2", "sub h -1", "set g b", "sub g c", "jnz g 2", "jnz 1 3",
                "sub b -17", "jnz 1 -23"
        )
    }

    fun countMulInstructions(input: List<String>): Int {
        val program = DuetProgram(input, 0, 0, "abcdefgh")

        for (n in 0..100_000) {
            program.coprocessorStep() ?: return program.totalMulInstructions
        }
        return -1
    }

    fun finalHValue(input: List<String>): Long {
        var h = 0L

        var b = 84 * 100 + 100000
        val c = b + 17000

        while (true) {
            var f = true
            for (d in 2..b) {
                if (b % d == 0)  // This used to be a loop
                    f = false
            }

            if (!f)
                h++
            if (b == c)
                break
            b += 17
        }

        return h
    }
}