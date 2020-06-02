package com.infomofo.scalassml

import org.scalatest.FlatSpec

class SSMLBuilderSpec extends FlatSpec {
  "A sentence" should "be well-formed xml" in {
    assert (SSMLBuilder().sentence("test").trimmedXml === <speak><s>test</s></speak>)
  }

  "A phoneme" should "be well-formed" in {
    assert (SSMLBuilder().ipaPhoneme("namaste", "ˈnʌməsteɪ").trimmedXml ===
      <speak><phoneme alphabet="ipa" ph="ˈnʌməsteɪ">namaste</phoneme></speak>
    )
  }

    "A pause for certain number of milliseconds" should "be formatted as a string in xml tree" in {
    assert (
      SSMLBuilder()
        .text("hello")
        .pause(1)
        .text("goodbye")
        .trimmedXml ===
        <speak>hello<break time="1s"/>goodbye</speak>
    )
  }
}
