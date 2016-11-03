package wpl.apartmentportal.bean;

public class MaintenanceBean {
	String userName;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	private String maintenanceType;
	private String maintenanceDesc;

	public String getMaintenanceType() {
		return maintenanceType;
	}

	public void setMaintenanceType(String maintenanceType) {
		this.maintenanceType = maintenanceType;
	}

	public String getMaintenanceDesc() {
		return maintenanceDesc;
	}

	public void setMaintenanceDesc(String maintenanceDesc) {
		this.maintenanceDesc = maintenanceDesc;
	}
}
