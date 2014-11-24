package I18n

import com.itizzimo.PoLoader

/**
 * Created by B001 on 20.11.2014.
 */
trait I18n {
  private var _language = "en"

  def t(singular: String): String = PoLoader.get(_language).t(singular)
  def tc(ctx: String, singular: String): String = ""
  def tn(singular: String, plural: String, n: Long): String = ""
  def tcn(ctx: String, singular: String, plural: String, n: Long): String = ""
}
