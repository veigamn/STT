package com.ppb.feeds.stt.storm.topology.module

import org.apache.storm.topology.base.BaseRichBolt

trait OutputModule {
  def getOutput(): BaseRichBolt

}
