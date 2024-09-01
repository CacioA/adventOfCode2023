package Day2

import java.io.File


fun main (){
    println("OUTPUT "+Day2().cubeConundrum("src/Day2/input.txt"))
        var x = "Game 4: 12 red, 3 green; 1 green, 2 blue, 2 red; 16 red, 4 green; 14 red, 3 green; 2 blue, 5 red; 11 red, 4 green"

}
class Day2 {




    fun cubeConundrum(filename: String): Int {
        var indexes = HashMap<Int,String>()
        var count = 0
        var gameId = ""

        var entries: List<String> = emptyList()
        var blues = 0;
        var green = 0;
        var red = 0;
        var sumIndexesOfPossibleGames = 0;

        File(filename).forEachLine {

            gameId = it.substring(0).substringBefore(":").substringAfter(" ")

            var newLine = it
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

        return sumIndexesOfPossibleGames
    }

    fun getSumForColour(line: String, character: String): Int{

        var index =0
        var sum=0
        var newLine = line
        if(newLine.contains(",")) {
            var x = newLine.split(",")
        }
        newLine = newLine.filter{!it.isWhitespace()}

       var substring = ""
        while(newLine.contains(character)){
            index  = newLine.indexOf(character)
            if (index > 1 ) {
                substring = newLine.substring(index - 2, index)
            }
            else {
                substring = newLine.substring(index - 1, index)
            }

            sum+= substring.filter{it.isDigit()}.toInt()
            newLine=newLine.replaceFirst(character,"!")
        }
        return sum
    }


    fun getSumForColour2(line:String, character:String): Int {
        var entries = line.split(",")

        return 1;
    }
}