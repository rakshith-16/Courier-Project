package com.chronos.model;

public class Rider {

	private String id;
	private String name;
	private String currentPackageId;
	private String currentLocation;
	private double reliabilityScore;
	private Status status;

	public Rider(String id, String name, double score, String location) {
		this.id = id;
		this.name = name;
		this.reliabilityScore = score;
		this.currentLocation = location;
		this.status = Status.AVAILABLE;
	}

	public String getId() {
		return id;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	
	public void setCurrentPackageId(String id) {
		this.currentPackageId = id;
	}

	public String getCurrentPackageId() {
		return currentPackageId;
	}

	public String toString() {
		return "Rider{id='" + id + "', name='" + name + ", score=" + reliabilityScore
				+ ", status=" + status + "}";
	}

}
