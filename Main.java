import java.util.Scanner;

//  Class author:  Joe Jiao
//  Date created:  11/10/2025
//  General description: The project takes user input and outputs wether the user input follows correct delimiter notation.
public class Main {
  public static void main(String[] args) {
    // Scanner definition
    Scanner scanner = new Scanner(System.in);


    // System text
    System.out.println("Enter input to have delimiter notation tested");

    String input = scanner.nextLine();

    if (validDelimiter(input)) System.out.println("\"" + input + "\" contains vaid delimiter notation");
    else System.out.println("\"" + input + "\" contains invaid delimiter notation");


    // Closes scanner because github gets mad if I don't
    scanner.close();
  }

  //  Pre-condition: The function requires the input of a string
  /*  Post-condition: The function will return a boolean on wether the input had correct delimiter notation. 
  The rules for delimiter notation are: 
  - Open brackets must be closed by the same type of brackets.
  - Open brackets must be closed in the correct order.
  - Every close bracket has a corresponding open bracket of the same type.
  */
  public static boolean validDelimiter (String s) {
    // Removes all characters that are not some form of parantheses
    String simplified = "";
    for (int i = 0; i < s.length(); i++) {
      if ("(){}[]".indexOf(s.charAt(i)) != -1) simplified += s.charAt(i);
    }

    if (simplified.length() % 2 == 1) return false;

    for (int i = simplified.length() - 1; i >= 0; i--) {
      if ("([{".indexOf(simplified.charAt(i)) != -1) {
        int char1Type = "([{".indexOf(simplified.charAt(i));
        int char2Type = ")]}".indexOf(simplified.charAt(i + 1));

        if (char1Type == char2Type) {
          if (i + 2 > simplified.length()) {
            simplified = simplified.substring(0, i);
          } else {
            simplified = simplified.substring(0, i) + simplified.substring(i + 2);
          }

          i = simplified.length() - 1;

        } else return false;
      }
    }

    if (simplified.length() > 0) return false;
    else return true;
  }
}
