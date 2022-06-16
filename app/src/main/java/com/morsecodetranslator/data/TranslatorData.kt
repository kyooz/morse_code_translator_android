package com.morsecodetranslator.data

class TranslatorData(
    val char: Char? = 'A',
    val code: String? = ""
) {

    fun getData(): List<TranslatorData> {

        return listOf(
            TranslatorData('A', ".-"),
            TranslatorData('B', "-..."),
            TranslatorData('C', "-.-."),
            TranslatorData('D', "-.."),
            TranslatorData('E', "."),
            TranslatorData('F', "..-."),
            TranslatorData('G', "--."),
            TranslatorData('H', "...."),
            TranslatorData('I', ".."),
            TranslatorData('J', ".---"),
            TranslatorData('K', "-.-"),
            TranslatorData('L', ".-.."),
            TranslatorData('M', "--"),
            TranslatorData('N', "-."),
            TranslatorData('O', "---"),
            TranslatorData('P', ".--."),
            TranslatorData('Q', "--.-"),
            TranslatorData('R', ".-."),
            TranslatorData('S', "..."),
            TranslatorData('T', "-"),
            TranslatorData('U', "..-"),
            TranslatorData('V', "...-"),
            TranslatorData('W', ".--"),
            TranslatorData('X', "-..-"),
            TranslatorData('Y', "-.--"),
            TranslatorData('Z', "--.."),
            TranslatorData('1', ".----"),
            TranslatorData('2', "..---"),
            TranslatorData('3', "...--"),
            TranslatorData('4', "....-"),
            TranslatorData('5', "....."),
            TranslatorData('6', "-...."),
            TranslatorData('7', "--..."),
            TranslatorData('8', "---.."),
            TranslatorData('9', "----."),
            TranslatorData('0', "-----"),
            TranslatorData(' ', "/"),
        )

    }

    fun checkMorseDataByChar(character: Char): String {
        return try {
            val result = getData().find {
                character.equals(it.char!!.toUpperCase())
            }
            result?.code.toString()
        } catch (error: Exception) {
            "#"
        }
    }

    fun checkMorseDataByCode(code: String): String {
        return try {
            val result = getData().find {
                code.equals(it.code)
            }
            result?.char.toString().toLowerCase()
        } catch (error: Exception) {
            "?"
        }
    }

}