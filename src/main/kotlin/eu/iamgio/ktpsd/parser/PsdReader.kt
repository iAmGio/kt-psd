package eu.iamgio.ktpsd.parser

/**
 * Reader which reads the document byte by byte
 * @author Giorgio Garofalo
 */
class PsdReader(private val bytes: ByteArray) {

    var byteIndex = 0

    private fun readNext(length: Int): ByteArray {
        val currentBytes = bytes.slice(IntRange(byteIndex, byteIndex + length - 1)).toByteArray()
        byteIndex += length
        return currentBytes
    }

    fun readNextString(length: Int) = String(readNext(length))

    private fun readNextNumber(length: Int): Number {
        val bytes = readNext(length)
        var value = 0
        bytes.filter {it > 0}.forEach {
            value = value * 10 + it
        }
        return value
    }

    fun readNextInt(length: Int) = readNextNumber(length).toInt()
    fun readNextByte(length: Int) = readNextNumber(length).toByte()
    fun readNextShort(length: Int) = readNextNumber(length).toShort()
}

/**
 * Interface implemented by all section types
 */
interface PsdSection