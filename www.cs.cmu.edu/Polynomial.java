
/**************************************************************************
 *                           Polynomial Algebra
 *
 * This application manipulates univariate polynomials over reals.
 **************************************************************************/

import java.util.*;
import java.text.*;

public class Polynomial
{
   private Monomial head;
   private double TOLERANCE = 0.00000001;

 /*****   the  Monomial (inner) class      ********************************/

   private class Monomial
   {
      private DecimalFormat precision = new DecimalFormat("#.####");
      private int exp;                 // coeff * x^exp
      private double coeff;
      private Monomial next;

      public Monomial(int exp, double coeff, Monomial next)
      {
         this.exp = exp;
         this.coeff = coeff;
         this.next = next;
      }
      public String toString()
      {
         String form = precision.format(Math.abs(coeff));

         if(exp == 0) return form ;
         else
         if(exp == 1) return form + "*x";
         else
         return form +"*x^" + exp;
      }
      public boolean equals(Monomial mono)
      {
         return exp == mono.exp && coeff == mono.coeff;
      }

   }

   public Polynomial()
   {
      head = null;
   }

 /**
   *  Adds a new term into the polynomial, assuming that the polynomial
   *  is sorted in order from smallest to largest exponent.
   */
   public void addTerm(int exp, double coeff)
   {
      if( Math.abs(coeff) < TOLERANCE ) return;

      if( head == null || exp < head.exp )
      {
         head = new Monomial(exp, coeff, head);
         return;
      }

      Monomial cur = head;
      Monomial prev = null;

      while( cur != null && exp > cur.exp)
      {
         prev = cur;
         cur = cur.next;
      }
      if( cur == null || exp != cur.exp )
			prev.next = new Monomial(exp, coeff, cur);
      else
      {
         cur.coeff += coeff;
         if( Math.abs(cur.coeff) < TOLERANCE )
           if(prev != null)
               prev.next = cur.next;
            else
               head = head.next;
      }
   }

   public String toString()
   {
      StringBuffer sb = new StringBuffer();

      for(Monomial tmp = head; tmp != null; tmp = tmp.next)
        if(tmp.coeff < 0 )
           sb.append(" - " + tmp.toString());
        else
           sb.append(" + " + tmp.toString());

      return sb.toString();
   }

 /**
   *  Adds two polynomials
   *  The method does not change the original polynomial.
   */
   public Polynomial add(Polynomial poly)
   {
      Polynomial res = clone();

      for(Monomial tmp = poly.head; tmp != null; tmp = tmp.next)
         res.addTerm(tmp.exp, tmp.coeff);

      return res;
   }

   public Polynomial clone()
   {
      Polynomial res = new Polynomial();

      for(Monomial tmp = head; tmp != null; tmp = tmp.next)
         res.addTerm(tmp.exp, tmp.coeff);

      return res;
   }

   public boolean equals(Polynomial poly)
   {
      Monomial tmp1 = head;
      Monomial tmp2 = poly.head;

      while(tmp1 != null && tmp2 != null)
      {
         if( !tmp1.equals(tmp2) ) return false;
         tmp1 = tmp1.next;
         tmp2 = tmp2.next;
      }
      return true;
   }

 /**
   *  Multiplies by a number
   *  The method does not change the original polynomial.
   */
   public Polynomial multiply(double num)
   {
      Polynomial res = clone();

      for(Monomial tmp = res.head; tmp != null; tmp = tmp.next)
         tmp.coeff *= num;

      return res;
   }

 /**
   *  Returns a new polynomial that is the derivative of this polynomial.
   */
   public Polynomial diff()
   {
      Polynomial res = new Polynomial();

      for(Monomial tmp = head; tmp != null; tmp = tmp.next)
      {
         if(tmp.exp != 0)
            res.addTerm(tmp.exp - 1, tmp.coeff * tmp.exp );
      }

      return res;
   }

 /**
   *  Computes the polynomial at x = value
   */
   public double eval(double value)
   {
      double res = 0;

      for(Monomial tmp = head; tmp != null; tmp = tmp.next)
      {
         res += tmp.coeff * Math.pow(value, tmp.exp);
      }

      return res;
   }


   public static void main(String[] args)
   {
      Polynomial first = new Polynomial();
      Polynomial second = new Polynomial();

      System.out.println( "first" );
      first.addTerm(1, 2.1);
      first.addTerm(4, 2);
      first.addTerm(3, 1);
      first.addTerm(0, 1.3);
      first.addTerm(4, 0.3);
      System.out.println( first );
      System.out.println( );

      System.out.println( "second" );
      second.addTerm(4, -2.3);
      second.addTerm(2, 1);
      second.addTerm(0, -1.3);
      second.addTerm(1, 0.3);
      System.out.println( second );
      System.out.println( );

      System.out.println( "compare first with second" );
      System.out.println( first.equals(second) );
      System.out.println( "compare first wih first" );
      System.out.println( first.equals(first) );
      System.out.println( );

      System.out.println( "add first and second" );
      Polynomial third = first.add(second);
      System.out.println( third );
      System.out.println( first );
      System.out.println( second );
      System.out.println( );

      System.out.println( "multiply first by 0.2" );
      Polynomial fourth = first.multiply(0.2);
      System.out.println( fourth );
      System.out.println( first );
      System.out.println( );

      System.out.println( "differentiate first" );
      Polynomial diff = first.diff();
      System.out.println( diff );
      System.out.println( first );
      System.out.println( );

      System.out.println( "eval first at x = 1.5" );
      System.out.println( first.eval(1.5) );
      System.out.println( );

   }

}

