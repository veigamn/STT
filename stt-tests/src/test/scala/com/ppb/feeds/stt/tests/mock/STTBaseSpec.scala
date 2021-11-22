package com.ppb.feeds.stt.tests.mock

import com.ppb.feeds.stt.storm.topology.STT
import com.ppb.feeds.stt.storm.topology.module.WCProcessorModule
import com.ppb.stt.tests.BaseSpec
import com.ppb.stt.tests.module.{MockOutputModule, MockSpoutModule}
import org.scalatest.Matchers.convertToAnyShouldWrapper
import org.scalatest.{FlatSpec, GivenWhenThen}

class STTBaseSpec extends FlatSpec with BaseSpec with GivenWhenThen {

  "STT" should "consume message from instructions spout, process it and output the result" in {

    Given("a simple create instruction")

    val stt = new STT
      with MockSpoutModule
      with WCProcessorModule
      with MockOutputModule

    When("processed")

    val results = runTopology(Map(stt.SPOUT -> List("Test input message", "Test input message 2")), stt)

    Then("the result contains two valid tuples emitted by the Kafka Spout and Processor Bolt")

    results.size shouldBe  2

    val instructionsTuple = results.get(stt.SPOUT)
    val processorTuple    = results.get(stt.PROCESSOR)

    instructionsTuple.isDefined shouldBe true
    processorTuple.isDefined shouldBe true
  }
}