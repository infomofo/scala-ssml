scala-ssml
==========

This library allows you to easily generate Speech Synthesis Markup Language (SSML) with Scala.

SSML is documented [here](https://developer.amazon.com/docs/custom-skills/speech-synthesis-markup-language-ssml-reference.html),
 but it has applications for:

- Alexa Skills
- Chrome Browser speechSynthesis fragments. 

And the implementation for each platform differs slightly.

Sample Usage
------------

Running Tests
-------------

Testing SSML pronunciation
--------------------------

SSML can be tested using Amazon's online tool at:
`https://developer.amazon.com/alexa/console/ask/test/<skillid>/development/en_US/`
where `<skillid>` is the id of an alexa skill.
