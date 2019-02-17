package com.infomofo.scala_ssml

import org.scalatest.FlatSpec

class SSMLBuilderSpec extends FlatSpec {
  "A sentence" should "be well-formed xml" in {
    assert (SSMLBuilder().sentence("test").ssmlTree === <speak><s>test</s> </speak>)
  }

  "A phoneme" should "be well-formed" in {
    assert (SSMLBuilder().ipaPhoneme("namaste", "ˈnʌməsteɪ").ssmlTree ===
      <speak><phoneme alphabet="ipa" ph="ˈnʌməsteɪ">namaste</phoneme> </speak>
    )
  }

    "A pause for certain number of milliseconds" should "be formatted as a string in xml tree" in {
    assert (
      scala.xml.Utility.trim(SSMLBuilder()
        .text("hello")
        .pause(1000)
        .text("goodbye")
        .ssmlTree) ===
        <speak>hello<break time="1000ms"/>goodbye</speak>
    )
  }
}
