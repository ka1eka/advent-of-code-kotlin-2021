fun main() {
    fun part1(input: List<String>): Int {
        val ones = ArrayList<Int>(12)
        val zeros = ArrayList<Int>(12)
        for ((index, value) in input.withIndex()) {
            for ((bitIndex, bitValue) in value.withIndex()) {
                if (index == 0) {
                    ones.add(bitIndex, 0)
                    zeros.add(bitIndex, 0)
                }
                when (bitValue) {
                    '0' -> zeros[bitIndex] = zeros[bitIndex] + 1
                    '1' -> ones[bitIndex] = ones[bitIndex] + 1
                }
            }
        }

        var rateStr = ""
        var epsStr = ""
        for ((index, value) in zeros.withIndex()) {
            if (ones[index] > value) {
                rateStr += "1"
                epsStr += "0"
            } else {
                rateStr += "0"
                epsStr += "1"
            }
        }

        return rateStr.toInt(2) * epsStr.toInt(2)
    }

    fun part2(input: List<String>): Int {
        fun decide(input: List<String>, position: Int, criteria: Boolean): List<String> {
            val zeros = ArrayList<String>()
            val ones = ArrayList<String>()
            for (value in input) {
                when (value[position]) {
                    '0' -> zeros.add(value)
                    '1' -> ones.add(value)
                }
            }
            return if (ones.size > zeros.size) {
                if (criteria) {
                    ones
                } else {
                    zeros
                }
            } else if (ones.size < zeros.size) {
                if (criteria) {
                    zeros
                } else {
                    ones
                }
            } else {
                if (criteria) {
                    ones
                } else {
                    zeros
                }
            }
        }

        var ogrList = input
        var co2List = input
        var bitIndex = 0
        while (ogrList.size > 1) {
            ogrList = decide(ogrList, bitIndex, true)
            bitIndex++
        }
        bitIndex = 0
        while (co2List.size > 1) {
            co2List = decide(co2List, bitIndex, false)
            bitIndex++
        }

        return ogrList[0].toInt(2) * co2List[0].toInt(2)
    }

    val testInput = readInput("Day03_test")
    check(part1(testInput) == 198)
    check(part2(testInput) == 230)

    val input = readInput("Day03")
    println(part1(input))
    println(part2(input))
}
