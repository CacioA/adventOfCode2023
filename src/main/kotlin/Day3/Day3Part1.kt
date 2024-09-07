package org.example.Day3

import java.io.File

fun main () {
    Day3Part1().gearRatios("src/main/kotlin/Day3/testInput.txt")
}
class Day3Part1 {
    private var sum = 0
    private var notSymbols = listOf('0','1','2','3','4','5','6','7','8','9','.')
    private var startingNon0Index = 0
    private var endingNonMaxIndex = 0

    fun gearRatios(filename:String) {
        val file = File(filename).readLines()
        val numberRegex= "\\d+".toRegex()

        file.forEachIndexed{ i, line ->
            numberRegex.findAll(line).forEach { result ->
                    val value = result.value.toInt()
                    val indexNumber = result.range.first

                    if (i == 0) {
                        checkSymbolExistsInLine(line, indexNumber, value)
                        checkSymbolExistsBelowOrAboveLine(file[i+1], indexNumber, value)
                    }
                    if(i in 1 until file.size){
                        // e.g:
                        // ...*555..
                        // OR
                        // ...555*..
                        checkSymbolExistsInLine(line, indexNumber, value)
                        checkSymbolExistsBelowOrAboveLine(file[i+1], indexNumber, value)
                        checkSymbolExistsBelowOrAboveLine(file[i-1], indexNumber, value)
                    }
                    else if (i == file.size - 1) {
                        checkSymbolExistsInLine(line, indexNumber, value)
                        checkSymbolExistsBelowOrAboveLine(file[i-1], indexNumber, value)
                    }
            }
        }
        println(sum)
    }

    private fun checkSymbolExistsBelowOrAboveLine(line:String, indexNumber: Int, value: Int) {
        sanitizeIndexes(indexNumber, value, line.length)

        line.substring(startingNon0Index - 1, endingNonMaxIndex)
            .filter { !notSymbols.contains(it) }
            .toSet()
            .also { if (it.isEmpty()) sum += value}
    }

    private fun sanitizeIndexes(indexNumber: Int, value: Int, lineLength: Int ) {
        startingNon0Index = indexNumber
        endingNonMaxIndex = indexNumber + value.toString().length + 1

        if (endingNonMaxIndex >= lineLength) {
            endingNonMaxIndex--
        }
        if (startingNon0Index == 0) {
            startingNon0Index++
        }
    }

    private fun checkSymbolExistsInLine(line: String, indexNumber: Int, value: Int) {
        sanitizeIndexes(indexNumber, value, line.length)

        if (!notSymbols.contains(line.get(startingNon0Index - 1))
            || !notSymbols.contains(line.get(endingNonMaxIndex))
        ) {
            sum += value
        }
    }
}





