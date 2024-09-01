package Day2

import java.io.File


fun main (){
    println("OUTPUT "+Day21().cubeConundrum("src/Day2/input.txt"))

}
class Day21 {




    fun cubeConundrum(filename: String): Int {

        var gameId = ""
        var entries: List<String> = emptyList()
        var blues = 0; var green = 0; var red = 0
        var sumIndexesOfPossibleGames = 0;


        File(filename).forEachLine {

            gameId = it.substring(0).substringBefore(":").substringAfter(" ")

            var newLine = it
            // remove everything before the first number, so Game X:
            newLine = newLine.substring(0).substring(it.indexOf(":")+1)
            entries = newLine.split(";").toList()
            var ok = true
            for(value in entries ){
                blues = getSumForColour(value,"blue")
                green = getSumForColour(value,"green")
                red = getSumForColour(value, "red")
                if(red > 12 || green >13 || blues > 14) {
                    ok= false
                }
            }
            if(ok) sumIndexesOfPossibleGames+=gameId.toInt()
        }
        return  sumIndexesOfPossibleGames
    }

private fun getNumberColourPair(substring:String, colour:String): HashMap<Int,String> {
    var numberColours = hashMapOf<Int, String>()
    var newLine = substring.trim()
    var entries = newLine.split(",")
    for( entry in entries) {

        var pair = entry.trim().split(" ")
        var number = pair[0]
        var entryColour = pair[1]
        if(entryColour == colour) numberColours[number.toInt()]=entryColour
    }
    return numberColours

}
private fun getSumForColour(line:String, colour:String, ): Int {
    var subset = getNumberColourPair(line, colour)
    var sum =0
    for ((number, colour) in subset){
        sum+=number
    }
    return sum
}

}