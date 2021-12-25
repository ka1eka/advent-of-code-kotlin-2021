fun main() {
    class Card(cardIndex: Int, input: List<String>) {
        private val _values: List<MutableList<String?>>

        init {
            val line = cardIndex * 6 + 2
            _values = input.drop(line)
                .take(5)
                .map {
                    ArrayList(it.split(' ').filter(String::isNotEmpty))
                }
        }

        private var _won: Boolean = false
        private var _score: Int? = null
        val won: Boolean
            get() {
                return _won
            }

        fun cross(value: String) {
            var updated = false
            for (row in _values) {
                val valueIndex = row.indexOf(value)
                if (valueIndex != -1) {
                    updated = true
                    row[valueIndex] = null
                    if (row.all { it == null }) {
                        _won = true
                        break
                    }
                }
            }
            if (updated && !_won) {
                val emptyCols = ArrayList<Int>()
                for ((rowIndex, row) in _values.withIndex()) {
                    if (rowIndex == 0) {
                        for ((colIndex, cell) in row.withIndex()) {
                            if (cell == null) {
                                emptyCols.add(colIndex)
                            }
                        }
                    } else {
                        emptyCols.removeIf {
                            row[it] != null
                        }
                    }
                    if (emptyCols.isEmpty()) {
                        break
                    }
                }
                if (emptyCols.isNotEmpty()) {
                    _won = true
                }
            }
        }

        val score: Int
            get() {
                if (!_won) {
                    return 0
                }
                val localScore = _score
                if (localScore != null) {
                    return localScore
                }
                var score = 0
                for (row in _values) {
                    for (cell in row) {
                        if (cell != null) {
                            score += cell.toInt()
                        }
                    }
                }
                _score = score
                return score
            }
    }

    fun part1(input: List<String>): Int {
        val cards = IntRange(0, (input.size - 1) / 6 - 1)
            .map { cardIndex -> Card(cardIndex, input) }

        val numbers = input[0].split(',')
        for (number in numbers) {
            for (card in cards) {
                card.cross(number)
                if (card.won) {
                    return card.score * number.toInt()
                }
            }
        }

        return 0
    }

    fun part2(input: List<String>): Int {
        var cards = IntRange(0, (input.size - 1) / 6 - 1)
            .map { cardIndex -> Card(cardIndex, input) }

        val numbers = input[0].split(',')
        for (number in numbers) {
            for (card in cards) {
                card.cross(number)
            }
            if (cards.size == 1 && cards[0].won) {
                return cards[0].score * number.toInt()
            }
            cards = cards.filterNot { it.won }
        }

        return 0
    }

    val testInput = readInput("Day04_test")
    check(part1(testInput) == 4512)
    check(part2(testInput) == 1924)

    val input = readInput("Day04")
    println(part1(input))
    println(part2(input))
}
