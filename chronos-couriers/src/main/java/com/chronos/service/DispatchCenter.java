package com.chronos.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.PriorityQueue;

import com.chronos.model.DeliveryPackage;
import com.chronos.model.PackageLogEntry;
import com.chronos.model.Priority;
import com.chronos.model.Rider;
import com.chronos.model.Status;


public class DispatchCenter {

	private Map<String, DeliveryPackage> packages = new HashMap<>();
	private Map<String, Rider> riders = new HashMap<>();
	private List<PackageLogEntry> logs = new ArrayList<>();
	private PriorityQueue<DeliveryPackage> dispatchQueue = new PriorityQueue<>(new PackageComparator());

	public void placeOrder(String id, String pickup, String drop, String priorityStr,
			LocalDateTime deadline) {
		if (packages.containsKey(id)) {
			System.out.println("Package already exists: " + id);
			return;
		}
		Priority priority = Priority.valueOf(priorityStr.toUpperCase());
		DeliveryPackage dp = new DeliveryPackage(id, pickup, drop, priority, deadline, LocalDateTime.now());
		packages.put(id, dp);
		dispatchQueue.offer(dp);
		System.out.println("Package placed: " + id);
		assignPackages();
	}

	public void addRider(String id, String name, double score, String location) {
		if (riders.containsKey(id)) {
			System.out.println("Rider already exists: " + id);
			return;
		}
		Rider rider = new Rider(id, name, score, location);
		riders.put(id, rider);
		System.out.println(" Rider added: " + id);
		assignPackages();
	}

	public void completeDelivery(String packageId) {
		DeliveryPackage dp = packages.get(packageId);
		if (dp == null || dp.getStatus() != Status.ASSIGNED) {
			System.out.println("Invalid package state.");
			return;
		}
		Rider rider = riders.get(dp.getAssignedRiderId());
		dp.setStatus(Status.DELIVERED);
		dp.setDeliveryTime(LocalDateTime.now());
		if (rider != null) {
			rider.setStatus(Status.AVAILABLE);
			rider.setCurrentPackageId(null);
		}
		logs.add(new PackageLogEntry(packageId, rider.getId(), dp.getPickupTime(), dp.getDeliveryTime(),
				dp.getDeliveryTime().isAfter(dp.getDeliveryDeadline()), dp.getPriority()));
		System.out.println(" Package delivered: " + packageId);
		assignPackages();
	}

	public DeliveryPackage getPackage(String id) {
		return packages.get(id);
	}

	public Rider getRider(String id) {
		return riders.get(id);
	}

	private void assignPackages() {
		List<DeliveryPackage> toRemove = new ArrayList<>();
		for (DeliveryPackage dp : dispatchQueue) {
			Optional<Rider> match = riders.values().stream().filter(r -> r.getStatus() == Status.AVAILABLE).findFirst();
			if (match.isPresent()) {
				Rider rider = match.get();
				dp.setStatus(Status.ASSIGNED);
				dp.setAssignedRiderId(rider.getId());
				dp.setPickupTime(LocalDateTime.now());
				rider.setStatus(Status.BUSY);
				rider.setCurrentPackageId(dp.getId());
				System.out.println(" Package " + dp.getId() + " assigned to Rider " + rider.getId());
				toRemove.add(dp);
			}
		}
		dispatchQueue.removeAll(toRemove);
	}

	private static class PackageComparator implements Comparator<DeliveryPackage> {
		public int compare(DeliveryPackage a, DeliveryPackage b) {
			int cmp = b.getPriority().compareTo(a.getPriority());
			if (cmp != 0)
				return cmp;
			cmp = a.getDeliveryDeadline().compareTo(b.getDeliveryDeadline());
			return cmp != 0 ? cmp : a.getCreatedAt().compareTo(b.getCreatedAt());
		}
	}

}
