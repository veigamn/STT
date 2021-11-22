package com.ppb.feeds.stt.core.processor.wordCounter

import com.ppb.feeds.stt.core.processor.Processor
import com.typesafe.scalalogging.LazyLogging

class WordCountProcessor extends Processor[String, Seq[String]] with LazyLogging {

  private var counts = Map[String, Int]()

  /** Processes an input based in a set of processing rules and returns the result.
   *
   * @param in the input to be processed
   * @return the processed input data
   */
  override def process(phrase: String): Seq[String] = {

    logger.info(s"counts context = $counts")
    phrase
      .split(" ")
      .map(word => {
        logger.info(s"Processed word = $word")
        countWord(word)
      })
  }

  private def countWord(word: String) = {
    val count = counts.get(word) match {
      case None => 0
      case Some(n) => n + 1
    }
    counts = counts + (word->count)
    s"word = $word, count = $count"
  }
}

object main extends App {
  val wordCountProcessor = new WordCountProcessor

  println(wordCountProcessor.process("sapo"))
  println(wordCountProcessor.process("sapo"))
}
