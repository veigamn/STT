package com.ppb.stt.tests.module

import com.ppb.feeds.stt.core.processor.Processor
import com.ppb.feeds.stt.core.processor.mock.MockProcessor
import com.ppb.feeds.stt.storm.bolt.ProcessorBolt
import com.ppb.feeds.stt.storm.topology.module.ProcessorModule

trait MockProcessorModule extends ProcessorModule {
  override def getProcessor(): ProcessorBolt = {

    new ProcessorBolt() {
      lazy val processor: Processor[String, Seq[String]] = new MockProcessor
    }
  }
}
