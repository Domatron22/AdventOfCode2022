package common.objects

class Elf private constructor(
    val name: String?,
    val items: MutableList<Int>,
    val rucksacks: MutableList<String>?,
    var fullSac: String?
) {
    data class Builder(
        var name: String? = null,
        var items: MutableList<Int> = mutableListOf(),
        var rucksacks: MutableList<String> = mutableListOf(),
        var fullSac: String? = null
    ){
        fun name(name: String?) = apply { this.name = name }
        fun items(items: MutableList<Int>) = apply { this.items = items }
        fun initialItem(item: Int)= apply { this.items.add(item) }
        fun fullSac(rucksack : String) = apply { this.fullSac = rucksack }
        fun splitSac(rucksacks: MutableList<String>) = apply { this.rucksacks = rucksacks }
        fun build() = Elf(name, items, rucksacks, fullSac)
    }

    fun addItem(item: Int){
        this.items.add(item)
    }

    fun sumItems() : Int{
        var sum : Int = 0
        for (item in this.items){
            sum += item
        }
        return sum
    }
}