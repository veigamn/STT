package com.ppb.feeds.stt.storm.bolt

import com.ppb.feeds.stt.core.processor.Processor
import com.ppb.feeds.stt.storm.MeterKey.{INSTRUCTION, KEY, PHRASE}
import com.typesafe.scalalogging.LazyLogging
import org.apache.storm.task.{OutputCollector, TopologyContext}
import org.apache.storm.topology.OutputFieldsDeclarer
import org.apache.storm.topology.base.BaseRichBolt
import org.apache.storm.tuple.{Fields, Tuple, Values}

import java.util

trait ProcessorBolt extends BaseRichBolt with LazyLogging {

  var output: OutputCollector = _
  val processor: Processor[String, Seq[String]]

  override def prepare(map: util.Map[_, _], topologyContext: TopologyContext, outputCollector: OutputCollector): Unit = {
    output = outputCollector
  }

  override def execute(tuple: Tuple): Unit = {

    val key = tuple.getValueByField(KEY.name)
    val phrase = tuple.getValueByField(PHRASE.name).asInstanceOf[String]

    logger.info(s"Phrase processed: $phrase")
    val results: Seq[String] = processor.process(phrase)

    results.map(word => output.emit(tuple, new Values(key, word)))
    output.ack(tuple)
  }

  override def declareOutputFields(outputFieldsDeclarer: OutputFieldsDeclarer): Unit = {

    outputFieldsDeclarer.declare(new Fields(KEY.name, PHRASE.name))
  }
}