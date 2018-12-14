fun parse(input: String, pattern: String): List<String> {
    val regex = Regex(pattern.replace("{}", "([^\\s]*)"))
    val matchResult = regex.find(input)
    return matchResult?.groupValues?.drop(1) ?: listOf()
}