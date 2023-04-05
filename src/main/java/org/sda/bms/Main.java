package org.sda.bms;

import org.sda.bms.controller.UserOption;
import org.sda.bms.utils.SessionManager;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        startHibernate();

        // dependencies
        Scanner scanner = new Scanner(System.in);

        UserOption userOption = UserOption.UNKNOWN;
        while (userOption != UserOption.EXIT) {
            UserOption.printAllOptions();
            System.out.println(" ");
            System.out.println("Please Select An Option : ");
            try {
                int selectedOption = Integer.parseInt(scanner.next().trim());
                userOption = UserOption.findUserOption(selectedOption).orElse(UserOption.UNKNOWN);
            } catch (NumberFormatException e) {
                userOption = UserOption.UNKNOWN;
            }

            switch (userOption) {
                case CREATE_AUTHOR:
                    System.out.println("Not Implemented");
                    break;
                case UPDATE_AUTHOR:
                    System.out.println("Not Implemented");
                    break;
                case DELETE_AUTHOR:
                    System.out.println("Not Implemented");
                    break;
                case UNKNOWN:
                    System.out.println("Please insert a valid option !!!! ");
                    break;
                case EXIT:
                    stopHibernate();
                    System.out.println("Good bye!");
                    break;
                default:
                    System.out.println("Not implemented. Please contact your Administrator");
                    break;
            }
        }
    }


    private static void startHibernate() {
        SessionManager.getSessionFactory();
        for (int i = 0; i <= 50; i++) {
            System.out.println(" ");
        }
    }

    private static void stopHibernate() {
        SessionManager.shutDown();
        for (int i = 0; i <= 50; i++) {
            System.out.println(" ");
        }
    }

}