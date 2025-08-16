scala-ssml
==========

![Scala CI](https://github.com/infomofo/scala-ssml/workflows/Scala%20CI/badge.svg)

This library allows you to easily generate Speech Synthesis Markup Language (SSML) with Scala.

SSML is documented [here](http://www.w3.org/TR/speech-synthesis),
 but it has applications for:

- Alexa Skills (which only supports a subset of the official ssml tags, documented [here](https://developer.amazon.com/docs/custom-skills/speech-synthesis-markup-language-ssml-reference.html))
- Chrome Browser speechSynthesis fragments, documented [here](https://developer.chrome.com/extensions/tts)

Dependency
----------

This dependency can be imported into any scala project using:

```scala
lazy val scalaSsml = RootProject(uri("git://github.com/infomofo/scala-ssml.git#v0.4.0"))
```

The library is currently compiled for scala versions `2.11.12`, `2.12.11`, and `2.13.2`.

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

GitHub Copilot Development Setup
--------------------------------

This repository is optimized for GitHub Copilot development. To get the best experience:

1. **Quick Setup**: Run `./setup-copilot.sh` to configure your development environment
2. **VS Code**: Install the Metals and GitHub Copilot extensions  
3. **IntelliJ IDEA**: Install the Scala and GitHub Copilot plugins
4. **Code Formatting**: Use `sbt scalafmtAll` to format code consistently

The project includes:
- VS Code settings with Metals configuration
- Scalafmt configuration for consistent code style
- Comprehensive ScalaDoc comments for better AI understanding
- Type annotations and clear naming conventions
- Development guides in `DEVELOPMENT.md` and `.copilot-context.md`

Testing SSML pronunciation
--------------------------

Generated SSML can be tested using Amazon's online tool at:
`https://developer.amazon.com/alexa/console/ask/test/<skillid>/development/en_US/`
where `<skillid>` is the id of an alexa skill.
