package core.distribution;

public class ConsultationReqProbability {
	

	public String getRequest() {
		String result =null;
		double r = Math.random();
		if (r<0.05){
			result ="MRI";
		}
		if(0.05<=r && r<0.40){
			result="NO TEST REQUIRED";
		}
		if (0.40<=r && r<0.80){
			result="BLOOD-TEST";
		}
		if (0.80<=r && r<1){
			result="RADIOGRAPHY";	
		}
		return result;
	}
}
