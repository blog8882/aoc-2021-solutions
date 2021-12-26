package days

class Day09 {

    private var seen: MutableList<Pair<Int, Int>> = mutableListOf()

    @OptIn(ExperimentalStdlibApi::class)
    private fun getNeighbors(field: Array<Array<Int>>, x: Int, y: Int): List<Pair<Int, Int>> {
        val offsets = listOf(-1 to 0, 1 to 0, 0 to -1, 0 to 1)

        return buildList {
            offsets.forEach { off -> try { field[y + off.second][x + off.first]; add(Pair(x + off.first, y + off.second)) } catch (_: ArrayIndexOutOfBoundsException) { } }
        }
    }

    private fun getRiskLevel(field: Array<Array<Int>>, x: Int, y: Int): Int =
        if (getNeighbors(field, x, y).all { field[it.second][it.first] > field[y][x] }) (field[y][x] + 1) else 0

    private fun getBasinSize(field: Array<Array<Int>>, x: Int, y: Int, prev: Int = 0): Int {
        var basinSize = prev

        getNeighbors(field, x, y).forEach {
            if (field[it.second][it.first] != 9 && it !in seen) {
                seen += it
                basinSize = getBasinSize(field, it.first, it.second, ++basinSize)
            }
        }

        return basinSize
    }

    fun partOne(input: List<String>): Any {
        val field = Array(input.size) { Array(input[0].length) { 0 } }
        input.forEachIndexed { index, s -> field[index] = s.toCharArray().map { it.digitToInt() }.toTypedArray() }

        return field.mapIndexed { y, rows -> rows.mapIndexed { x, _ -> x to y }.sumOf { getRiskLevel(field, it.first, it.second) } }.sum()
    }

    fun partTwo(input: List<String>): Any {
        val field = Array(input.size) { Array(input[0].length) { 0 } }
        val basinSizes = mutableListOf<Int>()

        input.forEachIndexed { index, s -> field[index] = s.toCharArray().map { it.digitToInt() }.toTypedArray() }

        val lowPoints = field.mapIndexed { y, rows -> rows.mapIndexed { x, _ -> x to y }.filter { getRiskLevel(field, it.first, it.second) > 0 } }.flatten()
        lowPoints.forEach { basinSizes.add(getBasinSize(field, it.first, it.second)) }

        return basinSizes.sortedDescending().take(3).reduce(Int::times) // multiplies every element
    }
}

fun main() {
    val input = readInput("Day09")
    println(Day09().partOne(input))
    println(Day09().partTwo(input))
}