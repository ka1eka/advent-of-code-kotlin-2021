fun main() {
    fun part1(input: List<String>): Int {
        var x = 0
        var d = 0

        for (cmdStr in input) {
            val parts = cmdStr.split(' ')
            val cmd = parts[0]
            val value = parts[1].toInt()
            when (cmd) {
                "forward" -> x += value
                "down" -> d += value
                "up" -> d -= value
            }
        }

        return x * d
    }

    fun part2(input: List<String>): Int {
        var x = 0
        var d = 0
        var aim = 0

        for (cmdStr in input) {
            val parts = cmdStr.split(' ')
            val cmd = parts[0]
            val value = parts[1].toInt()
            when (cmd) {
                "forward" -> {
                    x += value
                    d += aim * value
                }
                "down" -> aim += value
                "up" -> aim -= value
            }
        }

        return x * d
    }

    val testInput = readInput("Day02_test")
    check(part1(testInput) == 150)
    check(part2(testInput) == 900)

    val input = readInput("Day02")
    println(part1(input))
    println(part2(input))
}
