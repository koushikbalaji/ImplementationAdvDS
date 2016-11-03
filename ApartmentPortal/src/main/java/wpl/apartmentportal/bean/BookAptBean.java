package wpl.apartmentportal.bean;

import java.sql.Date;

public class BookAptBean {
		String email;
		int aptNumber;
		Date moveInDate;
		
		public String getEmail() {
			return email;
		}
		public void setEmail(String email) {
			this.email = email;
		}
		public int getAptNumber() {
			return aptNumber;
		}
		public void setAptNumber(int aptNumber) {
			this.aptNumber = aptNumber;
		}
		public Date getMoveInDate() {
			return moveInDate;
		}
		public void setMoveInDate(Date moveInDate) {
			this.moveInDate = moveInDate;
		}
}
