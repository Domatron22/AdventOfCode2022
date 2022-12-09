package common.utils

import common.objects.Elf
import java.io.File

class ElfUtils {
    private val ELF_NAMES: String =
        "src/main/resources/dayone/elf-names.txt"

    /**
     * This function will grab a random elf name to assign to the current [Elf]
     */
    fun nameElf(): String {
        return File(ELF_NAMES).readLines()[(0..913).random()]
    }


}