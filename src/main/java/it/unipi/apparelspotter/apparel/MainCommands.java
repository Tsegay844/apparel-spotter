package it.unipi.apparelspotter.apparel;

import it.unipi.apparelspotter.apparel.Service.CustomerService;
import it.unipi.apparelspotter.apparel.commands.Auth;
import it.unipi.apparelspotter.apparel.commands.customer.CustomerPage;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.Scanner;

@SpringBootApplication
public class MainCommands {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(MainCommands.class, args);
        CustomerService customerService = context.getBean(CustomerService.class);
        CustomerPage customerPage = context.getBean(CustomerPage.class);
        Auth auth = context.getBean(Auth.class);
        System.out.println("\u001B[0m********************************************\u001B[0m");
        System.out.println("  \u001B[0mApplication started successfully.\u001B[0m");
        System.out.println("\u001B[0m********************************************\u001B[0m");
        System.out.println("\u001B[0m*                                          *\u001B[0m");
        System.out.println("*      \u001B[1;32mWelCome to ApparelSpotter\u001B[0m           *");
        System.out.println("\u001B[0m*                                          *\u001B[0m");
        System.out.println("\u001B[0m********************************************\u001B[0m");
        // Perform the Auth action
        auth.performAction();
        Scanner scanner = new Scanner(System.in);
        int command;
        // Close the scanner
        scanner.close();
        // Close the Spring application context
        context.close();
    }
}