# MultiplyTask

# Usage

Multiplying two big numbers.


The main code you can find under /src/main/java/com/example/demo/
  - MultiplyApplication.java
  - MultiplyController.java

Two different ways are implemented to multiply there and can be used by different urls:

1. The first way is with the BigInteger java class used for mathematical operation. 
   For this one you can type:
### Example
`curl http://localhost:8080/multiply/NUMBER1/NUMBER2` where NUMBER1 and NUMBER2 are your choice 
  
2. The second way is with end to end implemented algorithm. 
  
  We start from the last digit of second number multiply it with first number. Then we multiply second digit of second number            with first number, and so on. We add all these multiplications. While adding, we put i-th multiplication shifted.

The approach used in this solution is to keep only one array for result. We traverse all digits first and second numbers in a loop and add the result at appropriate position.

  ### Example
  `curl http://localhost:8080/multiplybigint/NUMBER1/NUMBER2` where NUMBER1 and NUMBER2 are your choice 
   
    
