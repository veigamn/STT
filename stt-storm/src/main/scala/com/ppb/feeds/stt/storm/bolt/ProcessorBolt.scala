package com.ppb.feeds.stt.storm.bolt

import com.ppb.feeds.stt.core.processor.Processor
import com.typesafe.scalalogging.LazyLogging
import org.apache.storm.task.{OutputCollector, TopologyContext}
import org.apache.storm.topology.OutputFieldsDeclarer
import org.apache.storm.topology.base.BaseRichBolt
import org.apache.storm.tuple.Tuple

import java.util

trait ProcessorBolt extends BaseRichBolt with LazyLogging {

  var output: OutputCollector = _
  val processor: Processor[String, Seq[String]]

  override def prepare(map: util.Map[_, _], topologyContext: TopologyContext, outputCollector: OutputCollector): Unit = {
    output = outputCollector
  }

  override def execute(tuple: Tuple): Unit = {
  }

  override def declareOutputFields(outputFieldsDeclarer: OutputFieldsDeclarer): Unit = {
  }
}