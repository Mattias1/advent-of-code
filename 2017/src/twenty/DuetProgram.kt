package twenty

import java.util.*

class DuetProgram(private val _input: List<String>, p: Long = 0, a: Long = 0, registers: String = "abcdefghijklmnopqrstuvwxyz") {
    private val _registers = buildRegisters(p, a, registers)
    private val _queue: Queue<Long> = ArrayDeque<Long>()
    private var _step: Int = 0
    private var _lastFrequency: Long = 0
    private var _mulInstructionCount: Int = 0
    private var _totalSentItems: Int = 0
    var otherProgram: DuetProgram? = null

    // Back in the good ol' days when we didn't RTFM
    val lastFrequency: Long get() = _lastFrequency

    fun musicStep(): Instruction {
        val instruction = parseInstruction()
        val offsetNext = executeMusicInstruction(instruction)

        _step += offsetNext

        return instruction
    }

    private fun buildRegisters(p: Long = 0, a: Long = 0, registers: String): MutableMap<Char, Long> =
            registers.associateBy({ it }, {
                when (it) {
                    'p' -> p
                    'a' -> a
                    else -> 0L
                }
            }).toMutableMap()

    private fun parseInstruction(): Instruction {
        val params = (_input[_step] + ' ')
                .split(' ')
                .map { if (it == "") "0" else it }

        return Instruction(params[0], params[1][0], getParam(params[1]), getParam(params[2]))
    }

    private fun getParam(value: String): Long =
            _registers[value[0]] ?: value.toLong()

    private fun executeMusicInstruction(i: Instruction): Int {
        when (i.key) {
            "snd" -> _lastFrequency = i.x
            "set" -> _registers[i.nameX] = i.y
            "add" -> _registers[i.nameX] = i.x + i.y
            "mul" -> _registers[i.nameX] = i.x * i.y
            "mod" -> _registers[i.nameX] = i.x % i.y
            "rcv" -> {
            }
            "jgz" -> if (i.x != 0L) return@executeMusicInstruction i.y.toInt()
        }
        return 1
    }

    // We RTFM
    val isWaiting: Boolean get() = _step >= _input.count() || _input[_step].startsWith("rcv") && _queue.isEmpty()

    val totalSentItems: Int get() = _totalSentItems

    fun executionStep() {
        val instruction = parseInstruction()
        val offsetNext = executeRealInstruction(instruction)

        _step += offsetNext
    }

    private fun executeRealInstruction(i: Instruction): Int {
        when (i.key) {
            "snd" -> {
                otherProgram!!._queue.add(i.x)
                _totalSentItems++
            }
            "set" -> _registers[i.nameX] = i.y
            "add" -> _registers[i.nameX] = i.x + i.y
            "mul" -> _registers[i.nameX] = i.x * i.y
            "mod" -> _registers[i.nameX] = i.x % i.y
            "rcv" -> _registers[i.nameX] = _queue.poll()
            "jgz" -> if (i.x > 0L) return@executeRealInstruction i.y.toInt()
        }
        return 1
    }

    // Coprocessor logic (day 23)
    val totalMulInstructions: Int get() = _mulInstructionCount
    val hRegister: Long get() = _registers['h']!!

    fun coprocessorStep(): Instruction? {
        if (_step >= _input.count()) {
            return null
        }

        val instruction = parseInstruction()
        val offsetNext = executeCoprocessorInstruction(instruction)

        _step += offsetNext

        return instruction
    }

    private fun executeCoprocessorInstruction(i: Instruction): Int {
        when (i.key) {
            "set" -> _registers[i.nameX] = i.y
            "sub" -> _registers[i.nameX] = i.x - i.y
            "mul" -> {
                _mulInstructionCount++
                _registers[i.nameX] = i.x * i.y
            }
            "jnz" -> if (i.x != 0L) return@executeCoprocessorInstruction i.y.toInt()
        }
        return 1
    }

    class Instruction(val key: String, val nameX: Char, val x: Long, val y: Long)
}
