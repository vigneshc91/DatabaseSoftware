package database;

public class Validator {

	
	private static final String PINCODE_VALIDATOR = "\\d{6}";
	private static final String STRING_VALIDATOR = "\\w+";
	
	
	
	public boolean IsVaidPinCode(String pinCode){
		return pinCode.matches(PINCODE_VALIDATOR);
	}
	
	public boolean IsValidString(String inputString){
		return inputString.matches(STRING_VALIDATOR);
	}
}
