package com.ppb.feeds.stt.core.output

/** OutputSource base trait that should be extended by or mixed to create
  * a specific source to output messages.
  */
trait OutputSource[In, Out] {

  /** Writes a message in an output-source with a given key.
    *
    *  @param key used to uniquely identify the input message being writing
    *  @param message the input that is being outsourced.
    *  @return the result writing to the source
    */
  def write(key: String, message: In): Out
}
