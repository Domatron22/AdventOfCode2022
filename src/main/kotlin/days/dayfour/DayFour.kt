package days.dayfour

import java.io.File

/**
 * This class holds all the functions needed to complete the fourth day of problems for the Advent Of Code 2022
 */
class DayFour {
    // Constants
    val PAIRS = "src/main/resources/dayfour/pairs.txt"

    //Part 1
    var loadedPairs : MutableList<MutableList<IntRange>> = mutableListOf()

    /**
     * This function is the main entrypoint for day four. It leverages the other methods in this class to determine
     * amount of unique and overlapping [IntRange]
     */
    fun main(){
        File(PAIRS).forEachLine { pair ->
            val tempLines : Array<String> = pair.split(",").toTypedArray()
            val innerList : MutableList<IntRange> = mutableListOf()

            tempLines.forEach { range ->
                val ranges : Array<String> = range.split("-").toTypedArray()
                innerList.add(IntRange(ranges[0].toInt(), ranges[1].toInt()))
            }
            loadedPairs.add(innerList)
        }

        firstPart()
        secondPart()
    }

    /**
     * This function compares each [IntRange] to the values within its own [MutableList] to see what ranges are unique
     * in their own list
     */
    fun firstPart(){
        // Count the ranges that are contained in one or the other
        println("\nAll Pairs loaded! Total unique pairs: ${
            loadedPairs.count{ (first, last) ->
                // If both the start and end of the range are contained within either the first or the last range
                ( first.contains(last.first) && first.contains(last.last) ) ||
                        (last.contains(first.first) && last.contains(first.last))
            }
        }")
    }

    /**
     * This function compares each [IntRange] to the values within its own [MutableList] to see if the values overlap
     */
    fun secondPart(){
        println("Total overlapping pairs: ${
            loadedPairs.count{ (first, last) ->
                last.contains(first.first) || first.contains(last.first)
            }
        }")
    }

}