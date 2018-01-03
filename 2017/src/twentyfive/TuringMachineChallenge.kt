package twentyfive

const val TAPE_CENTER = 50000

class TuringMachineChallenge {
    fun diagnosticChecksum(steps: Int, blueprint: List<TuringMachineStep>): Int {
        val tape = MutableList(2 * TAPE_CENTER + 1, { false })
        var pos = TAPE_CENTER
        var state = 0

        for (n in 1..steps) {
            val step: TuringMachineStep = blueprint[2 * state + if (tape[pos]) 1 else 0]

            tape[pos] = step.value
            pos += step.dir
            state = step.state
        }
        return tape.count { it }
    }
}