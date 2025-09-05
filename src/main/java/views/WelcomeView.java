package views;

import dao.UserDAO;
import model.User;
import service.GenerateOTP;
import service.SendOTPService;
import service.UserService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.Scanner;

public class WelcomeView {
    public void welcomeScreen() {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Welcome to the app");
        System.out.println("Press 1 to login");
        System.out.println("Press 2 from signup");
        System.out.println("Press 0 to exit");
        int choice = 0;
        try {
            choice = Integer.parseInt(br.readLine());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        
        switch(choice) {
            case 1 -> login();
            case 2 -> signUp();
            case 0 -> System.exit(0);
        }
    }

    private void signUp() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter name: ");
        String name = sc.nextLine();
        System.out.println("Enter email: ");
        String email = sc.nextLine();

        String genOTP = GenerateOTP.getOTP();
        SendOTPService.sendOTP(email, genOTP);
        System.out.println("Enter the otp");
        String otp = sc.nextLine();
        if (otp.equals(genOTP)) {
            User user = new User(name, email);
            int res = UserService.saveUser(user);
            switch (res) {
                case 0 -> System.out.println("User already exists.");
                case 1 -> System.out.println("User registered.");
            }
        } else {
            System.out.println("Wrong OTP");
        }

    }

    private void login() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter your email: ");
        String email = sc.nextLine();
        try {
            if (UserDAO.isExists(email)) {
                String genOTP = GenerateOTP.getOTP();
                SendOTPService.sendOTP(email, genOTP);
                System.out.println("Enter the otp");
                String otp = sc.nextLine();
                if (otp.equals(genOTP)) {
                    new UserView(email).home();

                } else {
                    System.out.println("Wrong OTP");
                }
            } else {
                System.out.println("User Not Found");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
