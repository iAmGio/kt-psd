package eu.iamgio.ktpsd.parser

/**
 * Interface implemented by section types.
 */
interface PsdSection

/**
 * Implemented by single-instance objects, parses a [PsdSection].
 *
 * @param T parsed type
 * @author Giorgio Garofalo
 */
interface PsdSectionParser<T : PsdSection> {

    fun parse(reader: PsdReader): T
}