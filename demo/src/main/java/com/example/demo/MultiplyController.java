package com.example.demo;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

import java.math.BigInteger;

@RestController
public class MultiplyController {

    @RequestMapping(value = "/multiplybigint/{num1}/{num2}")
    public String multiplyBigInt(@PathVariable String num1, @PathVariable String num2) {
        BigInteger a = new BigInteger(num1);
        BigInteger b = new BigInteger(num2);

        BigInteger mult = a.multiply(b);

        return "BigInteger implementation result is " + mult;
    }

    @RequestMapping(value = "/multiply/{num1}/{num2}")
    public String multiply(@PathVariable String num1, @PathVariable String num2) {
        boolean isResultNegative = false;

        if ((num1.charAt(0) == '-' || num2.charAt(0) == '-') &&
                (num1.charAt(0) != '-' || num2.charAt(0) != '-'))
            isResultNegative = true;

        if (num1.charAt(0) == '-' &&
                num2.charAt(0) != '-')
        {
            num1 = num1.substring(1);
        }
        else if (num1.charAt(0) != '-' &&
                num2.charAt(0) == '-')
        {
            num2 = num2.substring(1);
        }
        else if (num1.charAt(0) == '-' &&
                num2.charAt(0) == '-')
        {
            num1 = num1.substring(1);
            num2 = num2.substring(1);
        }

        int len1 = num1.length();
        int len2 = num2.length();
        if (len1 == 0 || len2 == 0)
            return "0";


        // will keep the result number in vector
        // in reverse order
        int result[] = new int[len1 + len2];

        // Below two indexes are used to
        // find positions in result.
        int i_n1 = 0;
        int i_n2 = 0;

        // Go from right to left in num1
        for (int i = len1 - 1; i >= 0; i--)
        {
            int carry = 0;
            int n1 = num1.charAt(i) - '0';

            // To shift position to left after every
            // multiplication of a digit in num2
            i_n2 = 0;

            // Go from right to left in num2
            for (int j = len2 - 1; j >= 0; j--)
            {
                // Take current digit of second number
                int n2 = num2.charAt(j) - '0';

                // Multiply with current digit of first number
                // and add result to previously stored result
                // charAt current position.
                int sum = n1 * n2 + result[i_n1 + i_n2] + carry;

                // Carry for next itercharAtion
                carry = sum / 10;

                // Store result
                result[i_n1 + i_n2] = sum % 10;

                i_n2++;
            }

            // store carry in next cell
            if (carry > 0)
                result[i_n1 + i_n2] += carry;

            // To shift position to left after every
            // multipliccharAtion of a digit in num1.
            i_n1++;
        }

        // ignore '0's from the right
        int i = result.length - 1;
        while (i >= 0 && result[i] == 0)
            i--;

        // If all were '0's - means either both
        // or one of num1 or num2 were '0'
        if (i == -1)
            return "0";

        // genercharAte the result String
        String s = "";

        while (i >= 0)
            s += (result[i--]);

        if(isResultNegative)
            s = "-" + s;
        return "Algorithm implementation result is: " + s + "\n";

    }


}
