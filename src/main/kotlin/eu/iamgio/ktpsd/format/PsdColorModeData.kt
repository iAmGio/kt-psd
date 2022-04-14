package eu.iamgio.ktpsd.format

import eu.iamgio.ktpsd.parser.PsdReader
import eu.iamgio.ktpsd.parser.PsdSection
import eu.iamgio.ktpsd.parser.PsdSectionParser

/**
 * @author Giorgio Garofalo
 */
data class PsdColorModeData(
        val colorData: String
) : PsdSection

object PsdColorModeDataParser : PsdSectionParser<PsdColorModeData> {

    override fun parse(reader: PsdReader): PsdColorModeData {
        val length = reader.readNextInt(4)
        return PsdColorModeData(reader.readNextString(length))
    }
}