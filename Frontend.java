
import java.util.List;
import java.util.Scanner;

public class Frontend {

    static BackendInterface backend;
    static String date;
    static List<StateInterface> currentStates;
    static List<String> possibleStates;
    static Scanner scan;
    
    public static void main(String[] args) {
      String filePath = "us_state_vaccinations_f.csv";
      
      scan = new Scanner(System.in);
      
      //run loadBackend() to pass file back
      loadBackend(filePath);
      
      //application ends at this point -> goodbye message
      System.out.println("Thanks for using COVID-19 Vaccination Tracker 2021. Goodbye");
    }
    
    
    public static void loadBackend(String filePath) {
      
      backend = new Backend(filePath);
      
      possibleStates = backend.getAllStates();
      System.out.println(backend.getAllStates().size());
      
      //Welcome message prints once
      System.out.println("*********Welcome to COVID-19 Vaccination Tracker 2021!*********" +
      "\n***************************************************************");
      //enter base mode to start application
      baseMode();
      }
    
    /*
     * Base mode of the application. This is where information on selected criteria is displayed and
     * the other two modes can be accessed from.
     * 
     * @throws IllegalArgumentException if user input does not match any commands
     */
  public static void baseMode() throws IllegalArgumentException {
    String exit = "x";
    String stateSelection = "s";
    String dateSelection = "d";
    String input = "empty";

    // allow user input and check commands to enter other modes
    while (!input.equals(exit)) {
      // Display instructions
      System.out.println(
          "Base Mode: Press x to exit application" + "\n-To select a date of interest, enter  d"
              + "\n-To select states of interest, enter s");

      try {
        // Display data for selected states and date
        if(currentStates != null) {
          System.out.println("\nCOVID-19 Vaccination Data for Selected States on: " + date);
          for (int i = 0; i < currentStates.size(); i++) {
            String stateData = currentStates.get(i).toString();
            System.out.println(i + 1 + ") " + stateData);
          }
        }

        // accept any input from user
        input = scan.nextLine();

        // enter modes from input. If input matches no known command, exception is thrown
        if (input != null) {
          if (input.equals(dateSelection)) {
            dateSelectionMode();
          } else if (input.equals(stateSelection)) {
            stateSelectionMode();
          } else if (!input.equals(exit)) {
            throw new IllegalArgumentException("Input not accepted. Try again");
          }
        }
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
  }
    
    /*
     * This method serves as the mode where a user can select states. 
     */
  public static void stateSelectionMode() {
    String exit = "x";
    String input = "empty";

    // instructions
    System.out.println("State Selection Mode: Input \'x\' at any time to exit"
        + "\n**To select/deselect state, input its corresponding number**");


    // enter user interaction
    while (!input.equals(exit)) {
      // print available states and selected states
      System.out.print("\n\nStates to Select/Deselect: ");
      for (int i = 0; i < possibleStates.size(); i++) {
        System.out.print("(" + i + 1 + ") " + possibleStates.get(i) + ", ");
      }
      System.out.print("\nCurrently Selected States: ");
      for (int i = 0; i < currentStates.size(); i++) {
        System.out.print(currentStates.get(i).getState() + ", ");
      }

      // take input
      input = scan.nextLine();

      // find state indicated by inputed number if the input is valid and pass it to backend
      // or remove it from the list of selected states
      try {
        if (checkBounds(input)) {
          for (int i = 0; i < possibleStates.size(); i++) {
            if (Integer.parseInt(input) == i) {
              // determine if inputed state already exists in currentStates
              boolean exists = false;
              for (int j = 0; j < currentStates.size(); j++) {
                if (currentStates.get(j).getState().equals(possibleStates.get(i)))
                  exists = true;
              }
              // if state doesnt already exist, add it
              if (!exists) {
                backend.addState(possibleStates.get(i));
              }
              // otherwise remove it
              else {
                backend.removeState(possibleStates.get(i));
              }
            }
            update();
          }
        }
      } catch (IndexOutOfBoundsException e) {
        System.out.println("Not in the range of numbers to select. Try again");
      }
    }
  }
    
  public static void dateSelectionMode() {
    String exit = "x";
    String input = "empty";

    // instructions
    System.out.println("Date Selection Mode: Enter \'x\' at any time to exit"
        + "To select a date, enter it in the format yyyy-mm-dd. "
        + "\nAvailable dates to select are from 2021-01-12 to 2021-03-07");

    // start user input
    while (!input.equals(exit)) {
      try {
        input = scan.nextLine();

        // pass date to backend. If it is not valid backend will throw an exception
        if(!input.equals(exit))
          backend.chooseDate(input);
        // update and print selected date
        update();
        System.out.println("The currently selected date is: " + date);
      } catch (IllegalStateException e) {
        System.out.println("Scanner was unexpectedly closed");
      } catch (IllegalArgumentException e) {
        System.out.println("Date not accepted. Please try again. ");
      }
    }
  }
    
    /*
     * This method updates information in instance variables every time the application leaves
     * either selection mode
     */
    public static void update() {
      currentStates = backend.getSelectedStates();
      date = backend.getDate();
    }
    
    /*
   * Checks if input given in stateSelectionMode() can be parsed, is not x, and falls within 1-50
   */
  public static boolean checkBounds(String input) {
    // x is not an illegal argument, however it does fit fit the bounds
    if (input.equals("x")) {
      return false;
    }

    int numInput = Integer.parseInt(input);
    if (numInput >= 1 && numInput <= 50) { return true; } 
    else throw new IndexOutOfBoundsException("Input not accepted. Try again");
  }
}

