import five.AlchemicalReductionChallenge
import five.AlchemicalReductionInput
import kotlin.system.measureTimeMillis

fun main(args: Array<String>) {
    val time: Long = measureTimeMillis {
        val challenge = AlchemicalReductionChallenge()
        val input = AlchemicalReductionInput().get()

        val result = challenge.smartlyReducedPolymerCount(input)

        println("Answer: $result")
    }
    println("Total milliseconds: $time")
}
