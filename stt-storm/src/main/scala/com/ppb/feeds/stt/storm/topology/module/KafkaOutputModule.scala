package com.ppb.feeds.stt.storm.topology.module
import com.ppb.feeds.stt.core.output.OutputSource
import com.ppb.feeds.stt.core.output.kafka.KafkaOutputSource
import com.ppb.feeds.stt.storm.bolt.OutputBolt
import org.apache.kafka.clients.producer.KafkaProducer
import org.apache.storm.topology.base.BaseRichBolt

import java.util.Properties

trait KafkaOutputModule extends OutputModule {
  override def getOutput(): BaseRichBolt = new OutputBolt() {

      lazy val props = new Properties()
      props.put("bootstrap.servers", "localhost:31092")
      props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer")
      props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer")

      lazy val producer: KafkaProducer[String, String]            = new KafkaProducer[String, String](props)
      lazy val source: OutputSource[String, String]  = new KafkaOutputSource("test-output-topic", producer)
  }
}
