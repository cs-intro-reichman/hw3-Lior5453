// Computes the periodical payment necessary to pay a given loan.
public class LoanCalc 
{	
	static double epsilon = 0.001;  // Approximation accuracy
	static int iterationCounter;    // Number of iterations 
	
	// Gets the loan data and computes the periodical payment.
    // Expects to get three command-line arguments: loan amount (double),
    // interest rate (double, as a percentage), and number of payments (int).  
	public static void main(String[] args) 
	{		
		// Gets the loan data
		double loan = Double.parseDouble(args[0]);
		double rate = Double.parseDouble(args[1]);
		int n = Integer.parseInt(args[2]);
		System.out.println("Loan = " + loan + ", interest rate = " + rate + "%, periods = " + n);

		// Computes the periodical payment using brute force search
		System.out.print("\nPeriodical payment, using brute force: ");
		System.out.println((int) bruteForceSolver(loan, rate, n, epsilon));
		System.out.println("number of iterations: " + iterationCounter);

		// Computes the periodical payment using bisection search
		System.out.print("\nPeriodical payment, using bi-section search: ");
		System.out.println((int) bisectionSolver(loan, rate, n, epsilon));
		System.out.println("number of iterations: " + iterationCounter);
	}

	// Computes the ending balance of a loan, given the loan amount, the periodical
	// interest rate (as a percentage), the number of periods (n), and the periodical payment.
	private static double endBalance(double loan, double rate, int n, double payment) 
	{
		double endValue = 0;
		for (int i = 0; i < n; i++)
		{
			double currentValue = loan - ((loan - payment) * (rate / 100 + 1)); // Adding this period payment to the sum
			endValue += currentValue;
			loan -= currentValue;
		}
		return endValue;
	}
	
	// Uses sequential search to compute an approximation of the periodical payment
	// that will bring the ending balance of a loan close to 0.
	// Given: the sum of the loan, the periodical interest rate (as a percentage),
	// the number of periods (n), and epsilon, the approximation's accuracy
	// Side effect: modifies the class variable iterationCounter.
    public static double bruteForceSolver(double loan, double rate, int n, double epsilon) 
	{
		iterationCounter = 0;
		double currentPayment = loan / n;
		while (endBalance(loan, rate, n, currentPayment) < Math.abs(loan - epsilon)) // while the payment smaller then typical range
		{
			currentPayment += epsilon;
			iterationCounter++;
		}
		return currentPayment;
    }
    
    // Uses bisection search to compute an approximation of the periodical payment 
	// that will bring the ending balance of a loan close to 0.
	// Given: the sum of the loan, the periodical interest rate (as a percentage),
	// the number of periods (n), and epsilon, the approximation's accuracy
	// Side effect: modifies the class variable iterationCounter.
    public static double bisectionSolver(double loan, double rate, int n, double epsilon) 
	{  
		iterationCounter = 0;
		double low = loan / n;
		double high = loan;
		double middle = (high + low) / 2;

		double paymentLow = endBalance(loan, rate, n, low);
		double paymentHigh = endBalance(loan, rate, n, high);

		while ((high - low) > epsilon)
		{
			middle = (high + low) / 2;
			double paymentMiddle = endBalance(loan, rate, n, middle);
			if ((paymentMiddle * paymentLow) > 0)
			{
				low = middle;
				paymentLow = paymentMiddle;
			}
			else
			{
				high = middle;
				paymentHigh = paymentMiddle;
			}

			
			iterationCounter++;
			System.out.print(middle + ", ");
		}

		return ((low + high) / 2);
    }
}
		// // Sets L and H to initial values such that 𝑓(𝐿) > 0, 𝑓(𝐻) < 0, 
		// // implying that the function evaluates to zero somewhere between L and H. 
		// // So, let’s assume that L and H were set to such initial values. 
		// // Set g to (𝐿 + 𝐻)/2 
		// while (𝐻 −𝐿) > 𝜖  {  
		// // Sets L and H for the next iteration 
		// if  𝑓(𝑔)∙𝑓(𝐿) > 0	  
		// // the solution must be between g and H 
		// // so set L or H accordingly 
		// else  
		// // the solution must be between L and g 
		// // so set L or H accordingly 
		// // Computes the mid-value (𝑔) for the next iteration  
		// } 
		// return g  