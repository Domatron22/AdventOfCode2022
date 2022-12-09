package days.daytwo

import java.io.File

/**
 * This class holds all the functions needed to complete the second day of problems for the Advent Of Code 2022
 */
class DayTwo {
    val COMP_RPS = "src/main/resources/daytwo/rps.txt"

    /**
     * This function is the main entrypoint for day two, it leverages the other methods in this class to determine the final score
     */
    fun main(){
        var totalScore : Int = 0
        var totalRiggedScore : Int = 0
        File(COMP_RPS).forEachLine {
            val scores : Array<String> = it.split("\\s".toRegex()).toTypedArray()
            val rivalScore : String = scores[0]
            val playerScore : String = scores[1]
            totalScore += determineOutcome(rivalScore, playerScore)
            totalRiggedScore += determineRiggedOutcome(rivalScore, playerScore)
        }
        println("\nCongratulations! You received a score of : $totalScore")
        println("... Lol JK it was rigged, you received a total score of $totalRiggedScore")
    }

    /**
     * The scorecard determines how many points you shoudl be assigned based off of if you picked rock, paper, or scissors
     */
    fun scorecard(playerScore : String, isRigged : Boolean) : Int{
        if(!isRigged) {
            when (playerScore) {
                "X" -> return 1
                "Y" -> return 2
                "Z" -> return 3
                else -> return 0
            }
        } else {
            when(playerScore) {
                "X" -> return 0
                "Y" -> return 3
                "Z" -> return 6
                else -> return 0
            }
        }
    }

    /**
     * This method will take a look at the incoming scores and figure out the outcome
     *
     * A = Rock, B = Paper, C= Scissors, X = Rock, Y = Paper, Z = Scissors
     */
    fun determineOutcome(rivalScore : String, playerScore: String) : Int{
        var score : Int = 0
        score += scorecard(playerScore, false)

        when(rivalScore){
            "A" -> {
                when (playerScore) {
                    "X" -> score += 3
                    "Y" -> score += 6
                }
            }
            "B" -> {
                when (playerScore) {
                    "Y" -> score += 3
                    "Z" -> score += 6
                }
            }
            "C" -> {
                when(playerScore){
                    "X" -> score += 6
                    "Z" -> score += 3
                }
            }
            else -> return 0
        }
        return score
    }

    /**
     * This method will take a look at the incoming scores and figure out the rigged outcome
     *
     * X loose, Y draw, Z win
     */
    fun determineRiggedOutcome(rivalScore: String, playerScore: String) : Int{
        var score : Int = 0
        score += scorecard(playerScore, true)

        when(rivalScore){
            "A" -> {
                when (playerScore) {
                    "X" -> score += 3
                    "Y" -> score += 1
                    "Z" -> score += 2
                }
            }
            "B" -> {
                when (playerScore) {
                    "X" -> score += 1
                    "Y" -> score += 2
                    "Z" -> score += 3
                }
            }
            "C" -> {
                when (playerScore) {
                    "X" -> score += 2
                    "Y" -> score += 3
                    "Z" -> score += 1
                }
            }
        }
        return score
    }
}