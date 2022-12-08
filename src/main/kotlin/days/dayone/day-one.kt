package days.dayone

import common.objects.Elf
import java.io.File

/**
 * This class holds all the functions needed to complete the first day of problems for the Advent Of Code 2022
 */
class `day-one`() {
    private val ELF_NAMES: String =
        "src/main/resources/dayone/elf-names.txt"
    private val CAL_ONE: String =
        "src/main/resources/dayone/calories1.txt"

    /**
     * This function is the main function and will find the elf who has the most food, and the top 3 elves with the most food
     */
    fun main(){
        val bigBoi : Elf = findTopElf()
        println(bigBoi.name + " has the most SNACCS at :" + bigBoi.sumItems())

        val top3Bois : MutableList<Elf> = findTopThree()
        println("Top Three BOIS are:")
        top3Bois.forEach {
            println(it.name + ": " + it.sumItems())
        }
        println("For a total of: " + sumAll(top3Bois))
    }

    /**
     * This function will find the elf with the most calories in the given list
     */
    fun findTopElf(): Elf {
        val elves: MutableList<Elf> = createList()
        return compareElf(elves)
    }

    /**
     * This function will read a file and create a list of [Elf]
     */
    fun createList(): MutableList<Elf> {
        var elves: MutableList<Elf> = mutableListOf()
        var currentElf: Elf? = null
        File(CAL_ONE).forEachLine {
            if (it.isBlank()) {
                elves.add(currentElf!!)
                currentElf = null
            } else {
                if (currentElf == null) {
                    currentElf = Elf.Builder()
                        .name(nameElf())
                        .initialItem(it.toInt())
                        .build()
                } else {
                    currentElf!!.addItem(it.toInt())
                }
            }
        }
        return elves
    }

    /**
     * This function will take a given list of [Elf] and find the elf with the most calories
     *
     * @param elves
     *      The list of elves
     */
    fun compareElf(elves: MutableList<Elf>): Elf {
        var LORGEelf: Elf? = null
        var location: Int? = null
        elves.forEach {
            if (LORGEelf == null) {
                LORGEelf = it
            } else {
                if (it.sumItems() > LORGEelf!!.sumItems()) {
                    LORGEelf = it
                }
            }
        }
        location = elves.indexOf(LORGEelf)
        elves.removeAt(location)
        return LORGEelf!!
    }

    /**
     * This function will find the top three [Elf]s with the most calories
     */
    fun findTopThree(): MutableList<Elf> {
        var elves: MutableList<Elf> = createList()

        var gold: Elf = compareElf(elves)
        var silver: Elf = compareElf(elves)
        var bronze: Elf = compareElf(elves)

        return mutableListOf(gold, silver, bronze)
    }

    /**
     * This function will sum a list of [Elf]s calories
     *
     * @param elves
     *      The list of elves
     */
    fun sumAll(elves: MutableList<Elf>): Int {
        var total: Int = 0
        elves.forEach {
            total += it.sumItems()
        }

        return total
    }

    /**
     * This function will grab a random elf name to assign to the current [Elf]
     */
    fun nameElf(): String {
        return File(ELF_NAMES).readLines()[(0..913).random()]
    }
}
