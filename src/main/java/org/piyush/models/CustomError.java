package org.piyush.models;

public class CustomError {
	private String cause;

	public CustomError(String cause) {
		super();
		this.cause = cause;
	}

	public String getCause() {
		return cause;
	}

	public void setCause(String cause) {
		this.cause = cause;
	}

	@Override
	public String toString() {
		return "CustomError [cause=" + cause + "]";
	}

}
