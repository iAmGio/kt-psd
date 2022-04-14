package eu.iamgio.ktpsd.parser

/**
 * Reader which reads the document byte by byte
 * @author Giorgio Garofalo
 */
class PsdReader(private val bytes: ByteArray) {

    private var byteIndex = 0

    private fun readNext(length: Int): ByteArray {
        if(length == 0) return ByteArray(0)
        val currentBytes = bytes.slice(IntRange(byteIndex, byteIndex + length - 1)).toByteArray()
        byteIndex += length
        return currentBytes
    }

    fun readNextString(length: Int) = String(readNext(length))

    private fun readNextDecimal(length: Int): Number {
        val bytes = readNext(length)
        var value = 0
        bytes.filter { it > 0 }.forEach {
            value = value * 10 + it
        }
        return value
    }

    fun readNextInt(length: Int) = readNextDecimal(length).toInt()
    fun readNextByte(length: Int) = readNextDecimal(length).toByte()
    fun readNextShort(length: Int) = readNextDecimal(length).toShort()

    fun readNextHex(length: Int): Number {
        // TODO: hex byte array to number
        return 0
    }
}