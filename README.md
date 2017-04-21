# lambdaHelloWorld

This is an simple Hello World Alexa/Lambda example provided in German language.

SetUp:
1.) Use Maven Build to create the "lambdaHelloWorld-1.0-SNAPSHOT-shaded" jar file.
2.) Create a Lambda function and upload the jar file.
3.) Set an Alexa trigger. Set "handler.HelloWorldSpeechletRequestStreamHandler" as your handler.
--> The provided RequestStreamHandler will be executed by your AWS function. The calls will be handled in the provided "HelloWorldSpeechlet". Implement your logic over there!

4.) Create an Alexa Skill using the 2 propetie files under src/main/resources/alexa.
5.) Link the Alexa Skill with your lamdba function.

Speak with Alexa: "Alexa, sage <insert Alexa SKillname here> Ich möchte ein Spiel spielen."
response of Alexa "Zur Zeit kann ich mit dir noch kein Spiel spielen. Aber ich arbeite daran. Bisher unterstüze ich nur Hallo Welt. Also sage Hallo Welt."
