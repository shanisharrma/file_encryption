package views;

import dao.DataDAO;
import model.Data;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class UserView {
    final private String email;

    public UserView(String email) {
        this.email = email;
    }

    public void home() {
        do {
            System.out.println("Welcome " + this.email);
            System.out.println("Press 1 to show Hidden files");
            System.out.println("Press 2 to hide a new file");
            System.out.println("Press 3 to unhide a file");
            System.out.println("Press 0 to exit");
            Scanner sc = new Scanner(System.in);
            int ch = Integer.parseInt(sc.nextLine());
            switch (ch) {
                case 1 -> {
                    try {
                        List<Data> files = DataDAO.getAllFiles(this.email);
                        System.out.println("ID\t - File Name");
                        for (Data file: files) {
                            System.out.println(file.getId() + "\t - " + file.getFileName());
                        }
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                }
                case 2 -> {
                    System.out.println("Enter the file path: ");
                    String path = sc.nextLine();
                    File f = new File(path);
                    Data file = new Data(0, f.getName(), f.getPath(),this.email);
                    try {
                        DataDAO.hideFiles(file);
                    } catch (SQLException | IOException e) {
                        throw new RuntimeException(e);
                    }
                }
                case 3 -> {
                    try {
                        List<Data> files = DataDAO.getAllFiles(this.email);
                        System.out.println("ID\t - File Name");
                        for (Data file: files) {
                            System.out.println(file.getId() + "\t - " + file.getFileName());
                        }

                        System.out.println("Enter file ID to unhide: ");
                        int id = Integer.parseInt(sc.nextLine());
                        boolean isValidID = false;
                        for (Data file: files) {
                            if (file.getId() == id) {
                                isValidID = true;
                                break;
                            }
                        }
                        if (isValidID) {
                            DataDAO.unhideFile(id);
                        } else {
                            System.out.println("Wrong ID");
                        }

                    } catch (SQLException | IOException | SecurityException e) {
                        throw new RuntimeException(e);
                    }
                }
                case 0 -> System.exit(0);
            }
        } while (true);
    }

}
