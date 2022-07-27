import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.zip.DataFormatException;

public interface StateDataReaderInterface {
    public ArrayList<StateInterface> readStateData(FileReader inputFileReader, String date) 
        throws IOException, DataFormatException;

}
