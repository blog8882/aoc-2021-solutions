package days

private const val ENTRIES = 25
private const val SIZE = 5

class Day04 {

    data class Entry(val number: Int, var marked: Boolean)

    private fun isBingo(cols: List<Entry>, rows: List<Entry>): Boolean {
        return cols.all { it.marked } || rows.all { it.marked }
    }

    private fun getResults(input: List<String>): List<Int> {
        val bingoFields = mutableListOf<Int>()
        val results = mutableListOf<Int>()
        val markNumbers = input
            .first()
            .split(",")
            .map { it.trim().toInt() }
        val entries = input.subList(2, input.size).flatMap { line ->
            if (line.isNotEmpty()) {
                line.split(" ")
                    .filter { it.isNotEmpty() }
                    .map { Entry(it.trim().toInt(), false) }
            } else
                emptyList()
        }

        markNumbers.forEach { markNumber ->
            entries.forEachIndexed { i, entry ->
                if (entry.number == markNumber) {
                    entry.marked = true

                    val index = i / ENTRIES
                    val start = index * ENTRIES
                    val (col, row) = listOf((i - start) % SIZE, (i - start) / SIZE)

                    val cols = (0 until SIZE).map { entries[start + it * SIZE + col] }
                    val rows = entries.subList(start + row * SIZE, start + row * SIZE + SIZE)

                    if (isBingo(cols, rows)) {
                        val field = entries.subList(start, start + ENTRIES)
                        val unmarkedEntries = field.filter { !it.marked }.sumOf { it.number }

                        if (!bingoFields.contains(index)) {
                            bingoFields += index
                            results += unmarkedEntries * markNumber
                        }
                    }
                }
            }
        }

        return results
    }

    fun partOne(input: List<String>): Any {
        return getResults(input).first()
    }

    fun partTwo(input: List<String>): Any {
        return getResults(input).last()
    }
}

fun main() {
    val input = readInput("Day04")
    println(Day04().partOne(input))
    println(Day04().partTwo(input))
}