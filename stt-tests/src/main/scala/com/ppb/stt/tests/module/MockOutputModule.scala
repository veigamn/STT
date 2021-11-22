package com.ppb.stt.tests.module

import com.ppb.feeds.stt.core.output.OutputSource
import com.ppb.feeds.stt.core.output.mock.MockOutput
import com.ppb.feeds.stt.storm.bolt.OutputBolt
import com.ppb.feeds.stt.storm.topology.module.OutputModule
import org.apache.storm.topology.base.BaseRichBolt

trait MockOutputModule extends OutputModule {
  override def getOutput(): BaseRichBolt = {
    new OutputBolt() {
      lazy val source: OutputSource[String, String] = new MockOutput {}
    }
  }
}
