// --== CS400 File Header Information ==--
// Name: Aiden Bramwit
// Email: Bramwit@wisc.edu
// Team: BE red
// Role: Backend
// TA: Bri Cochran
// Lecturer: Gary Dahl
// Notes to Grader: 

import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class Backend implements BackendInterface {
    List<StateInterface> allStates = new ArrayList<StateInterface>();
    List<String> currentStates = new ArrayList<String>();
    static List<String> stateStrings = new ArrayList<String>();
    static RedBlackTree<String> Tree;
    String currentDate;
    String file;
    
    /*
     * Backend constructor takes filePath as parameter from Frontend and passes
     * it to data wrangler
     */
    public Backend(String filePath) {
        // Save filePath
        file = filePath;
        
    }    

    @Override
    public List<StateInterface> getSelectedStates() {
        List<StateInterface> selected = new ArrayList<StateInterface>();       
        
        for (int i = 0; i < allStates.size(); ++i) {
            for (int j = 0; j < currentStates.size(); ++j) {
                if (allStates.get(i).getState().equals(currentStates.get(j))) {
                    selected.add(allStates.get(i));
                    break;
                }
            }
        }
        
        return selected;
    }

    @Override
    public List<String> getAllStates() {
        return stateStrings;
    }

    @Override
    public void addState(String state) {  
        if (Tree.contains(state) && !currentStates.contains(state)) {
            currentStates.add(state);
        }                
    }

    @Override
    public void removeState(String state) {
        if (Tree.contains(state) && currentStates.contains(state)) {
            currentStates.remove(state);
        }         
    }

    @Override
    public String getDate() {
        return currentDate;
    }

    @Override
    public void chooseDate(String date) throws IllegalArgumentException {
        // Set current date and clear current state selection
        currentDate = date;
        currentStates.clear();
        allStates.clear();        
        
        // Create new instance of RB Tree
        Tree = new RedBlackTree<String>();
        
        // Pass file and date to Data Wrangler
        // Throw IllegalArgumentException to Frontend if date does not exist
        try {
            StateDataReader stateReader = new StateDataReader();
            allStates = stateReader.readStateData(new FileReader(file), date);
        } catch(Exception e) {
            System.out.println("Error reading file.");            
        }
        
        if (stateStrings.isEmpty()) {
            for (int i = 0; i < allStates.size(); ++i) {
                stateStrings.add(allStates.get(i).getState());
            }
        }
        
        // Check if null
        if (allStates.isEmpty()) {
            throw new IllegalArgumentException("Invalid date");
        }
        
        // Add to RB Tree
        for (int i = 0; i < allStates.size(); ++i) {
            Tree.insert((String) allStates.get(i).getState());
        }       
       
    }

}
