package com.ppb.feeds.stt.core.processor

/** Processor base trait that should be extended by or mixed to create
  * a specific Data Processor.
  */
trait Processor[In, Out] {

  /** Processes an input based in a set of processing rules and returns the result.
    *
    *  @param in the input to be processed
    *  @return the processed input data
    */
  def process(in: In): Out
}

