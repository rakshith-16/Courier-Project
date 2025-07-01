package com.chronos.model;

import java.time.LocalDateTime;


public class DeliveryPackage {

	private String id;
	private String pickupLocation;
	private String deliveryLocation;
	private String assignedRiderId;
	private Priority priority;
	private Status status;
	private LocalDateTime createdAt;
	private LocalDateTime deliveryDeadline;
	private LocalDateTime pickupTime;
	private LocalDateTime deliveryTime;

	public DeliveryPackage(String id, String pickupLocation, String deliveryLocation, Priority priority,
			LocalDateTime deadline, LocalDateTime createdAt) {
		this.id = id;
		this.pickupLocation = pickupLocation;
		this.deliveryLocation = deliveryLocation;
		this.priority = priority;
		this.deliveryDeadline = deadline;
		this.createdAt = createdAt;
		this.status = Status.PENDING;
	}

	public String getId() {
		return id;
	}

	public Priority getPriority() {
		return priority;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public LocalDateTime getDeliveryDeadline() {
		return deliveryDeadline;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public String getAssignedRiderId() {
		return assignedRiderId;
	}

	public void setAssignedRiderId(String id) {
		this.assignedRiderId = id;
	}

	public void setPickupTime(LocalDateTime time) {
		this.pickupTime = time;
	}

	public void setDeliveryTime(LocalDateTime time) {
		this.deliveryTime = time;
	}

	public LocalDateTime getPickupTime() {
		return pickupTime;
	}

	public LocalDateTime getDeliveryTime() {
		return deliveryTime;
	}

	public String toString() {
		return "DeliveryPackage{id='" + id + "', priority=" + priority + ", status=" + status + ", deadline="
				+ deliveryDeadline + "}";
	}
}
