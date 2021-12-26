package days

class Day02 {
    fun partOne(input: List<String>): Int {
        var (depth, horizontal) = listOf(0, 0)

        input.forEach {
            val (instruction, value) = it.split(" ")

            when (instruction) {
                "forward" -> horizontal += value.toInt()
                "down" -> depth += value.toInt()
                "up" -> depth -= value.toInt()
            }
        }

        return depth * horizontal
    }

    fun partTwo(input: List<String>): Int {
        var (depth, horizontal, aim) = listOf(0, 0, 0)

        input.forEach {
            val (instruction, value) = it.split(" ")

            when (instruction) {
                "forward" -> {
                    horizontal += value.toInt()
                    depth += aim * value.toInt()
                }
                "down" -> aim += value.toInt()
                "up" -> aim -= value.toInt()
            }
        }

        return depth * horizontal
    }
}

fun main() {
    val input = readInput("Day02")
    println(Day02().partOne(input))
    println(Day02().partTwo(input))
}