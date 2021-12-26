package days

import kotlin.math.abs
import kotlin.math.max

class Day05 {

    private fun countOverlaps(input: List<String>, diagonals: Boolean): Int {
        val diagram = Array(1000) { Array(1000) { 0 } }

        input.forEach { line ->
            val pairs = line.split(" -> ")
            val (x1, y1) = pairs[0].split(",").map { it.toInt() }
            val (x2, y2) = pairs[1].split(",").map { it.toInt() }

            val dx = x2 - x1
            val dy = y2 - y1

            (0..max(abs(dx), abs(dy))).forEach { i ->
                val x = x1 + (if (dx > 0) 1 else (if (dx < 0) -1 else 0)) * i
                val y = y1 + (if (dy > 0) 1 else (if (dy < 0) -1 else 0)) * i

                if (diagonals)
                    diagram[y][x] = diagram[y][x] + 1
                else if (dx == 0 || dy == 0)
                    diagram[y][x] = diagram[y][x] + 1
            }
        }

        return diagram.flatten().count { it >= 2 }
    }

    fun partOne(input: List<String>): Any {
        return countOverlaps(input = input, diagonals = false)
    }

    fun partTwo(input: List<String>): Any {
        return countOverlaps(input = input, diagonals = true)
    }
}

fun main() {
    val input = readInput("Day05")
    println(Day05().partOne(input))
    println(Day05().partTwo(input))
}