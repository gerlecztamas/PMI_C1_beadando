package beadando;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;



public class Main {
    public static Scanner sc = new Scanner(System.in);

    public static void main(String[] args){
        ArrayList<Good> goods = xmlReader.readGoodsFromXml("src/main/resources/goods.xml");
        int choice = -1;
        while(choice!=0){
            switch(choice){
                case 1 -> adminmenu(goods);
                case 2 -> customermenu(goods);
            }
            System.out.println("\r\n1 - I'm an employee\r\n2 - I'm a customer\r\n");
            System.out.println("0 - Exit");
            try {
                choice = sc.nextInt();
                sc.nextLine();
                if (choice < 0 || choice > 2) {
                    System.err.println("Please enter a number between 0 and 2!");
                }
            } catch (InputMismatchException ex) {
                System.err.println("Please enter a number between 0 and 2!");
                sc.nextLine();
            }
        }


    }
    public static void customermenu(ArrayList<Good> goods){
        ArrayList<Good> cart = new ArrayList<Good>();
        int choice = -1;
        while (choice != 0) {
            switch (choice) {
                case 1 -> Methods.listProducts(goods);
                case 2 -> Methods.addToCart(goods,cart);
                case 3 -> Methods.removeFromCart(cart);
                case 4 -> Methods.listCart(cart);
                case 5 -> Methods.pay(cart);
            }
            System.out.println("\r\n1 - List products\r\n2 - Add to cart\r\n3 - Remove from cart\r\n4 - What's in my cart?\r\n5 - Pay\r\n");
            System.out.println("0 - Exit");
            try {
                choice = sc.nextInt();
                sc.nextLine();
                if (choice < 0 || choice > 5) {
                    System.err.println("Please enter a number between 0 and 5!");
                }
            } catch (InputMismatchException ex) {
                System.err.println("Please enter a number between 0 and 5!");
                sc.nextLine();
            }

        }
    }

    public static void adminmenu(ArrayList<Good> goods){
        System.out.println("Enter username!");
        String uname = sc.nextLine();
        if(uname.isEmpty() || !uname.equals("dearadmin")){
            System.err.println("Wrong username!");
            return;
        }
        System.out.println("Enter password");
        String password = sc.nextLine();
        if(password.isEmpty() || !password.equals("6vasNzQ3569z")){
            System.err.println("Wrong password!");
            return;
        }


        int choice = -1;
        while (choice != 0) {
            switch (choice) {
                case 1 -> Methods.listProducts(goods);
                case 2 -> Methods.addNewProduct(goods);
                case 3 -> Methods.modifyProduct(goods);
                case 4 -> Methods.deleteProduct(goods);
                case 5 -> Methods.dataOfProduct(goods);
            }
            System.out.println("\r\n1 - List products\r\n2 - Add new product\r\n3 - Modify a product\r\n4 - Delete a product\r\n5 - Get data of a certain product\r\n");
            System.out.println("0 - Exit");
            try {
                choice = sc.nextInt();
                sc.nextLine();
                if (choice < 0 || choice > 5) {
                    System.err.println("Please enter a number between 0 and 5!");
                }
            } catch (InputMismatchException ex) {
                System.err.println("Please enter a number between 0 and 5!");
                sc.nextLine();
            }

        }
        xmlSaver.saveUsersToXml(goods,"src/main/resources/goods.xml" );

    }

}
class WrongLength extends Exception{}
class AlreadyTaken extends Exception{}
class IsEmpty extends Exception{}



