package com.infomofo.scalassml

import org.scalatest.flatspec.AnyFlatSpec

class SSMLBuilderSpec extends AnyFlatSpec {
  "A sentence" must "be well-formed xml" in {
    assert (SSMLBuilder().sentence("test").trimmedXml === <speak><s>test</s></speak>)
  }

  "A phoneme" must "be well-formed" in {
    assert (SSMLBuilder().ipaPhoneme("namaste", "ˈnʌməsteɪ").trimmedXml ===
      <speak><phoneme alphabet="ipa" ph="ˈnʌməsteɪ">namaste</phoneme></speak>
    )
  }

    "A pause for certain number of milliseconds" must "be formatted as a string in xml tree" in {
    assert (
      SSMLBuilder()
        .text("hello")
        .pause(1)
        .text("goodbye")
        .trimmedXml ===
        <speak>hello<break time="1s"/>goodbye</speak>
    )
  }

  "A comma" must "be formatted as a weak break" in {
    assert(
      SSMLBuilder()
        .comma()
        .trimmedXml ===
        <speak><break strength="weak"/></speak>
    )
  }

  "Text" must "be appended with spaces" in {
    assert(
      SSMLBuilder()
        .text("hello")
        .text("world")
        .trimmedXml ===
        <speak>hello world</speak>
    )
  }

  "Say-as with characters" must "be well-formed" in {
    assert(
      SSMLBuilder()
        .characters("ABC")
        .trimmedXml ===
        <speak><say-as interpret-as="characters">ABC</say-as></speak>
    )
  }

  "Say-as with custom interpretation" must "be well-formed" in {
    assert(
      SSMLBuilder()
        .sayAs("12345", "number")
        .trimmedXml ===
        <speak><say-as interpret-as="number">12345</say-as></speak>
    )
  }

  "Alias substitution" must "be well-formed" in {
    assert(
      SSMLBuilder()
        .alias("NATO", "North Atlantic Treaty Organization")
        .trimmedXml ===
        <speak><sub alias="North Atlantic Treaty Organization">NATO</sub></speak>
    )
  }

  "Paragraph with SSMLBuilder" must "be well-formed" in {
    val innerSsml = SSMLBuilder().text("inner content")
    assert(
      SSMLBuilder()
        .paragraph(innerSsml)
        .trimmedXml ===
        <speak><p>inner content</p></speak>
    )
  }

  "Sentence with SSMLBuilder" must "be well-formed" in {
    val innerSsml = SSMLBuilder().text("inner sentence")
    assert(
      SSMLBuilder()
        .sentence(innerSsml)
        .trimmedXml ===
        <speak><s>inner sentence</s></speak>
    )
  }

  "AppendChildren" must "add multiple nodes" in {
    val children = List(<break time="1s"/>, scala.xml.Text("test"))
    assert(
      SSMLBuilder()
        .appendChildren(children)
        .trimmedXml ===
        <speak><break time="1s"/>test</speak>
    )
  }

  "ToString" must "return trimmed XML string" in {
    val ssml = SSMLBuilder().text("hello")
    val result = ssml.toString
    assert(result === "<speak>hello</speak>")
  }

  "GetCardText" must "return plain text without XML tags" in {
    val ssml = SSMLBuilder()
      .sentence("Hello world")
      .comma()
      .ipaPhoneme("namaste", "ˈnʌməsteɪ")
      .pause(2)
      .characters("ABC")
    
    val cardText = ssml.getCardText.trim
    assert(cardText === "Hello world\n      \n      namaste\n      \n      ABC")
  }
}
