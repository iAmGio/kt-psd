package eu.iamgio.ktpsdtest

import eu.iamgio.ktpsd.format.PsdDocument
import eu.iamgio.ktpsd.parser.PsdParser

/**
 * @author Giorgio Garofalo
 */

fun main() {
    val testInputStream = object {}.javaClass.getResourceAsStream("/test.psd")!!
    val document: PsdDocument = PsdParser(testInputStream).parse()
    println(document)
}