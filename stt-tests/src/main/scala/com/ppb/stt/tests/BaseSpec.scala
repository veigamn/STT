package com.ppb.stt.tests

import com.ppb.feeds.stt.storm.topology.STT
import org.apache.storm.testing._
import org.apache.storm.tuple.Values
import org.apache.storm.{Config, ILocalCluster, Testing}

import scala.collection.JavaConverters._
import scala.collection.mutable

trait BaseSpec extends FeedHelper {

  def runTopology(spoutData: Map[String, List[String]], stt: STT): Map[String, List[FixedTuple]] = {

    var rawResults: java.util.Map[_, _] = new java.util.HashMap()

    val clusterConfig = new Config()
    clusterConfig.put(Config.STORM_LOCAL_MODE_ZMQ, java.lang.Boolean.FALSE)

    val cluster = new MkClusterParam()
    cluster.setSupervisors(1)
    cluster.setDaemonConf(clusterConfig)

    Testing.withLocalCluster(cluster, new TestJob {

      override def run(cluster: ILocalCluster): Unit = {
        val builder = stt
        val topology = builder.create()
        val config = builder.config()
        val sources = MockedSources(spoutData)
        val completeTopologyParam = new CompleteTopologyParam()
        completeTopologyParam.setMockedSources(sources)
        completeTopologyParam.setStormConf(config)
        rawResults = Testing.completeTopology(cluster, topology, completeTopologyParam)
      }
    })

    val resultsByBoltAndStream = rawResults.asScala.toMap.asInstanceOf[Map[String, java.util.ArrayList[FixedTuple]]]

    val results: mutable.Map[String, List[FixedTuple]] = mutable.Map()
    resultsByBoltAndStream.map(x => results.put(x._1, x._2.asScala.toList))
    results.toMap
  }

  private object MockedSources {

    def apply(instructions: Map[String, List[String]]): MockedSources = {

      val sources = new MockedSources()
      instructions.foreach {
        case (spoutId, List()) => sources.addMockData(spoutId)
        case (spoutId, its) => its.map(toValue).foreach(sources.addMockData(spoutId, _))
      }
      sources
    }

    private def toValue(phrase: String) = {
      val key = "0"
      new Values(key, phrase)
    }
  }

}
