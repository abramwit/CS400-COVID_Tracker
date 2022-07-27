
public interface StateInterface extends Comparable<StateInterface>{

    public String getState();
    public String getDate();
    public String getTotalVaccinations();
    public String getPeopleVaccinated();
    public String getPeopleFullyVaccinated();
    public String getVaccinesDistributed();
    public String getDailyVaccinations();
    
    public int compareTo(StateInterface otherState);
    
}