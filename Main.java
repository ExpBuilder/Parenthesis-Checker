import java.util.Scanner;

//  Class author:  Joe Jiao
//  Date created:  11/10/2025
//  General description: 
public class Main {
  public static void main(String[] args) {
    // Scanner definition
    Scanner scanner = new Scanner(System.in);


    // System text
    System.out.println("Enter input to have delimiter notation tested");

    String input = scanner.nextLine();

    if (validParantheses(input)) System.out.println("\"" + input + "\" contains vaid delimiter notation");
    else System.out.println("\"" + input + "\" contains invaid delimiter notation");


    // Closes scanner because github gets mad if I don't
    scanner.close();
  }

  //  Pre-condition: 
  //  Post-condition: 
  public static boolean validParantheses (String s) {
    // Removes all characters that are not some form of parantheses
    String simplified = "";
    for (int i = 0; i < s.length(); i++) {
      if ("(){}[]".indexOf(s.charAt(i)) != -1) simplified += s.charAt(i);
    }


    // Checks for correct amounts and ordering(ish)
    int[] typeAmount = {0, 0, 0, 0, 0, 0};
    
    for (int i = 0; i < simplified.length(); i++) {
      typeAmount["(){}[]".indexOf(simplified.charAt(i))]++;

      for (int k = 0; k < 3; k++) {
        if (typeAmount[2 * k] < typeAmount[2 * k + 1]) return false;
      }
    }
    for (int k = 0; k < 3; k++) {
      if (typeAmount[2 * k] - typeAmount[2 * k + 1] != 0) return false;
    }
    

    // Removes clearly correct outers
    while ((simplified.length() > 0) && ("([{".indexOf(simplified.charAt(0)) == ")]}".indexOf(simplified.charAt(simplified.length() - 1)))) {
      simplified = simplified.substring(1, simplified.length() - 1);
    }


    // Checks splits
    while (simplified.length() > 0) {
      // Creates splits
      int endIndex = 1;
      char endChar = ")}]".charAt("({[".indexOf(simplified.charAt(0)));

      while (simplified.charAt(endIndex) != endChar) endIndex++;

      String split = simplified.substring(0, endIndex + 1);
      System.out.println(split);
        
      // Checks split result
      if (split.length() % 2 == 1) return false;

      if (!validParantheses(split)) return false;
        
      simplified = simplified.substring(endIndex + 1);
    }


    return true;
  }
}
