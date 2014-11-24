package com.itizzimo

import java.io.{InputStream, File}
import java.util.Arrays
import scala.collection.mutable.{ArrayBuffer, Map => MMap}

import scaposer.{Po, Parser}
/**
 * Created by B001 on 20.11.2014.
 */
object PoLoader {
  private[this] val BUFFER_SIZE = 1024

  def get(language: String): Po = {

    synchronized {
      val urlEnum = Thread.currentThread.getContextClassLoader.getResources("i18n/" + "en" + ".po")
      val buffer  = ArrayBuffer.empty[Po]
      var i = 1
      var poaa: Option[Po] = null
      while (urlEnum.hasMoreElements()) {
        val url    = urlEnum.nextElement()
        val is     = url.openStream()
        val bytes  = this.bytesFromInputStream(is)
        val string = new String(bytes, "UTF-8")
//        println(i + " " + string)
        i = i+1
        Parser.parsePo(string).foreach(buffer.append(_))
        poaa = Parser.parsePo(string)
      }

      val ret = buffer.foldLeft(new Po(Map.empty)) { (acc, e) => acc ++ e }
//      println(poaa.get.t("HelloWorld!") + " verusch")
      ret
    }
  }

  def bytesFromInputStream(is: InputStream): Array[Byte] = {
    if (is == null) return null

    // Shorter version, but not optimized:
    //
    // var ret    = Array.empty[Byte]
    // var buffer = new Array[Byte](BUFFER_SIZE)
    // while (is.available() > 0) {
    //   val bytesRead = is.read(buffer)
    //   ret = ret ++ buffer.take(bytesRead)
    // }

    // "available" does not always return the exact number of bytes left
    if (is.available() > 0) {
      var buffer = new Array[Byte](BUFFER_SIZE)

      // Use null to avoid creating empty Array[Byte] unnecessarily
      var ret: Array[Byte] = null

      while (is.available() > 0) {
        val bytesRead = is.read(buffer)

        if (ret == null) {
          ret = Arrays.copyOf(buffer, bytesRead)
        } else {
          // http://stackoverflow.com/questions/80476/how-to-concatenate-two-arrays-in-java
          val oldLength = ret.length
          ret = Arrays.copyOf(ret, oldLength + bytesRead)
          System.arraycopy(buffer, 0, ret, oldLength, bytesRead)
        }
      }

      is.close()
      ret
    } else {
      is.close()
      Array.empty[Byte]
    }
  }

}
