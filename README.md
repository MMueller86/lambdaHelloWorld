# lambdaHelloWorld

<h2>This is an german Alexa custom skill using an AWS Lambnda function & Aws DyanamoDB as backend.</h2>. 
The Alexa skill is able to transate the words "Hello World" in german and english.

Configuration:
<h2>AWS</h2>
<p>
The backend of this skill is running in the <a href="https://console.aws.amazon.com">AWS Cloud </a>. This is a small 
manual to provide the necessarey steps. It is not an explaniation of the AWS itself. 
The understanding of the AWS is a precondition of this manual.  

</P>

<h3>Dynamo DB</h3>
<p>
This example uses an DynamoDB Database to store the translation of hello World in German and English. Do the 
following steps:
<ul>
    <li>Create a new DynamoDB Database with the table Name "helloworld"</li>
    <li>Creae an partion key "Id" as a String</li>    
</ul>

Add 2 items in the created table. The items of this example having the follwing structure:
</p>
<table>
    <tr>
        <th>Id</th>
        <th>helloWorld</th>
        <th>originalLanguage</th>
    </tr>
    <tr>
        <td>1</td>
        <td>Hello World</td>
        <td>English</td>
    </tr>
    <tr>
        <td>2</td>
        <td>Hallo Welt</td>
        <td>Deutsch</td>
    </tr>
</table>


<h3>Lambda function</h3>
<p>
    The AWS Lambda is the severless architecture implementation of the aws. 
    <ul>
        <li>Checkout the java code"</li>
        <li>perform maven package</li>
        <li>create a new lambda function</li>
        <li>upload the created shade jar (e.g. lambdaHelloWorld-1.0-SNAPSHOT-shaded.jar) to your lamdbda 
        function</li>
        <li>configure the alexa.handler.HelloWorldSpeechletRequestStreamHandler as the handler of the funtion </li>
        <li>use the existing <i>lambda_basic_execution</i> as the role  </li>
    </ul>
    
    Note: By choosing the AWS Lambda as the execution envirenment you do no know on which kind of server your code is
    running. Thats why you lambda function needs all java implementations if the dependencies inside. Nothing is 
    provided. Therefore the <i<mave shade plugin</i> is configured in the pom. During the execution of a maven 
    package command the shade plugin creates the shade jar. This jar file contains all implementations of all 
    depedencies. That is also the reason why this jar file is bigger than 10 MB. 
</p>

<h3>AIM</h3>
By default the <i>lambda_basic_execution</i> is not able to use the dynamoDB. You need to provide the group the 
needed rights in the AIM configuration to run this lambda function sucessfully.

<p><b>Please note that the DynamaoDB and the AWS Lambda function should be vreated in the same region!</b></p>

<h2>ALEXA</h2>
The Alexa skill is configured in the <a href="https://developer.amazon.com/edw/home.html#/">Amazon Developer Portal
</a>. You need to do the following steps:
<ul>
    <li>Create a new skill</li>
    <li>use AWS Lambda ARN (Amazon Resource Name) as the Service Endpoint Type </li>
    <li>confugre the url (ARN!) of your lambda function</li>
    <li>Take the file under src/main/resources/AlexaSkillConfig and copy them into the right places under 
    "Interaction Model"</li>
</ul>

Thats it. You Alexa custom skill is running right now!