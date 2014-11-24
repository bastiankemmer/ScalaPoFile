/**
 * Created by B001 on 20.11.2014.
 */
package scaposer

// msgid_plural in po file is for translators. We do not need to store it here
// because it is available in the translation methods of class Po.
class Translation(
                   val ctxo:     Option[String],
                   val singular: String,
                   val strs:     Seq[String]
                   )
