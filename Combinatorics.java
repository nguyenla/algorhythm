import java.util.ArrayList;

public class Combinatorics {
	private static ArrayList<String> list;

	public static boolean check(String input) {
		String s = input + input.charAt(0);
		boolean check = true;
		for (int i = 0; i < s.length()-1; i++) {
			if (s.substring(i, i+1).toLowerCase().equals(s.substring(i+1, i+2).toLowerCase())) {
				check = false;
			}
		}
		return check;
	}

	public static ArrayList<String> generate(String input) {
		ArrayList<String> list = new ArrayList<String>();

		return list;
	}

	/**
	 * List permutation of a string
	 * 
	 * @param s the input string
	 * @return  the list of permutation
	 */
	public static ArrayList<String> permutation(String s) {
	    // The result
	    ArrayList<String> res = new ArrayList<String>();
	    // If input string's length is 1, return {s}
	    if (s.length() == 1) {
	        res.add(s);
	    } else if (s.length() > 1) {
	        int lastIndex = s.length() - 1;
	        // Find out the last character
	        String last = s.substring(lastIndex);
	        // Rest of the string
	        String rest = s.substring(0, lastIndex);
	        // Perform permutation on the rest string and
	        // merge with the last character
	        res = merge(permutation(rest), last);
	    }
	    return res;
	}

	/**
	 * @param list a result of permutation, e.g. {"ab", "ba"}
	 * @param c    the last character
	 * @return     a merged new list, e.g. {"cab", "acb" ... }
	 */
	public static ArrayList<String> merge(ArrayList<String> list, String c) {
	    ArrayList<String> res = new ArrayList<String>();
	    // Loop through all the string in the list
	    for (String s : list) {
	        // For each string, insert the last character to all possible positions
	        // and add them to the new list
	        for (int i = 0; i <= s.length(); ++i) {
	            String ps = new StringBuffer(s).insert(i, c).toString();
	            res.add(ps);
	        }
	    }
	    return res;
	}
	
	public static void main(String[] args) {
		//permutation("AaBbCc");
		ArrayList<String> list = permutation("AaBbCcDdEe");
		int count = 0;
		for (int i = 0; i < list.size(); i++) {
			if (check(list.get(i))) {
				count++;
//				System.out.println(list.get(i));
			}
		}
		System.out.println(count);
	}
}
