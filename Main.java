public class Main {
  public static void main(String[] args) {
    //System.out.println(validParantheses("({)}"));
    System.out.println(validParantheses("(){xzvszdfsz}()", false));
  }

  public static boolean validParantheses (String s, boolean prevSimplified) {
    // Removes all characters that are not some form of parantheses
    String simplified = "";
    if (!prevSimplified) {
      for (int i = 0; i < s.length(); i++) {
        if ("({[]})".indexOf(s.substring(i, i+1)) != -1) simplified += s.substring(i, i+1);
      }
    } else simplified = s;

    // Checks for corresponding
    if (simplified.length() % 2 == 1) return false;

    for (int j = 0; j < "({[".length(); j++) {
      int forward = 0;
      int backward = 0;

      for (int i = 0; i < simplified.length(); i++) {
        if (simplified.charAt(i) == "({[".charAt(j)) forward++;
        if (simplified.charAt(i) == ")}]".charAt(j)) backward++;
      }

      if (forward != backward) return false;
    }
    System.out.println("Corresponding exist");


    // Checks for starting facing the right direction
    int parsForward = 0;
    int curliesForward = 0;
    int braketsForward = 0;
    for (int i = 0; i < simplified.length(); i++) {
      if (simplified.charAt(i) == '(') parsForward++;
      if (simplified.charAt(i) == '{') curliesForward++;
      if (simplified.charAt(i) == '[') braketsForward++;

      if (simplified.charAt(i) == ')') parsForward--;
      if (simplified.charAt(i) == '}') curliesForward--;
      if (simplified.charAt(i) == ']') braketsForward--;

      if ((parsForward == -1) || (curliesForward == -1) || (braketsForward == -1)) return false;
    }
    System.out.println("Correct Directions");
    
    // Removes clearly correct outers
    while ((simplified.length() > 0) && (simplified.charAt(0) == simplified.charAt(simplified.length() - 1))) {
      simplified = simplified.substring(1, simplified.length() - 2);
    }

    // Splits slimplified
    if (simplified.length() > 2) {
      while (simplified.length() > 0) {
        int endIndex = 1;
        char endChar = ")}]".charAt("({[".indexOf(simplified.substring(0,1)));

        while (simplified.charAt(endIndex) != endChar) endIndex++;
        
        // Checks split result
        if (!validParantheses(simplified.substring(0, endIndex + 1), true)) return false;
        
        // Condenses simplified
        simplified = simplified.substring(endIndex + 1);

        if (simplified.length() % 2 == 1) return false;
      }
      return true;
    }
    else return true;
  }
}
