package org.piyush.models;

public class Success {
	private boolean success;

	public Success(boolean success) {
		super();
		this.success = success;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	@Override
	public String toString() {
		return "Success [success=" + success + "]";
	}
	
}
