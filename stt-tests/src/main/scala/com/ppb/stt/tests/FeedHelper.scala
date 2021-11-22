package com.ppb.stt.tests

import scala.util.hashing.MurmurHash3

trait FeedHelper {

  def readPayloadFromFile(filePath:String) : String = {
    val dir   = getClass.getResource(filePath)
    scala.io.Source.fromFile(dir.getFile).mkString.replace("\n","")
  }

  def calculateKey(id: String): String = {
    MurmurHash3.stringHash(id, MurmurHash3.stringSeed).toString
  }

}
