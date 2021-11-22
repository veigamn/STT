package com.ppb.feeds.stt.core.processor.mock

import com.ppb.feeds.stt.core.processor.Processor

class MockProcessor extends Processor[String, Seq[String]] {

  /** Processes an input based in a set of processing rules and returns the result.
    *
    * @param in the input to be processed
    * @return the processed input data
    */
  override def process(in: String): Seq[String] = List(in)
}
