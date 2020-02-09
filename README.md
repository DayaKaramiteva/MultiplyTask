# MultiplyTask
  
The implementation contains an algorithm for multiplication of big numbers represented as strings. The logic is exposed as a REST service using Spring Boot. The service accepts as an input the two big number strings and returns the multiplication result. The REST service is also deployed on K8s.

Requirements:
  - Java
  - Maven
  - Kubernetes
  - Running K8s Cluster

# Usage

Multiplying two big numbers.
To run the project follow these commands:

`git clone https://github.com/DayaKaramiteva/MultiplyTask.git`

`cd MultiplyTask/demo`

`chmod +x start.sh`

`./start.sh`


The main code you can find under /src/main/java/com/example/demo/
  - MultiplyApplication.java
  - MultiplyController.java

To run the program there is a dedicated start.sh script where a couple of commands are implemented:

- First we build the project with `mvn clean install` 

- Then we are creating an image with `docker build` (there is a /demo/Dockerfile for this)

- `kubectl apply -f` is looking into the yaml (some configurations are located there) file and doing the deployment

- `kubectl expose` creates a Service object that exposes the deployment


Two different multiplying implementations are available and can be used by different urls:

Before stepping into the requests we should create a port forwarding between a dedicated port on our localhost(LOCAL_PORT_CHOICE) and the dedicated port on the pod(the containerPort specified in the multitask.yaml - in our case 8080). 

This can be done by these commands:

`kubectl get pods` - from the output take the name(POD_NAME) of the pod created (i.e. `multi-task-7f69b4698-m6jcq`)

`kubectl port-forward POD_NAME LOCAL_PORT_CHOICE:8080` (i.e. `kubectl port-forward multi-task-7f69b4698-m6jcq 12345:8080`)

1. The first implementation is using the BigInteger java class which is spesialized in mathematical operations with big numbers. 
   For this one you can type:
#### Example (BigInteger)
`curl http://localhost:LOCAL_PORT_CHOICE/multiplybigint/NUMBER1/NUMBER2` where NUMBER1 and NUMBER2 are your your big number choices 
i.e. `curl http://localhost:12345/multiplybigint/5/-6543245678`
  
2. The second implementation is using an end to end implemented algorithm: 
  
  We start from the last digit of the second number and multiply it with the whole first number. Then we multiply the second digit of the second number with the whole first number, and so on. After that we are summing up all the results of these multiplications. While summing up, we are putting the i-th multiplication shifted. With each iterration we are keeping the current iterration multiplication result in an array with a reversed order.

  #### Example (Algorithm)
  `curl http://localhost:LOCAL_PORT_CHOICE/multiply/NUMBER1/NUMBER2` where NUMBER1 and NUMBER2 are your big number choices
  
  You can also type this in the web browser url:
  
  `http://localhost:LOCAL_PORT_CHOICE/multiply/-15/78`
  
  
   
