#!/bin/bash

# GitHub Copilot Development Setup Script for scala-ssml
# This script helps set up the development environment for optimal GitHub Copilot integration

echo "🚀 Setting up scala-ssml for GitHub Copilot development..."

# Check if SBT is installed
if command -v sbt &> /dev/null; then
    echo "✅ SBT is installed"
else
    echo "❌ SBT is not installed. Please install SBT first."
    echo "   Visit: https://www.scala-sbt.org/download.html"
    exit 1
fi

# Check Java version
if command -v java &> /dev/null; then
    echo "✅ Java is installed"
    java -version
else
    echo "❌ Java is not installed. Please install Java 8 or higher."
    exit 1
fi

# Generate IDE metadata for better IntelliSense
echo "📋 Generating IDE metadata..."
sbt bloopInstall

# Format code according to project standards
echo "🎨 Formatting code..."
sbt scalafmtAll

# Compile project to generate semantic information
echo "🔨 Compiling project..."
sbt compile

# Run tests to verify setup
echo "🧪 Running tests..."
sbt test

echo ""
echo "🎉 Development environment setup complete!"
echo ""
echo "📝 Next steps for GitHub Copilot users:"
echo "   1. Install VS Code with the Metals extension"
echo "   2. Install GitHub Copilot extension"
echo "   3. Open this project folder in VS Code"
echo "   4. Metals will automatically detect the project configuration"
echo ""
echo "💡 For IntelliJ IDEA users:"
echo "   1. Install the Scala plugin"
echo "   2. Install GitHub Copilot plugin"
echo "   3. Import this project as an SBT project"
echo ""
echo "📚 See DEVELOPMENT.md for more details about project patterns and conventions."