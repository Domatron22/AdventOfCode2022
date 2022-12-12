import days.dayfour.DayFour
import days.dayone.DayOne
import days.daythree.DayThree
import days.daytwo.DayTwo

fun main() {
    val dayOne : DayOne = DayOne()
    dayOne.main()

    val dayTwo : DayTwo = DayTwo()
    dayTwo.main()

    val dayThree : DayThree = DayThree()
    dayThree.main()

    val dayFour : DayFour = DayFour()
    dayFour.main()
}