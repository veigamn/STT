package com.ppb.feeds.stt.storm.translator

import com.ppb.feeds.stt.storm.MeterKey.{KEY, PHRASE}
import org.apache.kafka.clients.consumer.ConsumerRecord
import org.apache.storm.kafka.spout.RecordTranslator
import org.apache.storm.tuple.{Fields, Values}

import java.util

object InstructionTranslator {
  case class Feed(headers: Map[String, String], message: String)
}

class InstructionTranslator extends RecordTranslator[String, String] {

  override def getFieldsFor(s: String): Fields = new Fields(KEY.name, PHRASE.name)

  override def streams(): util.List[String] = RecordTranslator.DEFAULT_STREAM

  override def apply(consumerRecord: ConsumerRecord[String, String]): util.List[AnyRef] = {

    val partition     = consumerRecord.partition().toString
    val message       = consumerRecord.value()

    new Values(partition, message)
  }

}
