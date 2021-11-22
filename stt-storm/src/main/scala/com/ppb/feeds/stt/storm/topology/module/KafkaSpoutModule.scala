package com.ppb.feeds.stt.storm.topology.module

import com.ppb.feeds.stt.storm.translator.InstructionTranslator
import org.apache.kafka.clients.consumer.ConsumerConfig
import org.apache.storm.kafka.spout.KafkaSpoutConfig.FirstPollOffsetStrategy
import org.apache.storm.kafka.spout.{KafkaSpout, KafkaSpoutConfig}
import org.apache.storm.topology.base.BaseRichSpout

import scala.collection.JavaConverters.seqAsJavaListConverter

trait KafkaSpoutModule extends SpoutModule {
  def getSpout(): BaseRichSpout = {

    val servers = "localhost:31092"
    val topics  = List("test-input-topic").asJava

    val kConfigBuilder = new KafkaSpoutConfig.Builder[String, String](servers, topics)
      .setRecordTranslator(new InstructionTranslator())
      .setFirstPollOffsetStrategy(FirstPollOffsetStrategy.UNCOMMITTED_LATEST)
      .setProp(ConsumerConfig.GROUP_ID_CONFIG, "Kafka_Consumer_Local")

    kConfigBuilder.setProp(ConsumerConfig.MAX_PARTITION_FETCH_BYTES_CONFIG, 10485760)
    kConfigBuilder.setProp(ConsumerConfig.SESSION_TIMEOUT_MS_CONFIG, 20000)
    kConfigBuilder.setProp(ConsumerConfig.RECEIVE_BUFFER_CONFIG, 10485760)
    kConfigBuilder.setProp("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer")
    kConfigBuilder.setProp("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer")

    val kConfig = kConfigBuilder.build()
    new KafkaSpout[String,  String](kConfig)
  }
}
