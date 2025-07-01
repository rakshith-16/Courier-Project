package com.chronos;

import com.chronos.cli.CommandProcessor;
import com.chronos.service.DispatchCenter;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Welcome to Chronos Couriers CLI System");
        System.out.println("Type a command (e.g., PLACE_ORDER ...) or EXIT to quit.\n");
        DispatchCenter dispatchCenter = new DispatchCenter();
        CommandProcessor processor = new CommandProcessor(dispatchCenter);
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print(">> ");
            String input = scanner.nextLine();
            if (!input.trim().isEmpty()) processor.process(input);
        }
    }
}
