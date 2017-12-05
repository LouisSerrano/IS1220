package Core.Distribution;

import Event.Event;
import java.util.HashMap;
import java.util.Map;

import Core.Patient;
import Core.Time;

public class ConsultationReqProbability {
	boolean bloodTestTaken;
	boolean mriTaken;
	boolean xrayTaken;

	public ConsultationReqProbability(){
		this.bloodTestTaken=false;
		this.mriTaken=false;
		this.xrayTaken=false;
	}
	
	
	public String getRequest() {
		String result =null;
		double r = Math.random();
		
		if(bloodTestTaken == true){
			if (r<0.05){
				result ="MRI";
			}
			if(0.05<=r && r<0.80){
				result="NO_TEST_REQUIRED";
			}
			if (0.80<=r && r<1){
				result="X_RAY";	
			}
		}
		if(mriTaken == true){
			
			if(0.00<=r && r<0.40){
				result="NO_TEST_REQUIRED";
			}
			if (0.40<=r && r<0.80){
				result="BLOOD_TEST";
			}
			if (0.80<=r && r<1){
				result="X_RAY";	
			}
		}
		if (xrayTaken==true){
			if (r<0.05){
				result ="MRI";
			}
			if(0.05<=r && r<0.60){
				result="NO_TEST_REQUIRED";
			}
			if (0.60<=r && r<1.00){
				result="BLOOD_TEST";
			}
		}
		if (bloodTestTaken==true && mriTaken==true){
			
			if(0.00<=r && r<0.80){
				result="NO_TEST_REQUIRED";
			}
			if (0.80<=r && r<1){
				result="X_RAY";	
			}
		}
			
	    if (bloodTestTaken==true && xrayTaken==true){
	    	if (r<0.05){
				result ="MRI";
			}
			if(0.05<=r && r<1.00){
				result="NO_TEST_REQUIRED";
			}
	    }
	    if (xrayTaken==true && mriTaken == true){
	    	
			if(0.00<=r && r<0.60){
				result="NO_TEST_REQUIRED";
			}
			if (0.60<=r && r<1.0){
				result="BLOOD_TEST";	
			}
	    }
	    
		if (xrayTaken && mriTaken && bloodTestTaken){
			result ="NO_TEST_REQUIRED";	
		}
		
		else {
			if (r<0.05){
				result ="MRI";
			}
			if(0.05<=r && r<0.40){
				result="NO_TEST_REQUIRED";
			}
			if (0.40<=r && r<0.80){
				result="BLOOD_TEST";
			}
			if (0.80<=r && r<1){
				result="X_RAY";	
				}
			}	
		return result;
	}


	public boolean isBloodTestTaken() {
		return bloodTestTaken;
	}


	public boolean isMriTaken() {
		return mriTaken;
	}


	public boolean isXrayTaken() {
		return xrayTaken;
	}


	public void setBloodTestTaken(boolean bloodTestTaken) {
		this.bloodTestTaken = bloodTestTaken;
	}


	public void setMriTaken(boolean mriTaken) {
		this.mriTaken = mriTaken;
	}


	public void setXrayTaken(boolean xrayTaken) {
		this.xrayTaken = xrayTaken;
	}
}
	


