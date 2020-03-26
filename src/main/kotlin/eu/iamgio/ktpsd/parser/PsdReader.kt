package eu.iamgio.ktpsd.parser

/**
 * Reader which reads the document byte by byte
 * @author Giorgio Garofalo
 */
class PsdReader(private val bytes: ByteArray) {

    var byteIndex = 0

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