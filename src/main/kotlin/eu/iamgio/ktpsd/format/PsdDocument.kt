package eu.iamgio.ktpsd.format

/**
 * Class representing a common .PSD file and its content.
 *
 * @author Giorgio Garofalo
 */
data class PsdDocument(
        val header: PsdHeader,
        val colorModeData: PsdColorModeData,
        val imageResources: PsdImageResources
)