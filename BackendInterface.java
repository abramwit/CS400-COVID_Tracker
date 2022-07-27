// --== CS400 File Header Information ==--
// Name: Aiden Bramwit
// Email: Bramwit@wisc.edu
// Team: BE red
// Role: Backend
// TA: Bri Cochran
// Lecturer: Gary Dahl
// Notes to Grader: <optional extra notes>

import java.util.List;

interface BackendInterface {
    
    public List<StateInterface> getSelectedStates();
    public List<String> getAllStates();
    public void addState(String state);
    public void removeState(String state);
    public String getDate();
    public void chooseDate(String date) throws IllegalArgumentException;

}
