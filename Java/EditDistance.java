// A Naive recursive Java program to find minimum number operations to convert str1 to str2
// Taken from http://www.geeksforgeeks.org/dynamic-programming-set-5-edit-distance/

public class EditDistance {
	public static int min(int x, int y, int z) {
		if (x<y && x<z) return x;
		if (y<x && y<z) return y;
		else return z;
	}

	public static int editDist(String s1, String s2, int m, int n){
		// If first string is empty, the only option is 
		// to insert all characters of second string into first
		if (m == 0) return n;

		// If second string is empty, the only option is 
		// to remove all characters of first string
		if (n == 0) return m;

		// If last characters of two strings are same, do nothing. 
		// Ignore last characters, get count for remaining strings.
		if (s1.charAt(m-1) == s2.charAt(n-1))
			return editDist(s1, s2, m-1, n-1);

		// If last characters are not same, consider all three 
		// operations on last character of first string, recursively
		// compute minimum cost for all three operations 
		// and take minimum of three values.
		return 1 + min ( editDist(s1,  s2, m, n-1), // Insert
				editDist(s1,  s2, m-1, n),   // Remove
				editDist(s1,  s2, m-1, n-1));
	}

	public static void main(String args[]) {
		String s1 = "sunday";
		String s2 = "saturday";
		System.out.println(editDist(s1, s2, s1.length(), s2.length()));
	}
}