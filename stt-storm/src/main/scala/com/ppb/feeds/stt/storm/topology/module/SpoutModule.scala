package com.ppb.feeds.stt.storm.topology.module

import org.apache.storm.topology.base.BaseRichSpout

trait SpoutModule {
  def getSpout(): BaseRichSpout
}
