package beadando;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Methods {

    public static Scanner sc = new Scanner(System.in);

    protected static void listProducts(ArrayList<Good> goods){
        for(Good good : goods){
            System.out.println(good.getName()+"-"+good.getPrice()+"-"+good.getId()+"-"+good.getCategory());
        }
    }



    protected static void addNewProduct(ArrayList<Good> goods){
        String name = inputName(goods);
        int price = inputPrice();
        int id = inputId(goods);
        System.out.println("Enter category of new product: ");
        String category = sc.nextLine();
        goods.add(new Good(name,price,id,category));
        System.out.println("Product is successfully registered!");
    }



    protected static void modifyProduct(ArrayList<Good> goods){
        System.out.println("Enter name of product you'd like to modify");
        String name = sc.nextLine();
        for(Good good : goods){
            if(good.getName().equals(name)){

                int choice = -1;
                while(choice!=0){
                    switch(choice){
                        case 1 ->{
                            name = inputName(goods);
                            goods.set(goods.indexOf(good), new Good(name, good.getPrice(), good.getId(), good.getCategory()));
                            System.out.println("Product is successfully modified!");}
                        case 2 -> {
                            int price = inputPrice();
                            goods.set(goods.indexOf(good), new Good(good.getName(),price,good.getId(), good.getCategory()) );
                            System.out.println("Product is successfully modified!");}
                        case 3 -> {
                            int id = inputId(goods);
                            goods.set(goods.indexOf(good), new Good(good.getName(), good.getPrice(), id, good.getCategory()) );
                            System.out.println("Product is successfully modified!");}
                        case 4 -> {
                            System.out.println("Enter category of product: ");
                            String category = sc.nextLine();;
                            goods.set(goods.indexOf(good), new Good(good.getName(), good.getPrice(), good.getId(), category) );
                            System.out.println("Product is successfully modified!");}
                    }
                    System.out.println("What would you like to modify?\r\n1 - Name\r\n2 - Price\r\n3 - Id\r\n4 - Category\r\n");
                    System.out.println("0 - Cancel");

                    try {
                        choice = sc.nextInt();
                        sc.nextLine();
                        if (choice < 0 || choice > 4) {
                            System.err.println("Please enter a number between 0 and 4!");
                        }
                    } catch (InputMismatchException ex) {
                        System.err.println("Please enter a number between 0 and 4!");
                        sc.nextLine();
                    }
                }
                return;
            }
        }
        System.err.println("There is no product with this name!");
    }




    protected static void deleteProduct(ArrayList<Good> goods){
        System.out.print("Enter name of product to delete! ");
        String name = sc.nextLine();
        for (Good good : goods) {
            if (good.getName().equals(name)) {
                goods.remove(good);
                System.out.println("Product is successfully deleted.");
                return;
            }
        }
        System.err.println("There is no product with this name.");
    }


    protected static void dataOfProduct(ArrayList<Good> goods){
        System.out.println("Enter name of product!");
        String name=sc.nextLine();
        for (Good good : goods) {
            if (good.getName().equals(name)) {
                System.out.println("Name: "+good.getName()+"\r\nPrice: "+good.getPrice()+"\r\nId: "+good.getId()+"\r\nCategory: "+good.getCategory());
                return;
            }
        }
        System.err.println("There is no product with this name.");
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
                System.err.println("Please enter a number!");
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
                        throw new AlreadyTaken();
                    }
                }
                sc.nextLine();
                break;
            }
            catch(InputMismatchException ex){
                System.err.println("Please enter a number!");
                sc.nextLine();
            }
            catch(WrongLength ex){
                System.err.println("The id has to consist of 9 digits!");
                sc.nextLine();
            }
            catch(AlreadyTaken ex){
                System.err.println("The id is already taken!");
                sc.nextLine();
            }
        }
        return id;
    }
    private static String inputName(ArrayList<Good> goods){
        String name="";
        while(true){
            try{
                System.out.println("Enter name of product: ");
                name=sc.nextLine();
                for(Good good : goods){
                    if(good.getName().equals(name)){
                        throw new AlreadyTaken();
                    }
                }
                break;
            }
            catch(AlreadyTaken ex){
                System.err.println("Name is already taken! Please choose a different one!");
            }
        }
        return name;
    }


}
