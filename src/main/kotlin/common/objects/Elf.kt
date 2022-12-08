package common.objects

class Elf private constructor(
    val name: String?,
    val items: MutableList<Int>
) {
    data class Builder(
        var name: String? = null,
        var items: MutableList<Int> = mutableListOf()
    ){
        fun name(name: String?) = apply { this.name = name }
        fun items(items: MutableList<Int>) = apply { this.items }
        fun initialItem(item: Int)= apply { this.items.add(item) }
        fun build() = Elf(name, items)
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