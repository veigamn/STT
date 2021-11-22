package com.ppb.feeds.stt.tests.mock

import com.ppb.stt.tests.BaseSpec
import org.scalatest.{FlatSpec, GivenWhenThen}

class STTBaseSpec extends FlatSpec with BaseSpec with GivenWhenThen {

  "STT" should "consume message from spout, process it and output the result" in {

    Given("a simple message")

    When("processed")

    Then("the result contains two valid tuples emitted by the Kafka Spout and Processor Bolt")

  }
}