
import java.io.File

var sum = 0
fun main() {
    println(Day1Part1().trebuchetPart1("src/Day1/input.txt"))
}

class Day1Part1 {
    fun trebuchetPart1(filename: String): Int {
        File(filename).forEachLine { sum += extractNumbers(it).toInt() }
        return sum
    }

    private fun extractNumbers(line: String): String {

        var result = ""
        val numberList = line.filter { it.isDigit() }.map { it.toString().toInt() }
        if (numberList.isNotEmpty()) {
            val firstNumber = numberList[0].toString()
            if (numberList.size > 1) {
                result = firstNumber + numberList[numberList.size - 1].toString()
            } else {
                result = firstNumber + firstNumber
            }
        }
        return result
    }

}