package com.ppb.feeds.stt.core.output.mock

import com.ppb.feeds.stt.core.output.OutputSource
import com.typesafe.scalalogging.LazyLogging

trait MockOutput extends OutputSource[String, String] with LazyLogging {

  /** Writes a message in an output-source with a given key.
    *
    * @param key     used to uniquely identify the input message being writing
    * @param message the input that is being outsourced.
    * @return the result writing to the source
    */
  override def write(key: String, message: String): String = {

    logger.info(s"Word published: ${message}")
    message
  }
}
