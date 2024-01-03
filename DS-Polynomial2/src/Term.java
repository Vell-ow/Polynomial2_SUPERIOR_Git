public class Term implements Comparable
{
	private static final char X_SYMBOL = 'x';
	private static final char POWER_SYMBOL = '^';
	private static final char ADD_SYMBOL = '+';
	private static final char MINUS_SYMBOL = '-';

    private int mCoefficient;
    private int mExponent;

    public Term()
    {
        this.setAll(1, 1);
    }

    public Term(int coefficient, int exponent)
    {
        this.setAll(coefficient, exponent);
    }

    public Term(Term copy)
    { 
        if(copy == null)
        {
            // throw new NullPointerException();
        }
        else
        {
            this.setAll(copy.getCoefficient(), copy.getExponent());
        }
    }


    public Term(String term)
    {
        int coefficient, exponent;

        if(!term.isEmpty())
        {
        	// Term contains variable
        	if(term.contains(Character.toString(X_SYMBOL)))
        	{
        		// Initializes a new string array known as splitTerm, which categorizes 
        		String[] splitTerm = term.split(Character.toString(X_SYMBOL));
            	// Parse left side of the string
                coefficient = parseCoefficientString(splitTerm[0]);
                // If right side of variable exists
                if (splitTerm.length == 2)
                {                	
                    // Parse right side of the string
                    exponent = parseExponentString(splitTerm[1]);
                }
                else
                {
                	exponent = 1;
                }
        	}
        	else
        	{
        		// Term does not contain variable
        		coefficient = parseCoefficientString(term);
        		// System.out.println("String Constructor Coefficient (Else): " + coefficient);
        		exponent = 0;
            }     
        	
        }
        else
        {
        	coefficient = exponent = 0;
        }
        
        this.setAll(coefficient, exponent);
    }

    /**
     * Checks the contents of the coefficient string.
     *
     */
    private int parseCoefficientString(String str)
    {
    	if(str.length() == 1 && str.charAt(0) == MINUS_SYMBOL)
    	{
    		return -1;
    	}
    	else if(str.equals("") || str.charAt(0) == '0')
        {
            return 0;
        }
    	else if(str.length() == 1 && str.charAt(0) == ADD_SYMBOL)
    	{
    		return 1;
    	}
    	else
    	{
    		return Integer.parseInt(str);
    	}
    }

    /**
     * Checks the contents of the exponent string.
     *
     */
    private int parseExponentString(String str)
    {
    	return Integer.parseInt(str.substring(1));
    }

    /**
     * Sets the values for a given coefficient and a given exponent.
     * @param coefficient, exponent
     */
    public void setAll(int coefficient, int exponent)
    {
        mCoefficient = coefficient;
        mExponent = exponent;
    }

    /**
     * Checks if the coefficient is blank.
     * @param coefficient
     */
    public void setCoefficient(int coefficient)
    {
        mCoefficient = coefficient;
    }

    /**
     * Checks if the exponent is blank.
     * @param exponent
     */
    public void setExponent(int exponent)
    {
        mExponent = exponent;
    }

    /**
     * Returns the exponent.
     *
     */
    public int getExponent() { return mExponent; }

    /**
     * Returns the coefficient.
     *
     */
    public int getCoefficient() { return mCoefficient; }

    /**
     * Checks if the given Term object is equal to another given Term object.
     *
     */
    @Override
    public boolean equals(Object o) { if (this == o) { return true; }

        if (!(o instanceof Term)) { return false; }
        // Downcast to Term

        Term other = (Term) o;
        // Compare all the fields
        return (mCoefficient == other.mCoefficient) && (mExponent == other.mExponent);
    }
    
    public Term sumOf(Term other)
    {
    	return add(this, other);
    }
    
    public Term add(Term termA, Term termB)
    {
        Term temp = null;
        int coefficientSum;

        // if exponents match
        if (termA.getExponent() == termB.getExponent())
        {
            // Add both coefficients
            coefficientSum = termA.getCoefficient() + termB.getCoefficient();

            if (coefficientSum == 0)
            {
                return null;
            }
            else
            {
                temp = new Term(coefficientSum, termA.getExponent());
            }

            //temp = new Term(termA.coefficient + termB.coefficient, termA.exponent);

        }

        return temp;
    }

    /**
     * Formats the given Term object.
     *
     */
    @Override
    public String toString()
    {    	
    	String temp = "";
    	
    	// Will not run if coefficient is zero
    	if(this.mCoefficient != 0)
    	{
    		// Handles coefficient
    		if(mCoefficient > 0)
    		{
    			// Adds plus symbol where needed
    			temp += ADD_SYMBOL;
    			if(mCoefficient > 1)
    			{
    				temp += this.mCoefficient;
    			}
    		}
    		else
    		{
    			if(mCoefficient == -1)
    			{
	    			// Adds minus symbol where needed
	    			temp += MINUS_SYMBOL;
    			}
    			else
    			{
    				temp += this.mCoefficient;
    			}
    		}
    		// Handles exponent, will not run if exponent is zero
    		if(this.mExponent != 0)
    		{
    			temp += X_SYMBOL;    			
    			if(this.mExponent > 1 || this.mExponent < -1)
    			{
    				temp += POWER_SYMBOL + "" + this.mExponent;
    			}
    		}
    	}
    	return temp;
    }

    // Method is part of the Comparable interface
    // MUST implement in
    // compareTo returns an integer(whole number value). The way it could be used is something like this:
    // I have String s1, which is equal to "Hello"
    // I have String s2, which is equal to "aloha"
    // I call System.out.println(s1.compareTo(s2));
    // s1 is the String "Hello", and s2 is the String "aloha"
    // compareTo returns one of three values, integer answers
    // A negative number
    // If compareTo returns a negative number, it means that s1 is less than(smaller than) s2
    // 0
    // If compareTo returns 0, it means that s1 is equal to(exactly) s2
    // A positive number
    // If compareTo returns a positive number, it means that s1 is greater than(bigger than) s2
    
    public int compareTo(Term o)
    {

        // Downcast o into Term
        Term other = (Term) o;
       	System.out.println("compareTo " + o.toString());
        if(this.mExponent > other.getExponent())
        {
        	return 1;
        }
        else if(this.mExponent < other.getExponent())
        {
        	return -1;
        }
        else
        {
        return 0;
        }
    }

    public Term clone()
    {
        Term copy = new Term(this);

        return copy;
    }
}