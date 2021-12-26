package days

class Day01 {
    fun partOne(input: List<String>): Int {
        var previous = Int.MAX_VALUE
        var increments = 0

        input.map { it.toInt() }.forEach { if (it > previous) increments++; previous = it }

        return increments
    }

    fun partTwo(input: List<String>): Int {
        var previous = Int.MAX_VALUE
        var increments = 0

        for ((i, x) in input.withIndex()) {
            var sum = input.subList(i, i + 3).sumOf { it.toInt() }

            if (sum > previous)
                increments++

            previous = sum

            if (i+3 > input.size-1)
                break
        }

        return increments
    }
}

fun main() {
    val input = readInput("Day01")
    println(Day01().partOne(input))
    println(Day01().partTwo(input))
}