package twenty

class DuetChallenge {
    fun firstRecoveryFrequency(input: List<String>): Long {
        val program = DuetProgram(input)

        for (n in 0..100_000) {
            val instruction = program.musicStep()
            if (instruction.key == "rcv" && instruction.x != 0L) {
                return@firstRecoveryFrequency program.lastFrequency
            }
        }
        return -1L
    }

    fun theCakeIsALie(input: List<String>): Int {
        val zero = DuetProgram(input, 0)
        val one = DuetProgram(input, 1)
        zero.otherProgram = one
        one.otherProgram = zero

        var activeProgram = one

        for (n in 0..10_000_000) {
            if (activeProgram.isWaiting) {
                activeProgram = activeProgram.otherProgram!!
                if (activeProgram.isWaiting) {
                    return@theCakeIsALie one.totalSentItems
                }
            }

            activeProgram.executionStep()
        }
        return -1
    }
}