package days

import kotlin.math.abs

class Day13(input: List<String>) {

    private val points = input.filter { it.isNotBlank() && !it.startsWith("fold") }.map { it.split(",").map { x -> x.toInt() } }
    private val folds = input.filter { it.startsWith("fold") }.map { s -> s.split("fold along ")[1].split("=") }

    fun partOne(): Any {
        var grid = points.map { it[0] to it[1] }.toSet()
        val (dir, n) = folds.first()

        grid = grid.map { (x, y) ->
            if (dir == "x") n.toInt() - abs(x - n.toInt()) to y
            else x to n.toInt() - abs(y - n.toInt())
        }.toSet()

        return grid.size
    }

    fun partTwo(): Any {
        var grid = points.map { it[0] to it[1] }.toSet()

        folds.forEach { (dir, n) ->
            grid = grid.map { (x, y) ->
                if (dir == "x") n.toInt() - abs(x - n.toInt()) to y
                else x to n.toInt() - abs(y - n.toInt())
            }.toSet()
        }

        val (x, y) = grid.maxOf { it.first } to grid.maxOf { it.second }
        (0..y).forEach { j -> println((0..x).joinToString(" ") { i -> if (i to j in grid) "#" else "." }) }

        return "PZEHRAER" // in my case
    }
}

fun main() {
    val input = readInput("Day13")
    println(Day13(input).partOne())
    println(Day13(input).partTwo())
}