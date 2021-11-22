package com.ppb.feeds.stt.storm

object MeterKey {

  sealed abstract class Key(val name: String)

  case object TIMESTAMP extends Key("timestamp")
  case object ACTION extends Key("action")
  case object PROVIDER extends Key("PROVIDER")
  case object PROVIDER_TIMESTAMP extends Key("PROVIDER_TIMESTAMP")
  case object MTF_TIMESTAMP_IN extends Key("TIMESTAMP_IN")
  case object SPORT_ID extends Key("SPORT")
  case object UUID extends Key("CORRELATION_ID")
  case object ID extends Key("ID")
  case object KEY extends Key("KEY")
  case object INSTRUCTION extends Key("INSTRUCTION")
  case object WORD extends Key("WORD")
  case object PHRASE extends Key("PHRASE")
  case object MARKET_ID extends Key("MARKET_ID")


  case object EVENT_ID extends Key("eventId")
  case object SEQUENCE extends Key("SEQUENCE")
  case object PROVIDER_SEQUENCE extends Key("PROVIDER_SEQUENCE")
  case object SUBCLASS_ID extends Key("subclassId")
  case object PROVIDER_LATENCY extends Key("providerLatency")
  case object PROCESSING_LATENCY extends Key("processingLatency")
  case object TOTAL_LATENCY extends Key("totalLatency")
  case object NUMBER_OF_INSTRUCTIONS extends Key("numberOfInstructions")
}


