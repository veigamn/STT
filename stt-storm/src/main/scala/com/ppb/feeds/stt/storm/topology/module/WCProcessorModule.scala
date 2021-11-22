package com.ppb.feeds.stt.storm.topology.module
import com.ppb.feeds.stt.core.processor.Processor
import com.ppb.feeds.stt.core.processor.wordCounter.WordCountProcessor
import com.ppb.feeds.stt.storm.bolt.ProcessorBolt

trait WCProcessorModule extends ProcessorModule {
  override def getProcessor(): ProcessorBolt = {
    new ProcessorBolt() {
      lazy val processor: Processor[String, Seq[String]] = new WordCountProcessor
    }
  }
}
