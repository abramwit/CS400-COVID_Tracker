// --== CS400 File Header Information ==--
// Name: Aiden Bramwit
// Email: Bramwit@wisc.edu
// Team: BE red
// Role: Backend
// TA: Bri Cochran
// Lecturer: Gary Dahl
// Notes to Grader: <optional extra notes>

import static org.junit.Assert.fail;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;

public class BackendTester {
        
    /*
     * This method tests the chooseDate() method
     * 
     * @return boolean
     */
    @Test
    public static void test1() {
        // Test 1 - IllegalArgumentException Thrown if date DNE
        try {
            Backend backend = new Backend("file");
            backend.chooseDate("Incorrect Date");
            fail("Test1 failed");
        } catch (IllegalArgumentException e) {}  
        
        // Test 2 - If date exists, states properly gathered and added to RB Tree
        Backend backend2 = new Backend("file");
        backend2.chooseDate("3/19");
        String states = backend2.Tree.toString();
        
        if (!states.equals("[ Connecticut, Florida, Minnesota, New York, Wisconsin ]")) {
            fail("Test1 failed");
        }
        
        // Test 3 - If new date passed to method RB Tree adds new state statistics
        backend2.chooseDate("3/20");
        String states2 = backend2.Tree.toString();
        
        if (!states2.equals("[ Arizona, Illinois, Massachusetts, Texas, Vermont ]")) {
            fail("Test1 failed");
        }

        
    }
    
    /*
     * This method tests the add method and get selected states
     * 
     * @return boolean
     */
    @Test
    public static void test2() {
        Backend backend = new Backend("file");
        backend.chooseDate("3/19");
        
        // Test 1: Add one state
        backend.addState("Wisconsin");
        String states = "";
        List<StateInterface> selected = new ArrayList<StateInterface>();
        selected = backend.getSelectedStates();
        for (int i = 0; i < selected.size(); ++i) {
            states += selected.get(i).getState() + " ";
        }
        
        if (!states.equals("Wisconsin ")) {
            fail("Test2 failed");
        }
        
        // Test 2: Add another state
        backend.addState("Florida");
        String states2 = "";
        selected.clear();
        selected = backend.getSelectedStates();
        for (int i = 0; i < selected.size(); ++i) {
            states2 += selected.get(i).getState() + " ";
        }
        
        if (!states2.equals("Florida Wisconsin ")) {
            fail("Test2 failed");
        }
        
        // Test 3: Add state already in list - Should not be added
        backend.addState("Florida");
        String states3 = "";
        selected.clear();
        selected = backend.getSelectedStates();
        for (int i = 0; i < selected.size(); ++i) {
            states3 += selected.get(i).getState() + " ";
        }
        
        if (!states3.equals("Florida Wisconsin ")) {
            fail("Test2 failed");
        }
        
        // Test 4: Add state that DNE - Should not be added
        backend.addState("Whosville");
        String states4 = "";
        selected.clear();
        selected = backend.getSelectedStates();
        for (int i = 0; i < selected.size(); ++i) {
            states4 += selected.get(i).getState() + " ";
        }
        
        if (!states4.equals("Florida Wisconsin ")) {
            fail("Test2 failed");
        }
        
        // Test 5: Add rest of the states
        backend.addState("Connecticut");
        backend.addState("New York");
        backend.addState("Minnesota");
        String states5 = "";
        selected.clear();
        selected = backend.getSelectedStates();
        for (int i = 0; i < selected.size(); ++i) {
            states5 += selected.get(i).getState() + " ";
        }
        
        if (!states5.equals("Connecticut Florida Wisconsin Minnesota New York ")) {
            fail("Test2 failed");
        }               
        
        // Test getAllStates() method returns all states
        List<String> output = backend.getAllStates();
        List<String> check = new ArrayList<String>();
        check.add("Connecticut");
        check.add("Florida");
        check.add("Wisconsin");
        check.add("Minnesota");
        check.add("New York");
        for (int i = 0; i < check.size(); ++i) {
            if (!check.get(i).equals(output.get(i))) {
                fail("Test2 failed");
            }
        }        
        
        // Test 6: Set new date and no states should be selected
        backend.chooseDate("3/20");
        String states6 = "";
        selected.clear();
        selected = backend.getSelectedStates();
        for (int i = 0; i < selected.size(); ++i) {
            states6 += selected.get(i).getState() + " ";
        }
        
        if (!states6.equals("")) {
            fail("Test2 failed");
        }               

    }
    
    /*
     * This method tests the remove method and get selected states
     * 
     * @return boolean
     */
    @Test
    public static void test3() {
        Backend backend = new Backend("file");
        List<StateInterface> selected = new ArrayList<StateInterface>();
        backend.chooseDate("3/19");       
        backend.addState("Connecticut");
        backend.addState("Florida");
        backend.addState("New York");
        backend.addState("Minnesota");
        backend.addState("Wisconsin");
        
        // Test 1: Remove a state 
        backend.removeState("Connecticut");
        String states = "";        
        selected = backend.getSelectedStates();
        for (int i = 0; i < selected.size(); ++i) {
            states += selected.get(i).getState() + " ";
        }
        
        if (!states.equals("Florida Wisconsin Minnesota New York ")) {
            fail("Test3 failed");
        }
        
        // Test 2: Remove another state
        backend.removeState("Florida");
        String states2 = "";     
        selected.clear();
        selected = backend.getSelectedStates();
        for (int i = 0; i < selected.size(); ++i) {
            states2 += selected.get(i).getState() + " ";
        }
        
        if (!states2.equals("Wisconsin Minnesota New York ")) {
            fail("Test3 failed");
        }
        
        // Test 3: Remove a state that is not selected
        backend.removeState("Florida");
        String states3 = "";     
        selected.clear();
        selected = backend.getSelectedStates();
        for (int i = 0; i < selected.size(); ++i) {
            states3 += selected.get(i).getState() + " ";
        }
        
        if (!states3.equals("Wisconsin Minnesota New York ")) {
            fail("Test3 failed");
        }
        
        // Test 4: Remove a state that DNE
        backend.removeState("Whosville");
        String states4 = "";     
        selected.clear();
        selected = backend.getSelectedStates();
        for (int i = 0; i < selected.size(); ++i) {
            states4 += selected.get(i).getState() + " ";
        }
        
        if (!states4.equals("Wisconsin Minnesota New York ")) {
            fail("Test3 failed");
        }
        
        // Test 5: Remove remaining selected states
        backend.removeState("Wisconsin");
        backend.removeState("Minnesota");
        backend.removeState("New York");
        String states5 = "";     
        selected.clear();
        selected = backend.getSelectedStates();
        for (int i = 0; i < selected.size(); ++i) {
            states5 += selected.get(i).getState() + " ";
        }
        
        if (!states5.equals("")) {
            fail("Test3 failed");
        }          

    }
    
    /*
     * This method tests the getDate() method
     * 
     * @return boolean
     */
    @Test
    public static void test4() {
        Backend backend = new Backend("file");
        
        // Test 1: Set date and check accuracy
        backend.chooseDate("3/19");        
        if (!backend.getDate().equals("3/19")) {
            fail("Test4 failed");
        }
        
        // Test 2: Choose new date and check accuracy
        backend.chooseDate("3/20");        
        if (!backend.getDate().equals("3/20")) {
            fail("Test4 failed");
        }

    }
    
    /*
     * This method tests the getAllStates() method
     * 
     * @return boolean
     */
    @Test
    public static void test5() {
        Backend backend = new Backend("file");
        List<String> states = new ArrayList<String>();
        
        // Test 1: Get all states
        backend.chooseDate("3/19");  
        states = backend.getAllStates();
        String output = "";
        for (int i = 0; i < states.size(); ++i) {
            output += states.get(i) + " ";
        }
        
        if (!output.equals("Connecticut Florida Wisconsin Minnesota New York ")) {
            fail("Test5 failed");
        }
        
        // Test 2: Change date and get all states
        states.clear();
        backend.chooseDate("3/20");  
        states = backend.getAllStates();
        String output2 = "";
        for (int i = 0; i < states.size(); ++i) {
            output2 += states.get(i) + " ";
        }
        
        if (!output2.equals("Vermont Illinois Massachusetts Texas Arizona ")) {
            fail("Test5 failed");
        }

    }
    
}
