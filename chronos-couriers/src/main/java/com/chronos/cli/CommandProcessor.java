package com.chronos.cli;

import java.time.LocalDateTime;

import com.chronos.model.DeliveryPackage;
import com.chronos.model.Rider;
import com.chronos.service.DispatchCenter;



public class CommandProcessor {
    private DispatchCenter dispatchCenter;

    public CommandProcessor(DispatchCenter dispatchCenter) {
        this.dispatchCenter = dispatchCenter;
    }

    public void process(String input) {
        String[] tokens = input.trim().split("\\s+");
        if (tokens.length == 0)
            return;

        String command = tokens[0].toUpperCase();

        try {
            switch (command) {

                case "PLACE_ORDER":
                    if (tokens.length != 6) {
                        System.out.println("Usage: PLACE_ORDER id pickup drop PRIORITY yyyy-MM-ddTHH:mm");
                        return;
                    }
                    dispatchCenter.placeOrder(tokens[1], tokens[2], tokens[3], tokens[4],
                            LocalDateTime.parse(tokens[5]));
                    break;

                case "ADD_RIDER":
                    if (tokens.length != 5) {
                        System.out.println("Usage: ADD_RIDER id name reliability location");
                        return;
                    }
                    dispatchCenter.addRider(tokens[1], tokens[2],
                            Double.parseDouble(tokens[3]), tokens[4]);
                    break;

                case "COMPLETE_DELIVERY":
                    dispatchCenter.completeDelivery(tokens[1]);
                    break;

                case "VIEW_PACKAGE":
                    DeliveryPackage pkg = dispatchCenter.getPackage(tokens[1]);
                    System.out.println(pkg != null ? pkg : "Package not found");
                    break;

                case "VIEW_RIDER":
                    Rider rider = dispatchCenter.getRider(tokens[1]);
                    System.out.println(rider != null ? rider : "Rider not found");
                    break;

                case "EXIT":
                    System.out.println("Exiting...");
                    System.exit(0);
                    break;

                default:
                    System.out.println("Unknown command.");
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}