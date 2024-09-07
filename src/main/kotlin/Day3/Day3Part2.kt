package org.example.Day3

import java.io.File
fun main() {
    Day3Part2().gearRatios("src/main/kotlin/Day3/input.txt")
}
class Day3Part2 {
    private var countPerSymbol = 0
    private var numberList = mutableListOf<Int>()
    private var sum = 0
    private val starRegex = "\\*".toRegex()
    private val numberRegex = "\\d+".toRegex()

    fun gearRatios(filename: String) {
        val file = File(filename).readLines()

        file.forEachIndexed { i, line ->
            if(line.contains("*"))
                starRegex.findAll(line)
                .forEach { result ->
                    countPerSymbol = 0
                    numberList.clear()
                    val indexSymbol = result.range.first

                    checkNumberExistsInLine(line,  indexSymbol)
                    if (i == 0) checkNumberExistsInLine(file[1], indexSymbol)

                    if (i in (1..file.size) && i + 1 <= file.size - 1) {
                        checkNumberExistsInLine(file[i + 1], indexSymbol)
                        checkNumberExistsInLine(file[i - 1], indexSymbol)
                    } else if (i == file.size - 1) {
                        checkNumberExistsInLine(file[i - 1], indexSymbol)
                    }
                    if(countPerSymbol == 2){
                        var multiplication =1;

                        for(number in numberList) {
                            multiplication *= number
                        }
                        sum += multiplication
                    }
                }
        }
        println(sum)
    }

    private fun checkNumberExistsInLine(line: String, indexNumber: Int) {
        numberRegex.findAll(line).forEach{ result ->
            val value = result.value.toInt()
            val startIndex = result.range.first
            val endIndex = startIndex + value.toString().length -1

            val validIndeces = listOf(indexNumber-1, indexNumber, indexNumber+1)
            val numberAllIndices = (startIndex .. endIndex).toList()
            if(validIndeces.intersect(numberAllIndices).isNotEmpty()) {
                numberList.add(value)
                countPerSymbol++
            }
        }
    }
}
