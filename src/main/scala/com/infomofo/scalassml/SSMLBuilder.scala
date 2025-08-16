package com.infomofo.scalassml

import scala.xml._

/**
  * SSMLBuilder is a library built on top of scala-xml for chaining together expressions to build an SSML expression
  * tree suitable for use in Alexa Skills, and potentially also Google Home and Browser Text-To-Speech engines.
  *
  * This class follows the builder pattern with immutable data structures. Each method returns a new instance
  * rather than modifying the current one, allowing for safe composition and reuse.
  *
  * Example usage:
  * {{{
  * val ssml = SSMLBuilder()
  *   .text("Hello")
  *   .pause(1)
  *   .text("world")
  *   .sentence("This is a sentence")
  * }}}
  *
  * It is based off of:
  * https://developer.amazon.com/docs/custom-skills/speech-synthesis-markup-language-ssml-reference.html
  * https://www.ibm.com/support/knowledgecenter/en/SSMQSV_6.1.1/com.ibm.voicetools.ssml.doc/ssml_intro.html
  * http://www.w3.org/TR/speech-synthesis/
  *
  * @param ssmlTree The underlying XML element tree, defaults to an empty speak element
  */
case class SSMLBuilder(ssmlTree: Elem = <speak></speak>) {
  
  /** Convert the SSML to a trimmed string representation */
  override def toString: String = scala.xml.Utility.trim(ssmlTree).toString

  /** Extract plain text content from the SSML (useful for display cards) */
  def getCardText: String = ssmlTree.text

  /** Get the trimmed XML representation as a Node */
  def trimmedXml: Node = scala.xml.Utility.trim(ssmlTree)

  /**
    * Private helper method to append a new XML node to the SSML tree
    * Adds a space after the new content for natural speech flow
    * 
    * @param newChild The XML node to append
    * @return A new SSMLBuilder instance with the appended content
    */
  private def append(newChild: Node): SSMLBuilder = {
    SSMLBuilder(ssmlTree.copy(child = ssmlTree.child ++ newChild ++ Text(" ")))
  }

  /**
    * Append multiple XML nodes to the SSML tree
    * 
    * @param children The collection of XML nodes to append
    * @return A new SSMLBuilder instance with the appended content
    */
  def appendChildren(children: Iterable[Node]): SSMLBuilder = {
    SSMLBuilder(ssmlTree.copy(child = ssmlTree.child ++ children))
  }

  /** Add a weak break/pause (comma-like pause) to the speech */
  def comma(): SSMLBuilder = {
    append(<break strength="weak"/>)
  }

  /**
    * Add a timed pause to the speech
    * 
    * @param s Duration of pause in seconds
    * @return A new SSMLBuilder with the pause added
    */
  def pause(s: Int): SSMLBuilder = {
    append(<break time={s + "s"}/>)
  }

  /**
    * Wrap content in a paragraph element for natural speech grouping
    * 
    * @param s The SSMLBuilder content to wrap in a paragraph
    * @return A new SSMLBuilder with paragraph-wrapped content
    */
  def paragraph(s: SSMLBuilder): SSMLBuilder = {
    append(<p>
      {s.ssmlTree.child}
    </p>)
  }

  /**
    * Wrap SSMLBuilder content in a sentence element
    * 
    * @param s The SSMLBuilder content to wrap in a sentence
    * @return A new SSMLBuilder with sentence-wrapped content  
    */
  def sentence(s: SSMLBuilder): SSMLBuilder = {
    append(<s>
      {s.ssmlTree.child}
    </s>)
  }

  /**
    * Wrap string content in a sentence element
    * 
    * @param s The text content to wrap in a sentence
    * @return A new SSMLBuilder with sentence-wrapped content
    */
  def sentence(s: String): SSMLBuilder = {
    append(<s>
      {s}
    </s>)
  }

  /**
    * Add plain text content to the SSML
    * 
    * @param s The text to add
    * @return A new SSMLBuilder with the text added
    */
  def text(s: String): SSMLBuilder = {
    append(new Text(s"$s "))
  }

  /**
    * Add text with specific interpretation instructions (say-as element)
    * 
    * @param s The text content
    * @param interpretAs How the text should be interpreted (e.g., "characters", "spell-out", "cardinal")
    * @return A new SSMLBuilder with the say-as element added
    */
  def sayAs(s: String, interpretAs: String): SSMLBuilder = {
    append(<say-as interpret-as={interpretAs}>
      {s}
    </say-as>)
  }

  /**
    * Add text that should be spelled out character by character
    * 
    * @param s The text to spell out
    * @return A new SSMLBuilder with character-by-character pronunciation
    */
  def characters(s: String): SSMLBuilder = {
    sayAs(s, "characters")
  }

  /**
    * Add text substitution (sub element) for pronunciation replacement
    * 
    * @param s The original text
    * @param alias The text that should be spoken instead
    * @return A new SSMLBuilder with the substitution element added
    */
  def alias(s: String, alias: String): SSMLBuilder = {
    append(<sub alias={alias}>
      {s}
    </sub>)
  }

  /**
    * Add phonetic pronunciation using International Phonetic Alphabet (IPA)
    * 
    * @param s The original text
    * @param p The IPA phonetic representation
    * @return A new SSMLBuilder with the phoneme element added
    */
  def ipaPhoneme(s: String, p: String): SSMLBuilder = {
    append(<phoneme alphabet="ipa" ph={p}>
      {s}
    </phoneme>)
  }
}
