import kotlin.math.max
import kotlin.math.min

fun main() {
    data class Point(val x: Int, val y: Int) {
        constructor(coords: List<Int>) : this(coords[0], coords[1])
    }

    fun part1(input: List<String>): Int {
        val field: MutableMap<Point, Int> = HashMap()
        for (line in input) {
            val points = line.split(" -> ")
            val start = Point(points[0].split(',').map(String::toInt))
            val end = Point(points[1].split(',').map(String::toInt))
            var linePoints: Iterable<Point>? = null
            if (start.x == end.x && start.y != end.y) {
                val from = min(start.y, end.y)
                val to = max(start.y, end.y)
                linePoints = IntRange(from, to)
                    .map { Point(start.x, it) }
            } else if (start.x != end.x && start.y == end.y) {
                val from = min(start.x, end.x)
                val to = max(start.x, end.x)
                linePoints = IntRange(from, to)
                    .map { Point(it, start.y) }
            }
            linePoints?.forEach {
                if (field.containsKey(it)) {
                    var value = field[it]!!
                    value += 1
                    field[it] = value
                } else {
                    field[it] = 1
                }
            }
        }

        return field.count {
            it.value > 1
        }
    }

    fun part2(input: List<String>): Int {

        val field: MutableMap<Point, Int> = HashMap()
        for (line in input) {
            val points = line.split(" -> ")
            val start = Point(points[0].split(',').map(String::toInt))
            val end = Point(points[1].split(',').map(String::toInt))
            val linePoints = ArrayList<Point>()
            var x = start.x
            var y = start.y
            do {
                linePoints.add(Point(x, y))
                if (end.x > start.x) {
                    x++
                } else if (end.x < start.x) {
                    x--
                }
                if (end.y > start.y) {
                    y++
                } else if (end.y < start.y) {
                    y--
                }
            } while (x != end.x || y != end.y)
            linePoints.add(end)
            linePoints.forEach {
                if (field.containsKey(it)) {
                    var value = field[it]!!
                    value += 1
                    field[it] = value
                } else {
                    field[it] = 1
                }
            }
        }

        return field.count {
            it.value > 1
        }
    }

    val testInput = readInput("Day05_test")
    check(part1(testInput) == 5)
    check(part2(testInput) == 12)

    val input = readInput("Day05")
    println(part1(input))
    println(part2(input))
}
