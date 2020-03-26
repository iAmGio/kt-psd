package eu.iamgio.ktpsd.parser

/**
 * @author Giorgio Garofalo
 */
interface PsdReadable {

    fun parse(reader: PsdReader): PsdSection
}