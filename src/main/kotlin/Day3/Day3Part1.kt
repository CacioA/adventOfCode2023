package org.example.Day3

import com.google.common.collect.ArrayListMultimap
import com.google.common.collect.Multimap
import java.io.File

fun main () {
    Day3Part1().gearRatios("src/main/kotlin/Day3/testInput.txt")
}
class Day3Part1 {
    var sum = 0;
    var symbols = listOf('0','1','2','3','4','5','6','7','8','9','.')
    //    var numbersAdded: MutableList<Int> = mutableListOf()
//    var numbersNotAdded: MutableList<Int> = mutableListOf()
    var startingNon0Index = 0;
    var endingNonMaxIndex = 0;
    fun gearRatios(filename:String) {
        var file = File(filename).readLines()

        var numberRegex= "\\d+".toRegex();
        for (i in file.indices) {
            var line = file[i]
            println(line)
            numberRegex.findAll(line)
                .map { result ->
                    val value = result.value.toInt()
                    val indexNumber = result.range.first

                    if (i == 0) {
                        checkSymbolExistsInLine(line, indexNumber, value)
                        checkSymbolExistsBelowOrAboveLine(file[i+1], indexNumber, value)
//                        println("0")
                    }
                    if (i in (1 .. file.size ) && i+1 <= file.size - 1 ) {
                        // e.g:
                        // ...*555..
                        // OR
                        // ...555*..
                        // this is actually check next to the number
                        checkSymbolExistsInLine(line, indexNumber, value)
                        checkSymbolExistsBelowOrAboveLine(file[i+1], indexNumber, value)
                        checkSymbolExistsBelowOrAboveLine(file[i-1], indexNumber, value)
//                        println("  0 < i < last line")
//                        println(i)
                        // function check previous line
                    }
                    else if (i == file.size - 1) {
                        checkSymbolExistsInLine(line, indexNumber, value)
//                        // check below from 1 before the number and 1 after
                        checkSymbolExistsBelowOrAboveLine(file[i-1], indexNumber, value)
//                        println("last")
                    }
                }
                .toList()
        }
        println(sum)
//        println("Added: "+ numbersAdded)
//        println("Not added: "+ numbersNotAdded)
    }

    private fun checkSymbolExistsBelowOrAboveLine(
        line:String,
        indexNumber: Int,
        value: Int
    ) {


        sanitizeIndexes(indexNumber, value, line.length)

        var symbolsBelowNumber = line.substring(startingNon0Index - 1, endingNonMaxIndex).toSet()
        symbolsBelowNumber = symbolsBelowNumber.filter { it -> !symbols.contains(it) }.toSet();
        if(symbolsBelowNumber.size > 0) {
            sum += value;
//            numbersAdded.add(value)
//            println(" Number "+ value + " ADDED in below above")

        }
        else {
//            println(" Number "+ value + " not added in below above")
//            numbersNotAdded.add(value)
        }
    }

    private fun sanitizeIndexes(indexNumber: Int, value: Int, lineLength: Int ) {
        startingNon0Index = indexNumber;
        endingNonMaxIndex = indexNumber + value.toString().length + 1

        if (endingNonMaxIndex >= lineLength) {
            endingNonMaxIndex--;
        }
        if (startingNon0Index == 0) {
            startingNon0Index++;
        }

    }

    private fun checkSymbolExistsInLine(
        line: String,
        indexNumber: Int,
        value: Int,
    ) {
        var startingNon0Index = indexNumber;
        var endingNonMaxIndex = indexNumber + value.toString().length

        if(startingNon0Index == 0){
            startingNon0Index++
        }

        if(endingNonMaxIndex == line.length){
            endingNonMaxIndex --;
        }
        if (!symbols.contains(line.get(startingNon0Index - 1))
            || !symbols.contains(line.get(endingNonMaxIndex ))
        ) {
            sum += value
//                println(" Number "+ value + "  ADDED in same line")

//                numbersAdded.add(value)
//            } else {
//                println(" Number "+ value + " not added in same line")
//
////                numbersNotAdded.add(value)
//            }

        }

//            file[i].forEachIndexed{index, c ->
//                if(!symbols.contains(c)) {
//                    symbolsFound.put(i, index)
//                }
//            }

        // check if for current line:
        // if i is not 0
        //   check previous line at index
        //else
        //   check current line
        // if i is not last -1
        //   check next line

    }
}





