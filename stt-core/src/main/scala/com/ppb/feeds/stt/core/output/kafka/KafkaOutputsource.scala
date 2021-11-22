package com.ppb.feeds.stt.core.output.kafka

import com.ppb.feeds.stt.core.output.OutputSource
import com.typesafe.scalalogging.LazyLogging
import org.apache.kafka.clients.producer.{Producer, ProducerRecord}

class KafkaOutputSource(topic: String, producer: Producer[String, String]) extends OutputSource[String, String] with LazyLogging {

  override def write(key: String, in: String): String = {
    producer.send(new ProducerRecord[String, String](topic, key, in))
    in
  }
}
