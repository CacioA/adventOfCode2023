package org.example.Day3

import java.io.File

fun main () {
    Day3Part1().gearRatios("src/main/kotlin/Day3/input.txt")
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
            numberRegex.findAll(line)
                .forEach { result ->
                    val value = result.value.toInt()
                    val indexNumber = result.range.first
                    checkSymbolExistsInLine(line, indexNumber, value)

                    if (i == 0) {
                        checkSymbolExistsBelowOrAboveLine(file[i+1], indexNumber, value)
                    }
                    if (i in (1 .. file.size ) && i+1 <= file.size - 1 ) {
                        checkSymbolExistsBelowOrAboveLine(file[i+1], indexNumber, value)
                        checkSymbolExistsBelowOrAboveLine(file[i-1], indexNumber, value)
                    }
                    else if (i == file.size - 1) {
                        checkSymbolExistsBelowOrAboveLine(file[i-1], indexNumber, value)
                    }
                }
        }
        println(sum)
    }

    private fun checkSymbolExistsBelowOrAboveLine(line:String, indexNumber: Int, value: Int) {
        sanitizeIndexes(indexNumber, value, line.length)
        // substring end index is not inclusive, so +1

        line.substring(startingNon0Index - 1, endingNonMaxIndex + 1)
            .filter { !notSymbols.contains(it) }
            .also{ if (it.isNotEmpty()) sum += value}
    }

    private fun sanitizeIndexes(indexNumber: Int, value: Int, lineLength: Int ) {
        startingNon0Index = indexNumber
        endingNonMaxIndex = indexNumber + value.toString().length

        if (endingNonMaxIndex >= lineLength) {
            endingNonMaxIndex = lineLength-1
        }
        if (startingNon0Index == 0) {
            startingNon0Index++;
        }
    }

    private fun checkSymbolExistsInLine(line: String, indexNumber: Int, value: Int) {
        sanitizeIndexes(indexNumber, value, line.length)
        if (!notSymbols.contains(line.get(startingNon0Index - 1))
            || !notSymbols.contains(line.get(endingNonMaxIndex)))
            sum += value
    }
}