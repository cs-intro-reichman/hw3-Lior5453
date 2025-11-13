/** Functions for checking if a given string is an anagram. */
public class Anagram 
{
	public static void main(String args[]) 
	{
		// Tests the isAnagram function.
		System.out.println(isAnagram("silent","listen"));  // true
		System.out.println(isAnagram("William Shakespeare","I am a weakish speller")); // true
		System.out.println(isAnagram("Madam Curie","Radium came")); // true
		System.out.println(isAnagram("Tom Marvolo Riddle","I am Lord Voldemort")); // true

		// Tests the preProcess function.
		System.out.println(preProcess("What? No way!!!"));
		
		// Tests the randomAnagram function.
		System.out.println("silent and " + randomAnagram("silent") + " are anagrams.");
		
		// Performs a stress test of randomAnagram 
		String str = "1234567";
		Boolean pass = true;
		//// 10 can be changed to much larger values, like 1000
		for (int i = 0; i < 10; i++) {
			String randomAnagram = randomAnagram(str);
			System.out.println(randomAnagram);
			pass = pass && isAnagram(str, randomAnagram);
			if (!pass) break;
		}
		System.out.println(pass ? "test passed" : "test Failed");
	}  

	// Returns true if the two given strings are anagrams, false otherwise.
	public static boolean isAnagram(String str1, String str2) 
	{
		// first converting the 2 strings
		String fixedStr1 = preProcess(str1);
		String fixedStr2 = preProcess(str2);
		if (fixedStr1.length() != fixedStr2.length())
			return false;

		for (int i = 0; i < fixedStr1.length(); i++) // checking if fixedStr1 letters are all in fixedStr2
		{
			if (fixedStr2.indexOf(fixedStr1.charAt(i)) == -1) // if current letter in fixedStr1 isnt in fixedStr2 - will return false
				return false;
		}

		for (int i = 0; i < fixedStr2.length(); i++) // checking if fixedStr2 letters are all in fixedStr1
		{
			if (fixedStr1.indexOf(fixedStr2.charAt(i)) == -1) 
				return false;
		}

		return true;
	}
	   
	// Returns a preprocessed version of the given string: all the letter characters are converted
	// to lower-case, and all the other characters are deleted.
	// For example, the string "What? No way!" becomes "whatnoway"
	public static String preProcess(String str) 
	{
		String newString = "";
		str = str.toLowerCase(); // lowercasing all letters

		for (int i = 0; i < str.length(); i++)
		{
			if (Character.isLetter(str.charAt(i))) // if current character is a letter add it to the new string
				newString += str.charAt(i);
		}

		return newString;
	} 
	   
	// Returns a random anagram of the given string. The random anagram consists of the same
	// characters as the given string, re-arranged in a random order. 
	public static String randomAnagram(String str) 
	{
		String randomStr = "";
		String newStr = str;
		int len = str.length();

		for (int i = 0; i < str.length(); i++) // loop for the length of the function
		{
			int index = (int)(Math.random() * len); // picking random index
			String tempStr = "";
			for (int j = 0; j < len; j++)
			{
				if (j != index)
					tempStr += newStr.charAt(j); 
			}
			randomStr += newStr.charAt(index);
			newStr = tempStr;
			len--;
		}		

		return randomStr;
	}
}