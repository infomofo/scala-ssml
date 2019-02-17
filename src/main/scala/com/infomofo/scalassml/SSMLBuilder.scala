package com.infomofo.scalassml

import scala.xml._

/**
  * SSMLBuilder is a library built on top of scala-xml for chaining together expressions to build an SSML expression
  * tree suitable for use in Alexa Skills, and potentially also Google Home and Browser Text-To-Speech engines.
  *
  * It is based off of:
  * https://developer.amazon.com/docs/custom-skills/speech-synthesis-markup-language-ssml-reference.html
  * https://www.ibm.com/support/knowledgecenter/en/SSMQSV_6.1.1/com.ibm.voicetools.ssml.doc/ssml_intro.html
  * http://www.w3.org/TR/speech-synthesis/
  */
case class SSMLBuilder(ssmlTree: Elem = <speak></speak>) {
  override def toString: String = ssmlTree.toString

  private def append(newChild: Node): SSMLBuilder = {
    SSMLBuilder(ssmlTree.copy(child = ssmlTree.child ++ newChild ++ Text(" ")))
  }

  def appendChildren(children: Iterable[Node]): SSMLBuilder = {
    SSMLBuilder(ssmlTree.copy(child = ssmlTree.child ++ children))
  }

  def comma(): SSMLBuilder = {
    append(<break strength="medium"/>)
  }

  def pause(ms: Int): SSMLBuilder = {
    append(<break time={ms + "ms"}/>)
  }

  def paragraph(s: SSMLBuilder): SSMLBuilder = {
    append(<p>{s.ssmlTree.child}</p>)
  }

  def sentence(s: SSMLBuilder): SSMLBuilder = {
    append(<s>{s.ssmlTree.child}</s>)
  }

  def sentence (s: String): SSMLBuilder = {
    append(<s>{s}</s>)
  }

  def text(s: String): SSMLBuilder = {
    append(new Text(s"$s "))
  }

  def sayAs(s: String, interpretAs: String): SSMLBuilder = {
    append(<say-as interpret-as={interpretAs}>{s}</say-as>)
  }

  def characters(s: String): SSMLBuilder = {
    sayAs(s, "characters")
  }

  def alias(s:String, alias: String): SSMLBuilder = {
    append(<sub alias={alias}>{s}</sub>)
  }

  def ipaPhoneme(s:String, p:String): SSMLBuilder = {
    append(<phoneme alphabet="ipa" ph={p}>{s}</phoneme>)
  }
}
