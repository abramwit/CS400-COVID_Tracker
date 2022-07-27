
public class State implements StateInterface{
    
    private String state;
    private String date;
    private String totalVac;
    private String peopleVac;
    private String peopleFullyVac;
    private String vacDist;
    private String dailyVac;
    
    public State(String s, String d, String tV, String vD, String pV, String pFV, String dV){
        this.state = s;
        this.date = d;
        this.totalVac = tV;
        this.peopleVac = pV;
        this.peopleFullyVac = pFV;
        this.vacDist = vD;
        this.dailyVac = dV;
        
    }
    
    public String getState() {
        return state;
    }


    public String getDate() {
        return date;
    }


    public String getTotalVaccinations() {
        return totalVac;
    }


    public String getPeopleVaccinated() {
        return peopleVac;
    }

 
    public String getPeopleFullyVaccinated() {
        return peopleFullyVac;
    }

    @Override
    public String getVaccinesDistributed() {
        return vacDist;
    }

    
    public String getDailyVaccinations() {
        return dailyVac;
    }


    public int compareTo(StateInterface otherState) {
        return this.getState().compareTo(otherState.getState());
    }
    public String toString() {
        String state = "State: " + this.getState();
        String date = "Date: " + this.getDate();
        String totalVaccinations = "Total Vaccinations: " + this.getTotalVaccinations();
        String peopleVaccinated = "People Vaccinated: " + this.getPeopleVaccinated();
        String peopleFullyVaccinated = "People Fully Vaccinated: " + this.getPeopleFullyVaccinated();
        String vaccinesDistributed = "Vaccines Distributed: " + this.getVaccinesDistributed();
        String dailyVaccinations = "Daily Vaccinations: " + this.getDailyVaccinations();
        return state + "\n" + totalVaccinations + "\n" + vaccinesDistributed + "\n" + peopleVaccinated + "\n" + peopleFullyVaccinated + "\n" + dailyVaccinations + "\n";
    }
}
