// Implements algebraic operations and the square root function without using 
// the Java operations a + b, a - b, a * b, a / b, a % b, and without calling 
// Math.sqrt. All the functions in this class operate on int values and
// return int values.

public class Algebra 
{
	public static void main(String args[]) 
	{
	    System.out.println(plus(2,3));   // 2 + 3
	    System.out.println(minus(7,2));  // 7 - 2
   		System.out.println(minus(2,7));  // 2 - 7
 		System.out.println(times(3,4));  // 3 * 4
   		System.out.println(plus(2,times(4,2)));  // 2 + 4 * 2
   		System.out.println(pow(5,3));      // 5^3
   		System.out.println(pow(3,5));      // 3^5
   		System.out.println(div(12,3));   // 12 / 3    
   		System.out.println(div(5,5));    // 5 / 5  
   		System.out.println(div(25,7));   // 25 / 7
   		System.out.println(mod(25,7));   // 25 % 7
   		System.out.println(mod(120,6));  // 120 % 6    
   		System.out.println(sqrt(36));
		System.out.println(sqrt(263169));
   		System.out.println(sqrt(76123));
	}  

	// Returns x1 + x2
	public static int plus(int x1, int x2) 
	{
		for (int i = 0; i < x2; i++) // loop that will run the same amount as the value of x2 and add 1 each time
		{
			x1++;
		}
		return x1;
	}

	// Returns x1 - x2
	public static int minus(int x1, int x2) 
	{
		for (int i = 0; i < x2; i++) // loop that will run the same amount as the value of x2 and minus 1 each time
		{
			x1--;
		}		
		return x1;
	}

	// Returns x1 * x2
	public static int times(int x1, int x2) 
	{
		int multi = 0;
		for (int i = 0; i < x2; i++)
		{
			multi = plus(multi, x1);
		}
		return multi;
	}

	// Returns x^n (for n >= 0)
	public static int pow(int x, int n) 
	{
		if (n == 0) // if n=0 returrning 1, else checking pow
			return 1;

		int thePow = x;
		for (int i = 1; i < n; i++) // loop goes for n-1 times because first time is me setting it to x
		{
			thePow = times(thePow, x);
		}
		return thePow;
	}

	// Returns the integer part of x1 / x2 
	public static int div(int x1, int x2) 
	{
		int currentValue = x1;
		int count = 0;
		while (currentValue >= x2)
		{
			currentValue = minus(currentValue, x2);
			count++;
		}
		return count;
	}

	// Returns x1 % x2
	public static int mod(int x1, int x2) 
	{
		int division = div(x1, x2);
		return x1 - (x2 * division);
	}	

	// Returns the integer part of sqrt(x) 
	public static int sqrt(int x) 
	{
		int i = 0;
		while (pow(i + 1, 2) < x)
		{
			i++;
		}
		return i;
	}	  	  
}