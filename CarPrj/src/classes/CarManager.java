package classes;

import classes.Com.Brand;
import classes.Com.BrandList;
import classes.Com.CarList;
import classes.Com.Menu;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CarManager {

    public static void main(String[] args) throws IOException {
        BrandList bl = new BrandList();
        CarList cl = new CarList(bl);

        Scanner sc = new Scanner(System.in);
        ArrayList<String> options = new ArrayList<>(Arrays.asList(
                "List all brands",
                "Add a new brand",
                "Search a brand based on it's ID",
                "Update a brand", "Save brands to the file",
                "List all cars in ascending order of brand names",
                "List cars based on a part of an input brand name",
                "Add a car", "Remove a car based on it's ID",
                "Update a car based on it's ID",
                "Save cars to file",
                "Exit"
        ));
        //System.out.println(options);
        //int N = options.size();

        try {
            bl.loadFromFile("Brands.txt");
        } catch (IOException ex) {
            Logger.getLogger(CarManager.class.getName()).log(Level.SEVERE, null, ex);
            System.err.println("Error");
        }
        try {
            cl.loadFromFile("Cars.txt");
        } catch (IOException ex) {
            Logger.getLogger(CarManager.class.getName()).log(Level.SEVERE, null, ex);
            System.err.println("Error");
        }
        int choice;

        Menu mnu = new Menu();
        do {
            System.out.println("/************** auto management app ******************/");
            choice = mnu.int_getChoice(options);
            switch (choice) {
                case 1:
                    while (true) {
                        bl.listBrands();
                        System.out.println("press enter to continue...");
                        Scanner out = new Scanner(System.in);
                        String take = out.nextLine();
                        //System.out.println(take);
                        if ("".equals(take)) {
                            //Runtime.getRuntime().exec("cls");
                            /*for(int i = 0; i < 8; i++){
                                System.out.println("\n");
                                //System.out.println("vao day roi");
                            }*/
                        }
                        break;
                    }
                    break;
                case 2:
                    //String newBrandID = getNewBrandID();
                    String newBrandID = "";
                    while (true) {
                        System.out.println("Input Brand's ID: ");
                        newBrandID = sc.nextLine().trim();
                        if ("".equals(newBrandID)) {
                            System.out.println("You have not pressed anything");
                        } else if (bl.isNewBrandIDValid(newBrandID)) {
                            break;
                        } else {
                            System.out.println("This ID is exist");
                        }
                    }
                    String newBrandName = "";
                    while (true) {
                        System.out.println("Input Brand's name: ");
                        newBrandName = sc.nextLine().trim();
                        if (bl.isNewBrandNameValid(newBrandName)) {
                            break;
                        } else {
                            System.out.println("This name is invalid");
                        }
                    }
                    String newSoundBrand = "";
                    while (true) {
                        System.out.println("Input Sound's Brand: ");
                        newSoundBrand = sc.nextLine().trim();
                        if (bl.isNewSoundBrandValid(newSoundBrand)) {
                            break;
                        } else {
                            System.out.println("This brand is invalid");
                        }
                    }
                    String newPrice = "";
                    while (true) {
                        System.out.println("Input Price: ");
                        newPrice = sc.nextLine();
                        if (bl.isNewPriceValid(newPrice)) {
                            break;
                        } else {
                            System.out.println("This price is invalid");
                        }
                    }
                    bl.addBrand(newBrandID, newBrandName, newSoundBrand, newPrice);
                    System.out.println("process finished!");
                    break;
                case 3:
                    System.out.println("Input the Id: ");
                    String bID = sc.nextLine().trim();
                    int index = bl.searchID(bID);
                    if (index == -1) {
                        System.out.println("Cannot find!");
                    } else {
                        System.out.format("%-15s%-35s%-20s%-20s", "Brand ID", "Brand name", "Sound Brand", "Price");
                        System.out.println("");
                        System.out.println(bl.getOneBrand(index));
                    }
                    System.out.println("process finished!");
                    break;
                case 4:
                    sc = new Scanner(System.in);
                    System.out.println("Input the Brand's ID:");
                    String brandID = sc.nextLine().trim();
                    int pos = bl.searchID(brandID);
                    if (pos < 0) {
                        System.out.println("Not found");
                    } else {
                        while (true) {
                            System.out.println("Input Brand's name: ");
                            newBrandName = sc.nextLine().trim();
                            if (bl.isNewBrandNameValid(newBrandName)) {
                                break;
                            } else {
                                System.out.println("This name is invalid");
                            }
                        }
                        while (true) {
                            System.out.println("Input Sound's Brand: ");
                            newSoundBrand = sc.nextLine().trim();
                            if (bl.isNewSoundBrandValid(newSoundBrand)) {
                                break;
                            } else {
                                System.out.println("This brand is invalid");
                            }
                        }
                        while (true) {
                            System.out.println("Input Price: ");
                            newPrice = sc.nextLine();
                            if (bl.isNewPriceValid(newPrice)) {
                                break;
                            } else {
                                System.out.println("This price is invalid");
                            }
                        }
                        bl.updateBrand(brandID, newBrandName, newSoundBrand, Double.parseDouble(newPrice));
                    }
                    System.out.println("process finished!");
                    break;
                case 5:
                    bl.saveToFile("Brands.txt");
                    System.out.println("process finished!");
                    break;
                case 6:
                    cl.listCars();
                    System.out.println("process finished!");
                    break;
                case 7:
                    sc = new Scanner(System.in);
                    System.out.println("Input a brand name:");
                    String aPartOfBrandName = sc.nextLine().trim();
                    cl.printBasedBrandName(aPartOfBrandName);
                    System.out.println("process finished!");
                    break;
                case 8:
                    System.out.println("Menu");
                    System.out.println("+---+---------------+-----------------------------------+--------------------+--------------------+");
                    System.out.format("%-4s%1s%-15s%1s%-35s%1s%-20s%1s%-20s%1s","|", "|", "Brand ID", "|", "Brand name", "|", "Sound Brand", "|", "Price", "|");
                    System.out.println("");
                    Brand b = mnu.ref_getChoice(bl.getListBrand());
                    String newCarID = "";
                    while (true) {
                        System.out.println("Input your car's ID:");
                        newCarID = sc.nextLine();
                        if (cl.isNewCarIDValid(newCarID)) {
                            break;
                        } else {
                            System.out.println("This ID is exist");
                        }
                    }
                    String newColor = "";
                    while (true) {
                        System.out.println("Input Color: ");
                        newColor = sc.nextLine();
                        if (cl.isNewColorValid(newColor)) {
                            break;
                        } else {
                            System.out.println("This color is invalid");
                        }
                    }
                    String newFrameID = "";
                    while (true) {
                        System.out.println("Input Frame's ID: ");
                        newFrameID = sc.nextLine().trim();
                        if (cl.isFrameIDExist(newFrameID)) {
                            System.out.println("This Frame'ID is exist");
                        } else if (cl.isNewFrameIDValid(newFrameID)) {
                            break;
                        } else {
                            System.out.println("This Frame's ID is invalid");
                        }
                    }
                    String newEngineID = "";
                    while (true) {
                        System.out.println("Input Engine's ID: ");
                        newEngineID = sc.nextLine().trim();
                        if (cl.isEngineIDExist(newEngineID)) {
                            System.out.println("This Engine'ID is exist");
                        } else if (cl.isNewEngineIDValid(newEngineID)) {
                            break;
                        } else {
                            System.out.println("This Engine's ID is invalid");
                        }
                    }
                    cl.addCar(newCarID, b, newColor, newFrameID, newEngineID);
                    System.out.println("process finished!");
                    break;
                case 9:
                    sc = new Scanner(System.in);
                    System.out.println("Imput the Car's Id: ");
                    String removeCarID = sc.nextLine().trim();
                    if (cl.removeCar(removeCarID)) {
                        System.out.println("process finished!");
                    } else {
                        System.out.println("Not found!");
                    }
                    break;
                case 10:
                    sc = new Scanner(System.in);
                    System.out.println("Input the Car's ID:");
                    String updateCarID = sc.nextLine().trim();
                    if (cl.searchID(updateCarID) < 0) {
                        System.out.println("Not found!");
                    } else {
                        ArrayList<String> smallOptions = new ArrayList<>(Arrays.asList(
                                "Use the brand we have",
                                "Use a new Brand"
                        ));
                        int smallChoice = mnu.int_getChoice(smallOptions);
                        switch (smallChoice) {
                            case 1:
                                System.out.println("Brand menu");
                                Brand newBrand = mnu.ref_getChoice(bl.getListBrand());
                                while (true) {
                                    System.out.println("Input Color: ");
                                    newColor = sc.nextLine();
                                    if (cl.isNewColorValid(newColor)) {
                                        break;
                                    } else {
                                        System.out.println("This color is invalid");
                                    }
                                }
                                while (true) {
                                    System.out.println("Input Frame's ID: ");
                                    newFrameID = sc.nextLine().trim();
                                    if (cl.isFrameIDExist(newFrameID)) {
                                        System.out.println("This Frame'ID is exist");
                                    } else if (cl.isNewFrameIDValid(newFrameID)) {
                                        break;
                                    } else {
                                        System.out.println("This Frame's ID is invalid");
                                    }
                                }
                                while (true) {
                                    System.out.println("Input Engine's ID: ");
                                    newEngineID = sc.nextLine().trim();
                                    if (cl.isEngineIDExist(newEngineID)) {
                                        System.out.println("This Engine'ID is exist");
                                    } else if (cl.isNewEngineIDValid(newEngineID)) {
                                        break;
                                    } else {
                                        System.out.println("This Engine's ID is invalid");
                                    }
                                }
                                if (cl.updateCar(updateCarID, newBrand, newColor, newFrameID, newEngineID)) {
                                    System.out.println("process finished!");
                                } else {
                                    System.out.println("Error");
                                }

                                break;
                            case 2:
                                while (true) {
                                    System.out.println("Input your new brand:");
                                    newBrandName = sc.nextLine().trim();
                                    if (bl.isNewBrandNameExist(newBrandName)) {
                                        System.out.println("This brand is exist");
                                    } else if (bl.isNewBrandNameValid(newBrandName)) {
                                        System.out.println("This brand is invalid");
                                    } else {
                                        break;
                                    }
                                }
                                while (true) {
                                    System.out.println("Input Color: ");
                                    newColor = sc.nextLine();
                                    if (cl.isNewColorValid(newColor)) {
                                        break;
                                    } else {
                                        System.out.println("This color is invalid");
                                    }
                                }
                                while (true) {
                                    System.out.println("Input Frame's ID: ");
                                    newFrameID = sc.nextLine().trim();
                                    if (cl.isFrameIDExist(newFrameID)) {
                                        System.out.println("This Frame'ID is exist");
                                    } else if (cl.isNewFrameIDValid(newFrameID)) {
                                        break;
                                    } else {
                                        System.out.println("This Frame's ID is invalid");
                                    }
                                }
                                while (true) {
                                    System.out.println("Input Engine's ID: ");
                                    newEngineID = sc.nextLine().trim();
                                    if (cl.isEngineIDExist(newEngineID)) {
                                        System.out.println("This Engine'ID is exist");
                                    } else if (cl.isNewEngineIDValid(newEngineID)) {
                                        break;
                                    } else {
                                        System.out.println("This Engine's ID is invalid");
                                    }
                                }
                                if (cl.updateCar(updateCarID, newBrandName, newColor, newFrameID, newEngineID)) {
                                    System.out.println("process finished!");
                                } else {
                                    System.out.println("Error");
                                }
                        }

                    }
                    break;

                case 11:
                    cl.saveToFile("Cars.txt");
                    System.out.println("process finished!");
                    break;
                case 12:
                    choice = -1;
                    break;
            }
        } while (choice > 0 && choice <= options.size());
    }
}
