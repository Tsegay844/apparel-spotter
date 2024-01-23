package it.unipi.apparelspotter.apparel;

import it.unipi.apparelspotter.apparel.Service.AuthService;
import it.unipi.apparelspotter.apparel.Service.CustomerService;
import it.unipi.apparelspotter.apparel.commands.Auth;
import it.unipi.apparelspotter.apparel.commands.CustomerPage;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.Scanner;

@SpringBootApplication // This enables auto-configuration and component scanning
public class MainCommands {

    public static void main(String[] args) {
        // Start the Spring application context
        ConfigurableApplicationContext context = SpringApplication.run(MainCommands.class, args);

        // Retrieve the CustomerService bean from the application context
        CustomerService customerService = context.getBean(CustomerService.class);
        // Retrieve the CustomerPage bean from the application context
        CustomerPage customerPage = context.getBean(CustomerPage.class);
        // Retrieve the Auth bean from the application context
        Auth auth = context.getBean(Auth.class);

        // Output a success message
        System.out.println("Application started successfully.");
        // Perform the Auth action
        auth.performAction();

        // Initialize a scanner for user input (unused in the snippet shown)
        Scanner scanner = new Scanner(System.in);
        // Placeholder for command handling (unused in the snippet shown)
        int command;

        // Close the scanner
        scanner.close();
        // Close the Spring application context
        context.close();
    }
}