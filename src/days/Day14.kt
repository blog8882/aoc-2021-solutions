package days

class Day14 {

    private fun solve(input: List<String>, times: Int): Long {
        val map = input.filter { it.contains(" -> ") }.map { it.split(" -> ") }.associate { it[0] to it[1] }
        var pairs = mutableMapOf<String, Long>()
        val chars = mutableMapOf<Char, Long>()

        input.first().windowed(2).forEach { pairs.increment(it) }

        repeat(times) {
            val temp = mutableMapOf<String, Long>()

            pairs.forEach { (k, v) ->
                temp.increment(k[0] + map[k]!!, v)
                temp.increment(map[k]!! + k[1], v)
            }

            pairs = temp
        }

        pairs.forEach { (k, v) -> chars.increment(k[0], v) }
        chars.increment(input.first().last())

        return chars.values.sorted().let { it.last() - it.first() }
    }

    private fun <K> MutableMap<K, Long>.increment(key: K, by: Long = 1) {
        put(key, getOrDefault(key, 0) + by)
    }

    fun partOne(input: List<String>): Any {
        return solve(input, 10)
    }

    fun partTwo(input: List<String>): Any {
        return solve(input, 40)
    }
}

fun main() {
    val input = readInput("Day13")
    println(Day14().partOne(input))
    println(Day14().partTwo(input))
}