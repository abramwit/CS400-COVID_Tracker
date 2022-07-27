// --== CS400 File Header Information ==--
// Name: Aiden Bramwit
// Email: Bramwit@wisc.edu
// Team: BE red
// Role: Backend
// TA: Bri Cochran
// Lecturer: Gary Dahl
// Notes to Grader: <optional extra notes>

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.DataFormatException;

public class StateDataReaderForTest implements StateDataReaderInterface {
    
    /*
     * I changed the name of this class to integrate our code because I could
     * not have two classed with the same name in the same build path
     */
    public StateDataReaderForTest() {
        
    }

    @Override
    public ArrayList<StateInterface> readStateData(FileReader inputFileReader, String date)
        throws IOException, DataFormatException {
        // TODO Auto-generated method stub
        ArrayList<StateInterface> states = new ArrayList<StateInterface>();
        
        if (date.equals("3/19")) {           
            State ct = new State("Connecticut", "", "", "", "", "", "");           
            State fl = new State("Florida", "", "", "", "", "", "");
            State wi = new State("Wisconsin", "", "", "", "", "", "");
            State mi = new State("Minnesota", "", "", "", "", "", "");
            State ny = new State("New York", "", "", "", "", "", "");
            states.add(ct);
            states.add(fl);
            states.add(wi);
            states.add(mi);
            states.add(ny);
        }
        
        if (date.equals("3/20")) {           
            State vt = new State("Vermont", "", "", "", "", "", "");
            State il = new State("Illinois", "", "", "", "", "", "");
            State ma = new State("Massachusetts", "", "", "", "", "", "");
            State tx = new State("Texas", "", "", "", "", "", "");
            State az = new State("Arizona", "", "", "", "", "", "");
            states.add(vt);
            states.add(il);
            states.add(ma);
            states.add(tx);
            states.add(az);
        }
        
        return states;
    }

}
