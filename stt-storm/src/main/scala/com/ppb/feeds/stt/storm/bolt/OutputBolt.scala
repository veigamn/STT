package com.ppb.feeds.stt.storm.bolt

import com.ppb.feeds.stt.core.output.OutputSource
import com.ppb.feeds.stt.storm.MeterKey._
import com.typesafe.scalalogging.LazyLogging
import org.apache.storm.task.{OutputCollector, TopologyContext}
import org.apache.storm.topology.OutputFieldsDeclarer
import org.apache.storm.topology.base.BaseRichBolt
import org.apache.storm.tuple.Tuple

import java.util

trait OutputBolt extends BaseRichBolt with LazyLogging {

  var output: OutputCollector = _
  val source: OutputSource[String, String]

  override def prepare(map: util.Map[_, _], topologyContext: TopologyContext, outputCollector: OutputCollector): Unit = {
    output = outputCollector
  }

  override def execute(tuple: Tuple): Unit = {

    val key     = tuple.getStringByField(KEY.name)
    val market  = tuple.getValueByField(PHRASE.name).asInstanceOf[String]

    source.write(key, market)
    output.ack(tuple)
  }

  override def declareOutputFields(declarer: OutputFieldsDeclarer): Unit = {
  }
}
