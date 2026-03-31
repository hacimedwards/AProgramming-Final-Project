package model;

public class Role {
	public static final String STUDENT = "student";
	public static final String TECHNICIAN = "technician";
	public static final String ADMIN = "ADMIN";
	
	public static String getRoleString(int num) {
		String res = "";
		if (num == 0) {
			res = ADMIN;
		}else if(num == 1) {
			res = TECHNICIAN;
		}else if(num == 2) {
			res = STUDENT;
		}
		return res;
	}

}
