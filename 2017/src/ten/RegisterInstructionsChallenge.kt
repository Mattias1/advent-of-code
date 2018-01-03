package ten

import kotlin.math.max

class RegisterInstructionsChallenge {
    fun getHighestRegister(input: List<String>): Int {
        val instructions = getInstructions(input)
        val registers = getRegisters(instructions)

        instructions.forEach {
            if (it.isConditionOk(registers)) {
                it.executeOperation(registers)
            }
        }

        return registers.values.max()!!
    }

    private fun getRegisters(instructions: List<RegisterInstruction>): MutableMap<String, Int> {
        return instructions
                .map { it.name }
                .distinct()
                .associateBy({ it }, { 0 })
                .toMutableMap()
    }

    private fun getInstructions(input: List<String>): List<RegisterInstruction> {
        return input.map { RegisterInstruction(it) }
    }

    fun getHighestRegisterEver(input: List<String>): Int {
        val instructions = getInstructions(input)
        val registers = getRegisters(instructions)

        var maxEver = 0

        instructions.forEach {
            if (it.isConditionOk(registers)) {
                it.executeOperation(registers)

                maxEver = max(maxEver, registers[it.name]!!)
            }
        }

        return maxEver
    }
}

class RegisterInstruction {
    val name: String
    private val modifier: String
    private val amount: Int
    private val conditionName: String
    private val conditionOperator: String
    private val conditionValue: Int

    constructor(input: String) {
        val inputs = input.split(' ').filter { it.isNotBlank() }

        name = inputs[0]
        modifier = inputs[1]
        amount = inputs[2].toInt()
        conditionName = inputs[4]
        conditionOperator = inputs[5]
        conditionValue = inputs[6].toInt()
    }

    fun isConditionOk(registers: MutableMap<String, Int>): Boolean {
        val otherValue = registers[conditionName] ?: 0

        return when (conditionOperator) {
            "==" -> otherValue == conditionValue
            "!=" -> otherValue != conditionValue
            ">" -> otherValue > conditionValue
            "<" -> otherValue < conditionValue
            ">=" -> otherValue >= conditionValue
            "<=" -> otherValue <= conditionValue
            else -> throw Exception("Unknown operator $conditionOperator")
        }
    }

    fun executeOperation(registers: MutableMap<String, Int>) {
        if (modifier == "inc") {
            registers[name] = registers[name]!! + amount
        } else if (modifier == "dec") {
            registers[name] = registers[name]!! - amount
        }
    }
}