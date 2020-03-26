package eu.iamgio.ktpsd.format

/**
 * Class representing a common .PSD file content
 * @author Giorgio Garofalo
 */
public data class PsdDocument(
        val header: PsdHeader,
        val colorModeData: PsdColorModeData
)