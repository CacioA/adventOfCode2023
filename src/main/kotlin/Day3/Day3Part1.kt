package Day3

import java.io.File

fun main(){
    println(Day3Part1().gearRatios("Day3/input.txt"));
}
class Day3Part1 {

    // Symbols[int,int]
    //        [row, index]
    // numbers[row, [start,end]]


    // gather all symbols


    var symbols: List<String> = listOf("-", "&", "/","*", "@", "#", "%", "+", "=", "$");
    val charSymbols: List<Char> = symbols.flatMap { it.toList() }
    var symbolsIndex =  HashMap<Int, Int>()
    var row=0;
    fun gearRatios(filename: String): HashMap<Int, Int> {
        File(filename).forEachLine() {
                line -> line.forEachIndexed{ index, c ->
            if(charSymbols.contains(c)) symbolsIndex[row] = index
        }
            row++;

        }
        return symbolsIndex;

    }

}