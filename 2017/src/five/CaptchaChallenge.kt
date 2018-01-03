package five

// Day 1
class CaptchaChallenge {
    fun nextCharCaptcha(input: String): Int {
        val numbers = toNumberList(input)
        numbers.add(numbers.first())

        val end = numbers.count() - 2
        return (0..end)
                .filter { numbers[it] == numbers[it + 1] }
                .sumBy { numbers[it] }
    }

    fun halfwayCharCaptcha(input: String): Int {
        val numbers = toNumberList(input)

        val end = numbers.count() - 1
        val len = numbers.count()
        val halfLen = len / 2
        return (0..end)
                .filter { numbers[it] == numbers[(it + halfLen) % len] }
                .sumBy { numbers[it] }
    }

    private fun toNumberList(input: String): MutableList<Int> {
        return input.toCharArray().map { Character.getNumericValue(it) }.toMutableList()
    }
}
