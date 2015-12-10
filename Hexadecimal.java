//Team 6112FFE -- Jannie Li, Nick Ng
//APCS1 pd10
//HW44 -- This or That or Fourteen Other Things
//2015-12-8


public class Hexadecimal {

    private final static String HEXDIGITS = "0123456789ABCDEF";
    private int _decNum;
    private String _hexNum;


    /*=====================================
      default constructor
      pre:  n/a
      post: initializes _decNum to 0, _hexNum to "0"
      =====================================*/
    public Hexadecimal() { 
        _decNum = 0;
	_hexNum = "0";
    }


    /*=====================================
      overloaded constructor
      pre:  n >= 0
      post: sets _decNum to n, _hexNum to equiv string of bits
      =====================================*/
    public Hexadecimal( int n ) {
        _decNum = n;
	_hexNum = decToHex(n);
    }


    /*=====================================
      overloaded constructor
      pre:  s is String representing non-negative binary number
      post: sets _hexNum to input, _decNum to decimal equiv
      =====================================*/
    public Hexadecimal( String s ) {
        _hexNum = s;
	_decNum = hexToDec(s);
    }


    /*=====================================
      String toString() -- returns String representation of this Object
      pre:  n/a
      post: returns String of 1's and 0's representing value of this Object
      =====================================*/
    public String toString() { 
	return _hexNum;  
    }


    /*=====================================
      String decToHex(int) -- converts base-10 input to binary
      pre:  n >= 0
      post: returns String of bits
      eg  decToHex(0) -> "0"
      decToHex(1) -> "1"
      decToHex(2) -> "10"
      decToHex(3) -> "11"
      decToHex(14) -> "1110"
      =====================================*/
    public static String decToHex( int n ) {
        String ret = "";

	//add hexdigit at proper index (remainder) backwards until n < 10
	//then add it to ret
	while (n > 9) { //still need 0 case...?
	    ret = HEXDIGITS.substring(n % 16, n % 16 + 1) + ret;
	    n /= 16;
	}

	return n + ret;
    }


    /*=====================================
      String decToHexR(int) -- converts base-10 input to binary, recursively
      pre:  n >= 0
      post: returns String of bits
      eg  decToHexR(0) -> "0"
      decToHexR(1) -> "1"
      decToHexR(2) -> "10"
      decToHexR(3) -> "11"
      decToHexR(14) -> "1110"
      =====================================*/
    public static String decToHexR( int n ) {	
	//until n == 0, add remainder with 2 backward and recurse on quotient	
	if (n < 10) {
	    return n + "";
	    //this will also make the ints into a String: n + ""
	}
	
	return decToHexR(n / 16) + HEXDIGITS.substring(n % 16, n % 16 + 1); 
    }


    /*=====================================
      String hexToDec(String) -- converts base-10 input to binary
      pre:  s represents non-negative binary number
      post: returns decimal equivalent as int
      eg  
      hexToDec("0") -> 0
      hexToDec("1") -> 1
      hexToDec("10") -> 2
      hexToDec("11") -> 3
      hexToDec("1110") -> 14
      =====================================*/
    public static int hexToDec( String s ) {
        int dec = 0;

	//add to dec the index of each digit multiplied by 16^index in s
	//(the value of each hexadecimal digit)
	for (int i = 0 ; i < s.length() ; i++) {
	    dec += HEXDIGITS.indexOf(s.substring(i,i+1))
		* Math.pow(16,s.length() - 1 - i);
	}

	return dec;
    }


    /*=====================================
      String hexToDecR(String) -- converts base-10 input to binary, recursively
      pre:  s represents non-negative binary number
      post: returns decimal equivalent as int
      eg  
      hexToDecR("0") -> 0
      hexToDecR("1") -> 1
      hexToDecR("10") -> 2
      hexToDecR("11") -> 3
      hexToDecR("1110") -> 14
      =====================================*/
    public static int hexToDecR( String s ) { 
        if (s.length() == 0) {
	    return 0;
	}

	//add the indices of each digit multiplied by 16^index in s
	//(the value of each hexadecimal digit)
	return HEXDIGITS.indexOf(s.substring(0,1)) * (int)Math.pow(16,s.length()-1)
	    + hexToDecR(s.substring(1));
    }


    /*=============================================
      boolean equals(Object) -- tells whether 2 Objs are equivalent
      pre:  other is an instance of class Hexadecimal
      post: Returns true if this and other are aliases (pointers to same 
      Object), or if this and other represent equal binary values
      =============================================*/
    public boolean equals( Object other ) {

	//both Hexadecimal objects? if not, error!
	if (!(other instanceof Hexadecimal)){
	    throw new ClassCastException (".equals() Input not a hex");
	}
	
	//aliases?
	boolean ret = this == other;
	
	//values equal?
	if (!ret) {
	    ret = this._decNum == ((Hexadecimal)other)._decNum; 
	    //and/or... this._hexNum.equals((Hexadecimal)other)._hexNum;
	}
	
	return ret;
    }


    /*=============================================
      int compareTo(Object) -- tells which of two Hexadecimal objects is greater
      pre:  other is instance of class Hexadecimal
      post: Returns 0 if this Object is equal to the input Object,
      negative integer if this<input, positive integer otherwise
      =============================================*/
    public int compareTo( Object other ) {
	
	//both Hexadecimal objects? if not, error!
	if (!(other instanceof Hexadecimal)){
	    throw new ClassCastException (".compareTo() Input not a hex");
	}
	
	//return difference
	return this._decNum - ((Hexadecimal)other)._decNum;
    }


    //main method for testing
    public static void main( String[] args ) {


	System.out.println();
	System.out.println( "Testing ..." );

	Hexadecimal b1 = new Hexadecimal(5);
	Hexadecimal b2 = new Hexadecimal(5);
	Hexadecimal b3 = b1;
	Hexadecimal b4 = new Hexadecimal(7);

	System.out.println( b1 );
	System.out.println( b2 );
	System.out.println( b3 );       
	System.out.println( b4 );       

	System.out.println( "\n==..." );
	System.out.println( b1 == b2 ); //should be false
	System.out.println( b1 == b3 ); //should be true

	System.out.println( "\n.equals()..." );
	System.out.println( b1.equals(b2) ); //should be true
	System.out.println( b1.equals(b3) ); //should be true
	System.out.println( b3.equals(b1) ); //should be true
	System.out.println( b4.equals(b2) ); //should be false
	System.out.println( b1.equals(b4) ); //should be false

	System.out.println( "\n.compareTo..." );
	System.out.println( b1.compareTo(b2) ); //should be 0
	System.out.println( b1.compareTo(b3) ); //should be 0
	System.out.println( b1.compareTo(b4) ); //should be neg
	System.out.println( b4.compareTo(b1) ); //should be pos

	  
	System.out.println("\nConverters...");
	  
	Hexadecimal b5 = new Hexadecimal("1AA");
	Hexadecimal b6 = new Hexadecimal(426);

	System.out.println( b5._decNum ); //should be 426
	System.out.println( b6 ); //should be 1AA

	System.out.println( decToHexR(21) ); //should be 15
	System.out.println( decToHexR(30031) ); //should be 754F
	System.out.println( hexToDec("16") ); //should be 22
	System.out.println( hexToDec("1AA") ); //should be 426


	System.out.println("\nExceptions...");
	Object b7 = new Object();
	//System.out.println( b6.equals(b7) );
	System.out.println( b6.compareTo(b7) );
	
	/*=========================================	  
	  =========================================*/

    }//end main()

} //end class
