package eu.iamgio.ktpsd.format

import eu.iamgio.ktpsd.parser.PsdParser

/**
 * Reader which reads the document byte by byte
 * @author Giorgio Garofalo
 */
abstract class PsdReader {

    lateinit var bytes: ByteArray
        private set

    var byteIndex = 0

    open fun parse(parser: PsdParser): PsdSection {
        bytes = parser.bytes
        return object : PsdSection {}
    }

    private fun readNext(length: Byte): ByteArray {
        val currentBytes = bytes.slice(IntRange(byteIndex, byteIndex + length - 1)).toByteArray()
        byteIndex += length
        return currentBytes
    }

    fun readNextString(length: Byte) = String(readNext(length))

    private fun readNextNumber(length: Byte): Number {
        val bytes = readNext(length)
        var value = 0
        bytes.filter {it > 0}.forEach {
            value = value * 10 + it
        }
        return value
    }

    fun readNextInt(length: Byte) = readNextNumber(length).toInt()
    fun readNextByte(length: Byte) = readNextNumber(length).toByte()
    fun readNextShort(length: Byte) = readNextNumber(length).toShort()
}

/**
 * Interface implemented by all section types
 */
interface PsdSection