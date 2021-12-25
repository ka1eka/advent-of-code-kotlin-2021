import java.util.*

fun main() {
    fun part1(input: List<String>): Int {
        var result = 0
        var previous = 0
        for ((index, value) in input.withIndex()) {
            val current = value.toInt()
            if (index > 0) {
                if (current > previous) {
                    result++
                }
            }
            previous = current
        }
        return result
    }

    fun part2(input: List<String>): Int {
        var result = 0
        var previousSum = 0
        val window = LinkedList<Int>()
        for (value in input) {
            val current = value.toInt()
            window.add(current)
            if (window.size > 3) {
                val removed = window.removeFirst()
                val currentSum = previousSum - removed + current
                if (currentSum > previousSum) {
                    result++
                }
                previousSum = currentSum
            } else if (window.size == 3) {
                previousSum = window[0] + window[1] + window[2]
            }
        }

        return result
    }

    val testInput = readInput("Day01_test")
    check(part1(testInput) == 7)
    check(part2(testInput) == 5)

    val input = readInput("Day01")
    println(part1(input))
    println(part2(input))
}
