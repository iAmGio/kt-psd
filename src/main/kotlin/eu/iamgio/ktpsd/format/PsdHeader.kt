package eu.iamgio.ktpsd.format

import eu.iamgio.ktpsd.parser.PsdParser

/**
 * Header of document. Contains several information about it
 * @author Giorgio Garofalo
 */
data class PsdHeader(
        val signature: String,
        val version: Byte,
        private val reserved: Byte,
        val channels: Short,
        val height: Int,
        val width: Int,
        val depth: Byte,
        val colorMode: Byte // TODO make enum
) : PsdSection {

    /**
     * Verifies the properties of the header
     * @throws IllegalStateException if at least one of the fixed properties has wrong value
     */
    @Throws(IllegalStateException::class)
    fun verify() {
        if(signature.compareTo("8BPS") != 0) {
            throw IllegalStateException("Wrong file signature ($signature)")
        }
        if(version.compareTo(1) != 0) {
            throw IllegalStateException("Version ($version) is not 1.")
        }
        if(reserved.compareTo(0) != 0) {
            throw IllegalStateException("Reserved value ($reserved) is not 0.")
        }
    }

    companion object : PsdReader() {

        override fun parse(parser: PsdParser): PsdHeader {
            super.parse(parser)
            return PsdHeader(
                    signature = readNextString(4),
                    version = readNextByte(2),
                    reserved = readNextByte(6),
                    channels = readNextShort(2),
                    height = readNextInt(4),
                    width = readNextInt(4),
                    depth = readNextByte(2),
                    colorMode = readNextByte(2)
            ).let {
                it.verify()
                it
            }
        }
    }
}