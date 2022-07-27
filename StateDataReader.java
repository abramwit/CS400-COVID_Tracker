
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.zip.DataFormatException;

public class StateDataReader{

    public static ArrayList<StateInterface> readStateData(FileReader inputFileReader, String specDate)
        throws IOException, DataFormatException {
        Scanner scan;
        try{
            scan = new Scanner(inputFileReader);
        }
        catch(Exception e) {
            throw new IOException();
        }
        ArrayList<StateInterface> stateList = new ArrayList<StateInterface>();
        int commaCount = 0;
        char comma = ',';
        String temp;
        char[] tempchar;
        String state;
        String date;
        String totalVac;
        String peopleVac;
        String peopleFullyVac;
        String vacDist;
        String dailyVac;
        scan.nextLine();
        while(scan.hasNext()) {
            
            date = "";
            state = "";
            totalVac = "";
            vacDist = "";
            peopleVac = "";
            peopleFullyVac = "";
            dailyVac = "";
            
            temp = scan.nextLine();
            tempchar = temp.toCharArray();
            for(char x: tempchar) {
                if(x==comma) {
                    commaCount++;
                    continue;
                }
                if(commaCount == 0)
                    date+=x;
                if(commaCount == 1)
                    state+=x;
                if(commaCount == 2)
                    totalVac+=x;
                if(commaCount == 3)
                    vacDist+=x;
                if(commaCount == 4) 
                    peopleVac+=x;
                if(commaCount == 5)
                    peopleFullyVac+=x;
                if(commaCount == 6)
                    dailyVac+=x;
                if(commaCount == 7)
                    throw new DataFormatException();
            }
            
            if(totalVac == "")
                totalVac = "N/A";
            if(vacDist == "")
                vacDist = "N/A";
            if(peopleVac == "")
                peopleVac = "N/A";
            if(peopleFullyVac == "")
                peopleFullyVac = "N/A";
            if(dailyVac == "")
                dailyVac = "N/A";
            
            if(date.equals(specDate))
                stateList.add(new State(state, date, totalVac, vacDist, peopleVac, peopleFullyVac, dailyVac));
            commaCount = 0;
        
        
        }
        scan.close();
        return stateList;
    }

}

