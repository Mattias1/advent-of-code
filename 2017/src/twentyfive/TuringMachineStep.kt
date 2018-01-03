package twentyfive

class TuringMachineStep {
    companion object {
        val steps = 12_523_873
        val blueprint = listOf<TuringMachineStep>(
                TuringMachineStep(true, 1, 'B'), TuringMachineStep(true, -1, 'E'),
                TuringMachineStep(true, 1, 'C'), TuringMachineStep(true, 1, 'F'),
                TuringMachineStep(true, -1, 'D'), TuringMachineStep(false, 1, 'B'),
                TuringMachineStep(true, 1, 'E'), TuringMachineStep(false, -1, 'C'),
                TuringMachineStep(true, -1, 'A'), TuringMachineStep(false, 1, 'D'),
                TuringMachineStep(true, 1, 'A'), TuringMachineStep(true, 1, 'C')
        )
    }

    val value: Boolean
    val dir: Int
    val state: Int

    constructor(value: Boolean, dir: Int, state: Char) {
        this.value = value
        this.dir = dir
        this.state = state - 'A'
    }
}