# Scala SSML Library
This is a Scala library for generating Speech Synthesis Markup Language (SSML) with fluent method chaining. It's designed for use with Alexa Skills, Google Assistant, and browser Text-To-Speech engines.

Always reference these instructions first and fallback to search or bash commands only when you encounter unexpected information that does not match the info here.

## Working Effectively

### Install SBT (Required)
SBT is not pre-installed and must be set up manually:
```bash
# Download and extract SBT 1.3.8 (matches project/build.properties)
curl -L -o sbt-1.3.8.tgz "https://github.com/sbt/sbt/releases/download/v1.3.8/sbt-1.3.8.tgz"
tar -xzf sbt-1.3.8.tgz
# Use ./sbt/bin/sbt for all subsequent commands
```

### Build, Test, and Package
```bash
# Compile the project
./sbt/bin/sbt compile  # Takes ~20 seconds (first time includes bridge compilation)

# Run all tests - NEVER CANCEL: Set timeout to 30+ minutes for safety
./sbt/bin/sbt test  # Takes ~8 seconds. All 3 tests should pass.

# Create JAR package
./sbt/bin/sbt package  # Takes ~5 seconds. Creates target/scala-2.13/scala-ssml_2.13-0.4.0.jar

# Generate API documentation
./sbt/bin/sbt doc  # Takes ~7 seconds. Creates target/scala-2.13/api/index.html
```

### Interactive Development
```bash
# Start Scala console with project on classpath
./sbt/bin/sbt console  # Takes ~10 seconds to start

# Example usage in console:
# scala> import com.infomofo.scalassml.SSMLBuilder
# scala> val ssml = SSMLBuilder().text("hello").pause(1000).text("goodbye")
# scala> println(ssml.trimmedXml)
# scala> :quit
```

### Cross-Compilation Limitations
Do NOT attempt cross-compilation with `+compile` or `+test`. The project is configured for Scala 2.11.12 and 2.12.11 cross-compilation, but scala-xml 2.2.0 dependency is not available for Scala 2.11, causing build failures.

## Validation

### Always Test Core Functionality
After making changes to the SSML generation code, always validate with these scenarios in the Scala console:

```scala
import com.infomofo.scalassml.SSMLBuilder

// Basic text with pause
val basic = SSMLBuilder().text("hello").pause(1000).text("goodbye")
println(basic.trimmedXml)
// Expected: <speak>hello<break time="1000s"/>goodbye</speak>

// Complex SSML with multiple features
val complex = SSMLBuilder()
  .sentence("Hello world")
  .comma()
  .ipaPhoneme("namaste", "ˈnʌməsteɪ")
  .pause(2)
  .characters("ABC")
println(complex.trimmedXml)
// Expected: <speak><s>Hello world</s><break strength="weak"/><phoneme alphabet="ipa" ph="ˈnʌməsteɪ">namaste</phoneme><break time="2s"/><say-as interpret-as="characters">ABC</say-as></speak>

// Verify card text extraction
println(complex.getCardText)
// Expected: Hello world namaste ABC (plain text, no XML)
```

### Verify CI Compatibility
The GitHub Actions workflow (`.github/workflows/test.yml`) runs `sbt test` with JDK 1.8. While local development works with Java 17, ensure your changes don't break with older Java versions by checking test output carefully.

## Common Tasks

### Repository Structure
```
├── README.md              # Usage documentation and examples
├── build.sbt              # Main build configuration (Scala 2.13.13)
├── project/
│   ├── build.properties   # SBT version (1.3.8)
│   └── Dependencies.scala # ScalaTest dependency
├── src/
│   ├── main/scala/com/infomofo/scalassml/
│   │   └── SSMLBuilder.scala  # Core SSML generation library
│   └── test/scala/com/infomofo/scalassml/
│       └── SSMLBuilderSpec.scala  # Test suite (3 tests)
└── .github/workflows/test.yml     # CI configuration
```

### Key APIs to Know
- `SSMLBuilder()` - Start building SSML
- `.text(string)` - Add plain text
- `.sentence(string)` - Wrap text in `<s>` tags
- `.pause(seconds)` - Add `<break time="Ns"/>` 
- `.comma()` - Add weak break `<break strength="weak"/>`
- `.ipaPhoneme(text, pronunciation)` - Add IPA phoneme markup
- `.characters(text)` - Spell out characters using `<say-as interpret-as="characters">`
- `.alias(text, substitute)` - Text substitution with `<sub>`
- `.trimmedXml` - Get clean XML Node representation
- `.getCardText` - Extract plain text (no XML markup)

### No Formatting/Linting Tools
This project has no built-in code formatting, style checking, or linting tools configured. Maintain consistency with existing code style manually.

### Dependencies
- **scala-xml 2.2.0**: XML manipulation and generation
- **scalatest 3.1.2**: Testing framework (Test scope only)
- **Scala 2.13.13**: Primary Scala version
- **Java 17**: Runtime (despite .java-version specifying 1.11)

### Troubleshooting
- **"sbt: command not found"**: Download and extract SBT manually as shown above
- **Cross-compilation fails**: Expected behavior due to scala-xml 2.2.0 unavailability for Scala 2.11
- **Network warnings during SBT**: Normal in sandboxed environments, builds complete successfully
- **Java SecurityManager warnings**: Expected with newer Java versions, safe to ignore

### Example Build Session
```bash
# Complete build and validation workflow
curl -L -o sbt-1.3.8.tgz "https://github.com/sbt/sbt/releases/download/v1.3.8/sbt-1.3.8.tgz"
tar -xzf sbt-1.3.8.tgz
./sbt/bin/sbt compile test package doc
# Total time: ~40 seconds for complete build cycle
```