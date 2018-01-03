import kotlin.system.measureTimeMillis

fun main(args: Array<String>) {
    val time: Long = measureTimeMillis {
        val steps = twentyfive.TuringMachineStep.steps
        val blueprint = twentyfive.TuringMachineStep.blueprint
        val challenge = twentyfive.TuringMachineChallenge()
        val answer = challenge.diagnosticChecksum(steps, blueprint)

        println("Answer: $answer")
    }
    println("Total milliseconds: $time")
}
