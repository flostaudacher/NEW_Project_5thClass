
public class frequenzy {
	// Klasse die gebraucht wird um die FrequenzenHashmap zu sortieren um den besten Zeitpunkt zu erhalten
	String time = "";
	int timesMinVal = 0;
	public frequenzy() {
		super();
		// TODO Auto-generated constructor stub
	}
	public frequenzy(String time, int timesMinVal) {
		super();
		this.time = time;
		this.timesMinVal = timesMinVal;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public int getTimesMinVal() {
		return timesMinVal;
	}
	public void setTimesMinVal(int timesMinVal) {
		this.timesMinVal = timesMinVal;
	}
	public int compareTo(frequenzy z) 
	{
	    int res=0;
	    if (timesMinVal < z.timesMinVal) {res = -1;}
	    if (timesMinVal > z.timesMinVal) {res = 1;}
	    return res;
	}
}
