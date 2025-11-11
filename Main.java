//  Class author:  Joe Jiao
//  Date created:  11/10/2025
//  General description: 
public class Main {
  public static void main(String[] args) {
    String input = "akf{}0}";

    System.out.println("\"" + input + "\" contains ");
    if (validParantheses(input, false)) System.out.print("valid");
    else System.out.print("invalid");
    System.out.print(" delimiter notation");
  }

  //  Pre-condition: 
  //  Post-condition: 
  public static boolean validParantheses (String s, boolean prevSimplified) {
    // Removes all characters that are not some form of parantheses
    String simplified = "";
    if (!prevSimplified) {
      for (int i = 0; i < s.length(); i++) {
        if ("({[]})".indexOf(s.charAt(i) + "") != -1) simplified += s.charAt(i) + "";
      }
    } else simplified = s;


    // Checks validity
    if (simplified.length() % 2 == 1) return false;


    // Checks for corresponding
    for (int j = 0; j < "({[".length(); j++) {
      int forward = 0;
      int backward = 0;

      for (int i = 0; i < simplified.length(); i++) {
        if (simplified.charAt(i) == "({[".charAt(j)) forward++;
        else backward++;
      }

      if (forward != backward) return false;
    }


    // Checks for correct directions
    int parsForward = 0;
    int curliesForward = 0;
    int braketsForward = 0;
    
    for (int i = 0; i < simplified.length(); i++) {
      if (simplified.charAt(i) == '(') parsForward++;
      else if (simplified.charAt(i) == '{') curliesForward++;
      else if (simplified.charAt(i) == '[') braketsForward++;
      else if (simplified.charAt(i) == ')') parsForward--;
      else if (simplified.charAt(i) == '}') curliesForward--;
      else braketsForward--;

      if ((parsForward < 0) || (curliesForward < 0) || (braketsForward < 0)) return false;
    }

    
    // Removes clearly correct outers
    while ((simplified.length() > 0) && ("([{".indexOf(simplified.charAt(0) + "") == ")]}".indexOf(simplified.charAt(simplified.length() - 1) + ""))) {
      simplified = simplified.substring(1, simplified.length() - 1);
    }

    // Checks splits
    while (simplified.length() > 0) {
      // Creates splits
      int endIndex = 1;
      char endChar = ")}]".charAt("({[".indexOf(simplified.charAt(0) + ""));

      while (simplified.charAt(endIndex) != endChar) endIndex++;
      
        
      // Checks split result
      if (!validParantheses(simplified.substring(0, endIndex + 1), true)) return false;
        

      // Condenses simplified
      simplified = simplified.substring(endIndex + 1);

      
      // Checks if simplified is still vaild
      if (simplified.length() % 2 == 1) return false;
    }
    return true;
  }
}
