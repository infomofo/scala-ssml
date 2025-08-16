# GitHub Copilot Development Guide

This project uses Scala 2.13 with SBT for building Speech Synthesis Markup Language (SSML) utilities.

## Project Structure

```
src/
  main/scala/com/infomofo/scalassml/  # Main source code
    SSMLBuilder.scala                 # Core SSML builder class
  test/scala/com/infomofo/scalassml/  # Test source code
    SSMLBuilderSpec.scala            # Unit tests for SSMLBuilder
```

## Key Components

- **SSMLBuilder**: Main case class for building SSML expressions using method chaining
- **Dependencies**: Scala XML library for XML manipulation
- **Testing**: ScalaTest framework for unit testing

## Development Setup

1. Install VS Code with the Metals extension
2. Open the project folder in VS Code
3. Metals will automatically configure the project
4. Use `sbt test` to run tests
5. Use `sbt scalafmtAll` to format code

## Common Patterns

- Method chaining: `SSMLBuilder().text("hello").pause(1).text("goodbye")`
- XML literals: `<speak><s>test</s></speak>`
- Case class immutability: Each method returns a new SSMLBuilder instance

## Code Style

- Use scalafmt for consistent formatting
- Follow Scala naming conventions  
- Keep methods pure and immutable
- Write comprehensive unit tests for all public methods