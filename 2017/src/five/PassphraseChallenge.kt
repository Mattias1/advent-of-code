package five

// Day 4
class PassphraseChallenge {
    fun isValidPassphrase(phrase: String): Boolean {
        val words = getWords(phrase)
        return words.count() == words.distinct().count()
    }

    fun isValidPassphraseAnagramWise(phrase: String): Boolean {
        val words = getWords(phrase)
        for (i in (0..words.lastIndex - 1)) {
            for (j in (i + 1..words.lastIndex)) {
                if (isAnagram(words[i], words[j])) {
                    return false
                }
            }
        }
        return true
    }

    private fun getWords(phrase: String): List<String> {
        return phrase.split(' ').filter { it.isNotEmpty() }.toList()
    }

    private fun isAnagram(a: String, b: String): Boolean {
        val aSorted: String = a.toCharArray().sorted().joinToString("")
        val bSorted: String = b.toCharArray().sorted().joinToString("")
        return aSorted == bSorted
    }
}