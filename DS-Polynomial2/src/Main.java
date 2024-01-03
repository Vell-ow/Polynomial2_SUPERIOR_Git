import java.util.ArrayList;
import java.util.Scanner;
import java.util.InputMismatchException;
import java.io.IOException;

/*
 * The <code>Main</code> allows the user to enter two polynomials to
 * store in a dynamic arraylist.  Users can calculate the sum of the two polynomials
 * as well as clear, modify and create new ones.
 * 
 * @author Andrew Coviello
 * @version 1.0
 */
class Main
{
    public static void main(String[] args)
    {
        int input = 0;
        int choice = 0;
        int firstCoefficient = 0;
        int firstExponent = 0;
        Term firstTerm = new Term();
        Polynomial firstPolynomial = null;
        int secondCoefficient = 0;
        int secondExponent = 0;
        Term secondTerm = new Term();
        Polynomial secondPolynomial = null;

        Scanner keyboard = new Scanner(System.in);
        
        try
        {
            do
            {
                // 1) Get the choice from the user in a loop
                System.out.print(
                        "\n********************************************************************\n"
                                + "**                                                                **\n"
                                + "**             WELCOME TO THE POLYNOMIAL CALCULATOR               **\n"
                                + "**                                                                **\n"
                                + "********************************************************************\n"
                                + "** Please select from the following choices:                      **\n"
                                + "** 1) Enter First Polynomial                                      **\n"
                                + "** 2) Enter Second Polynomial                                     **\n"
                                + "** 3) Display Sum Of The Two Polynomials                          **\n"
                                + "** 4) Exit                                                        **\n"
                                + "********************************************************************\n"
                                + ">> ");

                choice = keyboard.nextInt();
                switch (choice)
                {
                    case 1:  // First Polynomial
                        keyboard.nextLine();
                        // Create
                        // Modify
                        firstPolynomial = new Polynomial();
                        firstPolynomial.clear();
                        input = 0;
                        do
                        {
                        	System.out.println("Enter the number of terms:");
                        	input = keyboard.nextInt();
                        }
                        while(input < 1);
                        for(int i = 0; i < input; i++)
                        {
                            System.out.print("Enter Coefficient: ");
                            firstCoefficient = keyboard.nextInt();
                            System.out.print("Enter Exponent: ");
                            firstExponent = keyboard.nextInt();
                            firstTerm = new Term(firstCoefficient, firstExponent);
                            firstPolynomial.addTerm(firstTerm);
                        }
                        break;

                    case 2:  // Second Polynomial
                        keyboard.nextLine();
                        // Create
                        // Modify
                        secondPolynomial = new Polynomial();
                        secondPolynomial.clear();
                        input = 0;
                        do
                        {
                        	System.out.println("Enter the number of terms:");
                        	input = keyboard.nextInt();
                        }
                        while(input < 1);
                        for(int i = 0; i < input; i++)
                        {
                            System.out.print("Enter First Coefficient: ");
                            secondCoefficient = keyboard.nextInt();
                            System.out.print("Enter First Exponent: ");
                            secondExponent = keyboard.nextInt();
                            secondTerm = new Term(secondCoefficient, secondExponent);
                            secondPolynomial.addTerm(secondTerm);
                        }

                        break;

                    case 3:  // Display all polynomials in the list
                    	System.out.println("~~~ Sum of the Two ~~~");
                        System.out.println(firstPolynomial.add(firstTerm, secondTerm));
                        break;

                    case 4:
                        if(!firstPolynomial.equals(null))
                        {
                        	firstPolynomial.clear();
                        	System.out.println("Happy calculating!");
                        	// Clear the cache for the next calculation and exit
                        	break;
                		}
                        else
                        {
                        	System.out.println("Happy calculating!");
                        	// Clear the cache for the next calculation and exit
                        	break;
                        }
                }

            } while (choice != 4);

            keyboard.close();
        }
        catch(InputMismatchException i)
        {
            System.out.println("Unexpected error: " + i.getMessage());
        }
    }
}


