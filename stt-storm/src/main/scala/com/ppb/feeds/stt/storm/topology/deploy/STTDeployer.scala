package com.ppb.feeds.stt.storm.topology.deploy

import com.ppb.feeds.stt.storm.topology.STT
import com.ppb.feeds.stt.storm.topology.module.{KafkaOutputModule, KafkaSpoutModule, WCProcessorModule}
import org.apache.storm.StormSubmitter

object STTDeployer {

  def main(args: Array[String]): Unit = {

    val mtf = new STT
      with KafkaSpoutModule
      with WCProcessorModule
      with KafkaOutputModule

    StormSubmitter.submitTopologyWithProgressBar(mtf.name(), mtf.config(), mtf.create())
  }
}