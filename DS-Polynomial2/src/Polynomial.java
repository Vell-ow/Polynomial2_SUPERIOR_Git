
import java.util.LinkedList;
import java.lang.NumberFormatException;

public class Polynomial
{
	private static final char X_SYMBOL = 'x';
	private static final char POWER_SYMBOL = '^';
	private static final char ADD_SYMBOL = '+';
	private static final char MINUS_SYMBOL = '-';
    private LinkedList<Term> mTerms;
    LinkedList<Term> terms;

    public Polynomial(Polynomial poly)
    {
    	 this.terms = new LinkedList();

         if (poly != null)
         {
             for (int i = 0; i < poly.getNumTerms(); i++)
             {
                 terms.add(new Term(poly.getTerm(i)));
             }
         }

    }

    public Polynomial()
    {
        terms = new LinkedList<Term>();
    }

    public void setTerms(LinkedList<Term> terms)
    {
        mTerms = terms;
    }

    public Term getTerm(int index)
    {
    	return new Term(this.terms.get(index));
    }

    public LinkedList<Term> getTerms()
    {
        return terms;
    }

    public int getNumTerms()
    {
        return terms.size();
    }
    
    // adds the termArg to this polynomial in the correct position, 
    // going from highest exponent to lowest.
    // If termArg's exponent and local term's exponent are the same, 
    // both terms are added.
    // Together, the result would be inserted into the position occupied
    // by the local term, unless the coefficient sum is zero, in which
    // case the local term is removed from the list.


    public void addTerm(Term termArg)
    {
    	// local variable
    	Term current;
    	// If the list is empty, add this term to index 0 
        if(this.terms.size() == 0)
        {
        	// Add the term being added to the terms list
        	this.terms.add(termArg);
        	// Break
        	return;
        }
        // Otherwise
        else
    	{
        	// For the number of indices in the size of the term array
    		for(int i = 0; i < terms.size(); i++)
    		{
    			// Assign the space of the current term to the term at this position
    			current = this.getTerm(i);
    			// If the exponent of the term being added is greater than the exponent of the current term
    			if(termArg.getExponent() > current.getExponent())
    			{
    				// Add the term being added at this position
    				terms.add(i, termArg);
    				return;
    			}
    			// Otherwise, if the two terms' exponents are the same
    			else if(termArg.getExponent() == current.getExponent())
    			{
    				// If the term being added and the current term have the same exponent and amount to zero
        			if((termArg.getCoefficient() + current.getCoefficient() == 0))
        			{
        				// Add to Zero check
        				System.out.println("If the terms' coefficients add to zero(check)");
        				// Remove the current term from the list
        				terms.remove(current);
        				return;
        			}
    				int coSum;
    				coSum = termArg.getCoefficient() + current.getCoefficient();
    				terms.add(i, new Term(coSum, current.getExponent()));
    				terms.remove(i + 1);
    				return;
    			}
    		}
    		terms.add(terms.size(), termArg);
    		return;
    	}
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Polynomial)) {
            return false;
        }
        // Downcast to Polynomial

        Polynomial other = (Polynomial) o;
        // Compare all the fields
        return terms.equals(other.terms);
    }
    
    @Override
    public String toString()
    {

    	String temp;
    	
    	if(this.terms == null || terms.size() == 0)
    	{
    		temp = "0";
    	}
    	else
    	{
    		temp = "";
    		for(Term term : terms)
    		{
    			temp += term.toString();
    		}
    		if(temp.charAt(0) == ADD_SYMBOL)
    		{
    			temp = temp.substring(1);
    		}
    	}
    	return temp;
    }

    public void add(Polynomial p)
    {
    	// For the number of terms in the polynomial
    	for(int i = 0; i < p.getNumTerms(); i++)
    	{
    		// Add this term at the specified position (i)
    		this.addTerm(p.getTerm(i));
    	}
    }

    public void clear()
    {
        terms.clear();
    }

}