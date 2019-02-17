scala-ssml
==========

This library allows you to easily generate Speech Synthesis Markup Language (SSML) with Scala.

SSML is documented [here](http://www.w3.org/TR/speech-synthesis),
 but it has applications for:

- Alexa Skills (which only supports a subset of the official ssml tags, documented [here](https://developer.amazon.com/docs/custom-skills/speech-synthesis-markup-language-ssml-reference.html))
- Chrome Browser speechSynthesis fragments, documented [here](https://developer.chrome.com/extensions/tts)

And the implementation for each platform differs slightly.

Basic Usage
------------

You can generate ssml for any of the use cases outlined above.

This library makes use of method chaining for convenience.

```scala
import com.infomofo.scalassml.SSMLBuilder

SSMLBuilder()
        .text("hello")
        .pause(1000)
        .text("goodbye")
```

The output of a command like that would be:
```xml
<speak>
    hello
    <break time="1000ms"/>
    goodbye
</speak>
```

Running Tests
-------------

Tests can be run using `sbt test`.

Testing SSML pronunciation
--------------------------

Generated SSML can be tested using Amazon's online tool at:
`https://developer.amazon.com/alexa/console/ask/test/<skillid>/development/en_US/`
where `<skillid>` is the id of an alexa skill.
