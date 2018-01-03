package twentyfive

import junit.framework.TestCase
import org.junit.Assert
import kotlin.system.measureTimeMillis

class TuringMachineChallengeTest : TestCase() {
    private val challenge = TuringMachineChallenge()

    fun testDiagnosticChecksum() {
        val blueprint = getSimpleBlueprint()
        val result = challenge.diagnosticChecksum(6, blueprint)

        Assert.assertEquals(3, result)
    }

    fun testDiagnosticChecksumSpeed() {
        val time: Long = measureTimeMillis {
            val blueprint = getSimpleBlueprint()
            val result = challenge.diagnosticChecksum(6000, blueprint)
        }
        println("Total milliseconds: $time")
    }

    private fun getSimpleBlueprint(): List<TuringMachineStep> {
        return listOf(
                TuringMachineStep(true, 1, 'B'), TuringMachineStep(false, -1, 'B'),
                TuringMachineStep(true, -1, 'A'), TuringMachineStep(true, 1, 'A')
        )
    }
}