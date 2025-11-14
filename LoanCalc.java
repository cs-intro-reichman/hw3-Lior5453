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
		double low  = loan / n; // minimal value of payment
		double high = loan; // max value of payment
		double mid  = 0;

		// initializing the value of both of the corners - whats missing from the loan
	    double endLow  = endBalance(loan, rate, n, low) - loan;
    	double endHigh = endBalance(loan, rate, n, high) - loan;

		while ((high - low) > epsilon)
		{
			mid = (low + high) / 2.0;
			double endMiddle = endBalance(loan, rate, n, mid) - loan;

			if (endMiddle * endLow > 0) // if still low change low values
			{
				low  = mid;
				endLow = endMiddle;
			} 
			else // change high values
			{
				high  = mid;
				endHigh = endMiddle;
			}
			iterationCounter++;
		}

    	return (low + high) / 2.0;
    }
}