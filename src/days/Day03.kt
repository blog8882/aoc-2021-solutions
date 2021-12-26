package days

class Day03 {

    private fun getMostCommonBit(list: List<String>, index: Int): Char {
        var count = 0
        list.forEach { count += if (it.toCharArray()[index] == '1') 1 else -1 }
        return if (count >= 0) '1' else '0'
    }

    fun partOne(input: List<String>): Any {
        var (gamma, epsilon) = listOf("", "")

        input.first().toCharArray().forEachIndexed { i, _ ->
            gamma += if (getMostCommonBit(input, i) == '1') '0' else '1'
            epsilon += getMostCommonBit(input, i)
        }

        return Integer.parseInt(gamma, 2) * Integer.parseInt(epsilon, 2)
    }

    fun partTwo(input: List<String>): Any {
        var inputClone = input.toMutableList()

        run oxygen@ {
            input.first().toCharArray().forEachIndexed { i, _ ->
                val mostCommon = getMostCommonBit(inputClone, i)

                inputClone.toMutableList().forEach {
                    if (it[i] != mostCommon)
                        inputClone.remove(it)

                    if (inputClone.size == 1)
                        return@oxygen
                }
            }
        }
        val oxygen = inputClone.first()

        inputClone = input.toMutableList()

        run scrubber@ {
            input.first().toCharArray().forEachIndexed { i, _ ->
                val mostCommon = getMostCommonBit(inputClone, i)

                inputClone.toMutableList().forEach {
                    if (it[i] != if (mostCommon == '1') '0' else '1')
                        inputClone.remove(it)

                    if (inputClone.size == 1)
                        return@scrubber
                }
            }
        }
        val scrubber = inputClone.first()

        return Integer.parseInt(oxygen, 2) * Integer.parseInt(scrubber, 2)
    }
}

fun main() {
    val input = readInput("Day03")
    println(Day03().partOne(input))
    println(Day03().partTwo(input))
}