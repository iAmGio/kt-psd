package eu.iamgio.ktpsd.format

import eu.iamgio.ktpsd.parser.PsdReader
import eu.iamgio.ktpsd.parser.PsdSection
import eu.iamgio.ktpsd.parser.PsdSectionParser

/**
 * @author Giorgio Garofalo
 */
data class PsdImageResources(
        val signature: String,
        val id: Int
        // TODO
) : PsdSection

object PsdImageResourcesParser : PsdSectionParser<PsdImageResources> {

    override fun parse(reader: PsdReader) = with(reader) {
        val length = readNextInt(4)
        PsdImageResources(
                signature = readNextString(4),
                id = readNextHex(2).toInt() // TODO
        )
    }
}