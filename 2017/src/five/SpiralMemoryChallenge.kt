package five

import kotlin.math.absoluteValue

// Day 3
class SpiralMemoryChallenge {
    fun manhattenDistanceToCenter(stepNumber: Int): Int {
        val step = getSteps(stepNumber)
        return step.X.absoluteValue + step.Y.absoluteValue
    }

    private fun getSteps(startingStep: SpiralStep, amount: Int): SpiralStep {
        var step = startingStep
        while (step.Index < amount) {
            step = step.Next()
            // debug(step)
        }
        return step
    }

    private fun getSteps(amount: Int): SpiralStep {
        return getSteps(SpiralStep(null), amount)
    }

    private fun debug(step: SpiralStep) {
        println("Index: ${step.Index}, Value: ${step.Value}, Coordinate: ${step.X}, ${step.Y}, Step: ${step.Step}, Maximum: ${step.Maximum}")
    }

    fun firstLarger(inputValue: Int): Int {
        val grid: MutableList<Int> = (1..10201).map { 0 }.toMutableList() // HACK: Make the list big enough
        var step = SpiralStep(grid)
        debug(step)
        while (step.Value <= inputValue) {
            step = step.Next()
            debug(step)
        }
        return step.Value
    }
}
