package eu.iamgio.ktpsd.parser

import eu.iamgio.ktpsd.format.PsdDocument
import eu.iamgio.ktpsd.format.PsdHeader
import java.io.File
import java.io.InputStream

/**
 * This class parses a .PSD file into accessible information
 * @author Giorgio Garofalo
 */
public class PsdParser(val bytes: ByteArray) {

    constructor(inputStream: InputStream) : this(inputStream.readBytes())
    constructor(file: File) : this(file.readBytes())
    constructor(content: String) : this(content.toByteArray())

    /**
     * Parses the content
     * @return Photoshop document
     */
    public fun parse(): PsdDocument {
        val header = PsdHeader.parse(this)
        return PsdDocument(header)
    }
}