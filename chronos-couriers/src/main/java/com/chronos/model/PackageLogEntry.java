package com.chronos.model;

import java.time.LocalDateTime;

public class PackageLogEntry {

	private String packageId;
	private String riderId;
	private LocalDateTime pickupTime;
	private LocalDateTime deliveryTime;
	private boolean deliveredLate;
	private Priority priority;

	public PackageLogEntry(String packageId, String riderId, LocalDateTime pickupTime, LocalDateTime deliveryTime,
			boolean deliveredLate, Priority priority) {
		this.packageId = packageId;
		this.riderId = riderId;
		this.pickupTime = pickupTime;
		this.deliveryTime = deliveryTime;
		this.deliveredLate = deliveredLate;
		this.priority = priority;
	}

	public String toString() {
		return "Log{package=" + packageId + ", rider=" + riderId + ", pickup=" + pickupTime + ", delivery=" + deliveryTime
				+ ", late=" + deliveredLate + ", priority=" + priority + "}";
	}
}
