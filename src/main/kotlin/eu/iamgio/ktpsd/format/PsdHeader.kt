package eu.iamgio.ktpsd.format

import eu.iamgio.ktpsd.parser.PsdReadable
import eu.iamgio.ktpsd.parser.PsdReader
import eu.iamgio.ktpsd.parser.PsdSection

/**
 * Header of document. Contains several information about it
 * @author Giorgio Garofalo
 */
data class PsdHeader(
        /**
         * Signature. Must be 8BPS
         */
        val signature: String,
        /**
         * Version constant. Must be 1
         */
        val version: Byte,
        /**
         * Reserved value. Must be 0
         */
        private val reserved: Byte,
        /**
         * The number of channels in the image, including any alpha channels. Supported range is 1 to 56
         */
        val channels: Short,
        /**
         * The height of the image in pixels. Supported range is 1 to 30,000
         */
        val height: Int,
        /**
         * The width of the image in pixels. Supported range is 1 to 30,000
         */
        val width: Int,
        /**
         * The number of bits per channel. Supported values are 1, 8, 16 and 32
         */
        val depth: Byte,
        /**
         * The color mode of the file. Supported values are: Bitmap = 0; Grayscale = 1; Indexed = 2; RGB = 3; CMYK = 4; Multichannel = 7; Duotone = 8; Lab = 9
         */
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

    companion object : PsdReadable {

        override fun parse(reader: PsdReader) = with(reader) {
            PsdHeader(
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