package days

class Day06 {

    private fun simulate(input: String, days: Int): Long {
        var fishTypes = Array<Long>(9) { 0 } // 9 "types" of fishes

        for (i in 0..8) {
            fishTypes[i] = input.split(",").count { it.toLong() == i.toLong() }.toLong()
        }

        for (i in 0 until days) {
            val newFishTypes = Array<Long>(9) { 0 }

            for (j in 0..8) {
                if (j == 0) {
                    newFishTypes[6] += fishTypes[j]
                    newFishTypes[8] += fishTypes[j]
                } else
                    newFishTypes[j-1] += fishTypes[j]
            }

            fishTypes = newFishTypes
        }

        return fishTypes.sumOf { it }
    }

    fun partOne(input: String): Any {
        return simulate(input = input, 80)
    }

    fun partTwo(input: String): Any {
        return simulate(input = input, 256)
    }
}

fun main() {
    val input = readInput("Day06")[0]
    println(Day06().partOne(input))
    println(Day06().partTwo(input))
}