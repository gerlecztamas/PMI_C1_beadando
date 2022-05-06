package beadando;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;



public class Main {
    public static Scanner sc = new Scanner(System.in);
    public static void main(String[] args){
        ArrayList<Good> goods = xmlReader.readGoodsFromXml("src/main/resources/goods.xml");
        menu(goods);

    }



    public static void menu(ArrayList<Good> goods){


        int choice = -1;
        while (choice != 0) {
            switch (choice) {
                case 1 -> listProducts(goods);
                case 2 -> addNewProduct(goods);
                case 3 -> modifyProduct(goods);
                case 4 -> deleteProduct(goods);
            }
            System.out.println("1 - List products\r\n2 - Add new product\r\n3 - Modify a product\r\n4 - Delete a product\r\n0 - Exit");
            try {
                choice = sc.nextInt();
                sc.nextLine();
                if (choice < 0 || choice > 4) {
                    System.out.println("Please enter a number between 0 and 4!");
                }
            } catch (InputMismatchException ex) {
                System.out.println("Please enter a number between 0 and 4!");
                sc.nextLine();
            }

        }
        xmlSaver.saveUsersToXml(goods,"src/main/resources/goods.xml" );

    }



    private static void listProducts(ArrayList<Good> goods){
        for(Good good : goods){
                System.out.println(good.getName()+"-"+good.getPrice()+"-"+good.getId()+"-"+good.getCategory());
        }
    }



    private static void addNewProduct(ArrayList<Good> goods){
        String name = inputName(goods);
        int price = inputPrice();
        int id = inputId(goods);
        System.out.println("Enter category of new product: ");
        String category = sc.nextLine();
        goods.add(new Good(name,price,id,category));
        System.out.println("Product is successfully registered!");
    }



    private static void modifyProduct(ArrayList<Good> goods){
        System.out.println("Enter name of product you'd like to modify");
        String name = sc.nextLine();
        for(Good good : goods){
            if(good.getName().equals(name)){
                int price = inputPrice();
                int id = inputId(goods);
                System.out.println("Enter category of new product: ");
                String category = sc.nextLine();
                goods.set(goods.indexOf(good), new Good(name, price, id, category));
                System.out.println("Product is successfully modified!");
                return;
            }
        }
        System.out.println("There is no product with this name!");
    }



    private static void deleteProduct(ArrayList<Good> goods){
        System.out.print("Enter name of product to delete! ");
        String name = sc.nextLine();
        for (Good good : goods) {
            if (good.getName().equals(name)) {
                goods.remove(good);
                System.out.println("Product is successfully deleted.");
                return;
            }
        }
        System.out.println("There is no product with this name.");
    }




    private static int inputPrice(){
        int price=0;
        while(true){
            try{
                System.out.println("Enter price of new product: ");
                price = sc.nextInt();
                sc.nextLine();
                break;
            }
            catch(InputMismatchException ex){
                System.out.println("Please enter a number!");
                sc.nextLine();
            }
        }
        return price;
    }
    private static int inputId(ArrayList<Good> goods){
        int id=0;
        while(true){
            try{
                System.out.println("Enter id of new product: ");
                id = sc.nextInt();
                int length = String.valueOf(id).length();
                if(length!=9){
                    throw new WrongLength();
                }
                for(Good good : goods){
                    if(good.getId()==id){
                        throw new idAlreadyTaken();
                    }
                }
                sc.nextLine();
                break;
            }
            catch(InputMismatchException ex){
                System.out.println("Please enter a number!");
                sc.nextLine();
            }
            catch(WrongLength ex){
                System.out.println("The id has to consist of 9 digits!");
                sc.nextLine();
            }
            catch(idAlreadyTaken ex){
                System.out.println("The id is already taken!");
                sc.nextLine();
            }
        }
        return id;
    }
    private static String inputName(ArrayList<Good> goods){
        String name="";
        while(true){
            try{
                System.out.println("Enter name of new product: ");
                name=sc.nextLine();
                for(Good good : goods){
                    if(good.getName().equals(name)){
                        throw new nameAlreadyTaken();
                    }
                }
                break;
            }
            catch(nameAlreadyTaken ex){
                System.out.println("Name is already taken! Please choose a different one!");
            }
        }
        return name;
    }

}
class WrongLength extends Exception{}
class idAlreadyTaken extends Exception{}
class nameAlreadyTaken extends Exception{}


