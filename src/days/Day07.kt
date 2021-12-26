package days

class Day07(input: String) {

    private val maxHorizontalPos = input.split(",").maxOf { it.toInt() }

    private fun getMinimumFuel(input: String, increment: Boolean): Int {
        val fuel = Array(maxHorizontalPos) { 0 }

        for (x2 in 1..maxHorizontalPos) {
            input.split(",").map { it.toInt() }.forEach { x1 ->
                var x = x1
                var incrementAmount = 1

                while (x != x2) {
                    x += if (x1 - x2 > 0) -1 else 1
                    fuel[x2-1] += incrementAmount
                    if (increment) incrementAmount++
                }
            }
        }

        return fuel.minOrNull()!!
    }

    fun partOne(input: String): Any {
        return getMinimumFuel(input, increment = false)
    }

    fun partTwo(input: String): Any {
        return getMinimumFuel(input, increment = true)
    }
}

fun main() {
    val input = readInput("Day07")[0]
    println(Day07(input).partOne(input))
    println(Day07(input).partTwo(input))
}