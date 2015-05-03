package jbehave.runner.entity;

public class Dp {

	private String ip;
	private String name;
	private RegistrationState RegistrationState;
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public RegistrationState getRegistrationState() {
		return RegistrationState;
	}
	public void setRegistrationState(RegistrationState registrationState) {
		RegistrationState = registrationState;
	}
}
