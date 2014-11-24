package com.itizzimo

import I18n.I18n

/**
 * Created by B001 on 20.11.2014.
 */
object Boot extends App with I18n {
  val stringa = "Hallo du" + t("HelloWorld!")

  var stringb = t("Mein Gott!")

  println(stringa)
  println(stringb)
}
