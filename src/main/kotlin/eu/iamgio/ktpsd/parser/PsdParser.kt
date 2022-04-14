package eu.iamgio.ktpsd.parser

import eu.iamgio.ktpsd.format.PsdColorModeDataParser
import eu.iamgio.ktpsd.format.PsdDocument
import eu.iamgio.ktpsd.format.PsdHeaderParser
import java.io.File
import java.io.InputStream

/**
 * This class parses a .PSD file into accessible information
 *
 * @author Giorgio Garofalo
 */
class PsdParser(private val bytes: ByteArray) {

    constructor(inputStream: InputStream) : this(inputStream.readBytes())
    constructor(file: File) : this(file.readBytes())
    constructor(content: String) : this(content.toByteArray())

    /**
     * Parses the content
     * @return Photoshop document
     */
    fun parse(): PsdDocument {
        val reader = PsdReader(bytes)
        val header = PsdHeaderParser.parse(reader)
        val colorModeData = PsdColorModeDataParser.parse(reader)
        return PsdDocument(header, colorModeData)
    }
}