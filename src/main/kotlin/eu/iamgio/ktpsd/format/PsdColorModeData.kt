package eu.iamgio.ktpsd.format

import eu.iamgio.ktpsd.parser.PsdReadable
import eu.iamgio.ktpsd.parser.PsdReader
import eu.iamgio.ktpsd.parser.PsdSection

/**
 * @author Giorgio Garofalo
 */
data class PsdColorModeData(
        val colorData: String
) : PsdSection {

    companion object : PsdReadable {

        override fun parse(reader: PsdReader): PsdColorModeData {
            val length = reader.readNextInt(4)
            return PsdColorModeData(reader.readNextString(length))
        }
    }
}