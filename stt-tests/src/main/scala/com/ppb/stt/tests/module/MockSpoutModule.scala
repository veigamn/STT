package com.ppb.stt.tests.module

import com.ppb.feeds.stt.storm.MeterKey.{KEY, PHRASE}
import com.ppb.feeds.stt.storm.topology.module.SpoutModule
import org.apache.storm.testing.FeederSpout
import org.apache.storm.topology.base.BaseRichSpout
import org.apache.storm.tuple.Fields

trait MockSpoutModule extends SpoutModule {
  override def getSpout(): BaseRichSpout = {
    new FeederSpout(new Fields(KEY.name, PHRASE.name))
  }
}
