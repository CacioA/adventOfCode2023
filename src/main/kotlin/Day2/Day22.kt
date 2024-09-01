package Day2

import java.io.File


fun main (){
    println("OUTPUT "+Day22().cubeConundrum("src/Day2/input.txt"))

}
class Day22 {


    var MIN_BLUE = Int.MIN_VALUE
    var MIN_RED = Int.MIN_VALUE
    var MIN_GREEN = Int.MIN_VALUE

    fun cubeConundrum(filename: String): HashMap<String,Int> {

        var gameId = ""

        var entries: List<String> = emptyList()
        var blueEntries = hashMapOf<String,Int>()
        var redEntries = hashMapOf<String,Int>()
        var greenEntries = hashMapOf<String,Int>()

        var sumIndexesOfPossibleGames = 0;
        var powerOfMin = 0







        File(filename).forEachLine {



            gameId = it.substring(0).substringBefore(":").substringAfter(" ")

            var newLine = it
            newLine = newLine.substring(0).substring(it.indexOf(":")+1)
            entries = newLine.split(";").toList()
            var ok = true


            var blueCurrentAndMin = hashMapOf("min" to MIN_BLUE, "current" to 0)
            var redCurrentAndMin = hashMapOf("min" to MIN_RED, "current" to 0)
            var greenCurrentAndMin = hashMapOf("min" to MIN_GREEN, "current" to 0)

            for(value in entries ){


                blueEntries = getNumberColourPair2(value,"blue",blueCurrentAndMin, MIN_BLUE)
                MIN_BLUE = blueEntries["min"]!!
                redEntries = getNumberColourPair2(value,"red",redCurrentAndMin, MIN_RED)
                MIN_RED = redEntries["min"]!!
                greenEntries = getNumberColourPair2(value,"green",greenCurrentAndMin, MIN_GREEN)
                MIN_GREEN = greenEntries["min"]!!


                if(redEntries["current"]!! > 12 || greenEntries["current"]!! >13 || blueEntries["current"]!! > 14) {
                    ok= false
                }

            }
            MIN_BLUE = Int.MIN_VALUE
            MIN_RED = Int.MIN_VALUE
            MIN_GREEN = Int.MIN_VALUE
            if(ok) {
                sumIndexesOfPossibleGames+=gameId.toInt()
            }
            powerOfMin+=blueEntries["min"]!! * redEntries["min"]!! * greenEntries["min"]!!

        }

        return hashMapOf("sum of games" to sumIndexesOfPossibleGames, "power of min" to powerOfMin)
    }


    private fun getNumberColourPair(substring:String, colour:String): HashMap<Int,String> {
        val numberColours = hashMapOf<Int, String>()
        val newLine = substring.trim()
        val entries = newLine.split(",")
        for( entry in entries) {

            var pair = entry.trim().split(" ")
            var number = pair[0]
            var entryColour = pair[1]
            if(entryColour == colour) numberColours[number.toInt()]=entryColour
        }
        return numberColours

    }

    //input:
    // 1 blue, 5 green
    // 3 red, 2 blue, 1 green
    // this will be executed for each subset of a game -> should reset max int after
    private fun getNumberColourPair2(substring:String, colour:String, minAndCurrent: HashMap<String,Int>, minColour: Int): HashMap<String,Int> {
        // Blue ->  { min -> 5, current -> 10}


        val newLine = substring.trim()
        val entries = newLine.split(",")
        for (entry in entries) {

            val pair = entry.trim().split(" ")
            val number = pair[0].toInt()
            val entryColour = pair[1]
            if (entryColour == colour) {
                if (number > minColour) { minAndCurrent["min"] = number }
                minAndCurrent["current"] = number

            }
        }
        return minAndCurrent
    }

}