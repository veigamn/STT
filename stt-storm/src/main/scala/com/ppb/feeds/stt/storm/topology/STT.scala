package com.ppb.feeds.stt.storm.topology

import com.ppb.feeds.stt.storm.topology.module.{OutputModule, ProcessorModule, SpoutModule}
import org.apache.storm.Config
import org.apache.storm.generated.StormTopology
import org.apache.storm.topology.TopologyBuilder

trait STT extends SpoutModule
  with ProcessorModule
  with OutputModule {

  val SPOUT     : String = "KafkaSpout"
  val PROCESSOR : String = "ProcessorBolt"
  val OUTPUT    : String = "OutputBolt"

  def name() = "STT"

  def create(): StormTopology = {
    val builder = new TopologyBuilder()

    builder.setSpout(SPOUT, getSpout(), 1)

    builder.setBolt(PROCESSOR, getProcessor(), 1)
      .shuffleGrouping(SPOUT)

    builder.setBolt(OUTPUT, getOutput(), 1)
      .shuffleGrouping(PROCESSOR)

    builder.createTopology()
  }

  def config(): Config = {
    val cg = new Config
    cg.setDebug(false)
    cg.setNumWorkers(1)
    cg
  }
}
