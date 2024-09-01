import kotlin.collections.HashMap

import java.io.File


val numbersMap = HashMap<String, String>()


fun main() {
  // MAP STRING VALUES TO INT VALUES
  numbersMap["one"] = "o1e"
  numbersMap["two"] = "t2o"
  numbersMap["three"] = "thr3e"
  numbersMap["four"] = "fo4r"
  numbersMap["five"] = "fi5e"
  numbersMap["six"] = "s6x"
  numbersMap["seven"] = "se7en"
  numbersMap["eight"] = "ei8ht"
  numbersMap["nine"] = "ni9e"

  println(Day1Part2().trebuchetPart2("src/Day1/input.txt"))

}

class Day1Part2 {
  fun trebuchetPart2(filename: String): Int {
    var sum=0
    File(filename).forEachLine { sum += extractNumbers(it).toInt() }
    return sum
  }

  private fun extractNumbers(line: String): String {

    var result = ""
    val noStringLine = replaceStringWithNumber(line)
    val numberList = noStringLine.filter { it.isDigit() }.map { it.toString().toInt() }
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

  private fun replaceStringWithNumber(line: String): String {
    var result = line
    numbersMap.forEach { (key, value) ->
      if (result.contains(key))
        result = result.replace(key, value)
    }
    if (result == "") return line
    return result
  }
}