package days

class Day08 {

    fun partOne(input: List<String>): Int {
        return input.flatMap { line -> line.split("|")[1].trim().split(" ").map { it.length } }.count { it in listOf(2, 3, 4, 7) }
    }

    fun partTwo(input: List<String>): Int {
        val values = mutableListOf<Int>()

        input.forEach { line ->
            val (input, output) = line.split("|").map { it.trim() }.map { it.split(" ") }
            val lengthToChars = input.associate { it.length to it.toCharArray().toSet() }
            var value = ""

            output.forEach {
                val inputSet = it.toCharArray().toSet()
                val triple = Triple(it.length, inputSet.intersect(lengthToChars[4]!!).size, inputSet.intersect(lengthToChars[2]!!).size)

                value += when (triple.first) {
                    2 -> '1'
                    3 -> '7'
                    4 -> '4'
                    7 -> '8'
                    5 -> when (triple.second) {
                        2 -> '2'
                        3 -> if (triple.third == 1) '5' else '3'
                        else -> throw IllegalStateException()
                    }
                    6 -> when (triple.second) {
                        4 -> '9'
                        3 -> if (triple.third == 1) '6' else '0'
                        else -> throw IllegalStateException()
                    }
                    else -> throw IllegalStateException()
                }
            }

            values.add(value.toInt())
        }

        return values.sum()
    }
}

fun main() {
    val input = readInput("Day08")
    println(Day08().partOne(input))
    println(Day08().partTwo(input))
}