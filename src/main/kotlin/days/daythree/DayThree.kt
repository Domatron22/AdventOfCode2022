package days.daythree

import common.objects.Elf
import common.utils.ElfUtils
import java.io.File

/**
 * This class holds all the functions needed to complete the third day of problems for the Advent Of Code 2022
 */
class DayThree {
    // Constants
    private val RUCK_ITEMS : String = "src/main/resources/daythree/rucksacks.txt"
    private val RUCKSACK_VALUES : String = "0abcdefghijklmnopqrstuvwxyzABCDEFGHIJFLMNOPQRSTUVWXYZ"

    //Part 1
    var elfList : MutableList<Elf> = mutableListOf()
    var inventory : MutableList<Int> = mutableListOf()

    //Part 2
    var threeElves : MutableList<Elf> = mutableListOf()
    var groupInventory : MutableList<Int> = mutableListOf()

    val elfUtils : ElfUtils = ElfUtils()

    /**
     * This function is the main entrypoint for day two, it leverages the other methods in this class to determine the final priorities
     */
    fun main(){
        File(RUCK_ITEMS).forEachLine {
            val halfLine : Int = it.length / 2
            val tempSack : MutableList<String> = mutableListOf(it.substring(0, halfLine), it.substring(halfLine))

            var currentElf = Elf.Builder()
                .name(elfUtils.nameElf())
                .fullSac(it)
                .splitSac(tempSack)
                .build()

            elfList.add(currentElf)
        }
        checkElves(elfList)
        println("\nThe total priority of matching items is ${inventory.sum()}")

        groupElves()
        println("The total priority for the groups are ${groupInventory.sum()}")
    }

    /**
     * This function checks every [Elf] in the list for matching values in their two piece rucksack
     *
     * @param elfList This is a list of pre-assembled [Elf]s
     */
    fun checkElves(elfList: MutableList<Elf>){
        elfList.forEach {
            inventoryItems(it)
        }
    }

    /**
     * This function will take a look at a single [Elf] and find the common item between the split rucksack pouches
     *
     * @param the [Elf] whose inventory we are rifling through
     */
    fun inventoryItems(elf : Elf){
        val firstSac : String = elf.rucksacks!![0]
        val secondSac : String = elf.rucksacks[1]

        for (char in firstSac) {
            if (secondSac.contains(char)) {
                inventory.add(RUCKSACK_VALUES.indexOf(char))
                break
            }
        }
    }

    /**
     * This function will put [Elf]s into groups of three and calculate the priority of their rucksacks
     */
    fun groupElves() {
        elfList.forEach {
            threeElves.add(it)
            if(threeElves.size == 3){
                assembleRucksacksAndCheck()
                threeElves = mutableListOf()
            }
        }
    }

    /**
     * This function will compare the [threeElves] inventories and find the common item
     */
    fun assembleRucksacksAndCheck(){
        val sacOne : String = threeElves[0].fullSac!!
        val sacTwo : String = threeElves[1].fullSac!!
        val sacThree : String = threeElves[2].fullSac!!

        for (char in sacOne) {
            if (sacTwo.contains(char) && sacThree.contains(char)) {
                groupInventory.add(RUCKSACK_VALUES.indexOf(char))
                break
            }
        }
    }
}