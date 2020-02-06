# MultiplyTask

# Usage

Multiplying two big numbers.
To run the project follow these commands:

`git clone https://github.com/DayaKaramiteva/MultiplyTask.git`

`cd demo`

`./start.sh`

Requirements:
  - Java
  - Maven
  - Kubernetes


The main code you can find under /src/main/java/com/example/demo/
  - MultiplyApplication.java
  - MultiplyController.java

To basically run the program there is a dedicated start.sh script where a couple of commands are implemented:

	- First we build the project with `mvn clean install` 

	- Then we are creating an image with `docker build` (there is a /demo/Dockerfile for this)

	- `kubectl apply -f` is looking into the yaml (some configurations are located there, for example here we have 2 containers to be on the safe side) file and doing the deployment

	- `kubectl expose` Create a Service object that exposes the deployment


Two different ways are implemented to multiply and can be used by different urls:

1. The first way is with the BigInteger java class used for mathematical operation. 
   For this one you can type:
### Example
`curl http://localhost:8080/multiply/NUMBER1/NUMBER2` where NUMBER1 and NUMBER2 are your choice 
  
2. The second way is with end to end implemented algorithm. 
  
  We start from the last digit of second number multiply it with first number. Then we multiply second digit of second number            with first number, and so on. We add all these multiplications. While adding, we put i-th multiplication shifted.

The approach used in this solution is to keep only one array for result. We traverse all digits first and second numbers in a loop and add the result at appropriate position.

  ### Example
  `curl http://localhost:8080/multiplybigint/NUMBER1/NUMBER2` where NUMBER1 and NUMBER2 are your choice 
  
  You can also type this in the web browser url:
  
  `http://localhost:8080/multiply/-15/78`
  
  
   
