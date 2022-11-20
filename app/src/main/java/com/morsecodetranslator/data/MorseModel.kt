package com.morsecodetranslator.data

class MorseModel(
    val key: Char? = 'A',
    val code: String? = ""
)

fun getMorseDatabase(): List<MorseModel> {

    return listOf(
        MorseModel('A', ".-"),
        MorseModel('B', "-..."),
        MorseModel('C', "-.-."),
        MorseModel('D', "-.."),
        MorseModel('E', "."),
        MorseModel('F', "..-."),
        MorseModel('G', "--."),
        MorseModel('H', "...."),
        MorseModel('I', ".."),
        MorseModel('J', ".---"),
        MorseModel('K', "-.-"),
        MorseModel('L', ".-.."),
        MorseModel('M', "--"),
        MorseModel('N', "-."),
        MorseModel('O', "---"),
        MorseModel('P', ".--."),
        MorseModel('Q', "--.-"),
        MorseModel('R', ".-."),
        MorseModel('S', "..."),
        MorseModel('T', "-"),
        MorseModel('U', "..-"),
        MorseModel('V', "...-"),
        MorseModel('W', ".--"),
        MorseModel('X', "-..-"),
        MorseModel('Y', "-.--"),
        MorseModel('Z', "--.."),
        MorseModel('1', ".----"),
        MorseModel('2', "..---"),
        MorseModel('3', "...--"),
        MorseModel('4', "....-"),
        MorseModel('5', "....."),
        MorseModel('6', "-...."),
        MorseModel('7', "--..."),
        MorseModel('8', "---.."),
        MorseModel('9', "----."),
        MorseModel('0', "-----"),
        MorseModel(' ', "/"),
    )

}