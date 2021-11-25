
import classes.Com.BrandList;
import classes.Com.CarList;
import java.io.IOException;


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author DucAnh;
 */
public class TestProject {

    BrandList bt = new BrandList();
    CarList ct = new CarList(bt);

    public static void main(String[] args) throws IOException {
        TestProject t = new TestProject();
        t.test1();
        t.test2();
        t.test3();
        t.test4();
        t.test5();
        t.test6();
        t.test7();
        t.test8();
        t.test9();
        t.test10();
        t.test11();
    }

    public void test1() throws IOException {
        System.out.println("Test 1: print the brand list");
        bt.loadFromFile("Brands.txt");
        bt.listBrands();
        System.out.println("");
    }

    public void test2() {
        System.out.println("Test 2: add a new brand and then add it again to check duplicate case");
        bt.addBrand("B7-MS2", "BMW 730Li M", "Alpine", "4.050");
        bt.listBrands();
        bt.addBrand("B7-MS2", "BMW 730Li M", "Alpine", "4.050");
        System.out.println("");
    }

    public void test3() {
        System.out.println("Test 3: search id do not have and do have in the brand's list");
        if (bt.searchID("B5-30") == -1) {
            System.out.println("No brand's ID is detected!");
        } else {
            System.out.println(bt.getOneBrand(bt.searchID("B5-30")));
        }
        System.out.println("");
        if (bt.searchID("B5-18") == -1) {
            System.out.println("No brand's ID is detected!");
        } else {
            System.out.format("%-15s%-35s%-20s%-20s", "Brand ID", "Brand name", "Sound Brand", "Price");
            System.out.println("");
            System.out.println(bt.getOneBrand(bt.searchID("B5-18")));
        }
        System.out.println("");
    }

    public void test4() {
        System.out.println("Test 4: update a new brand");
        System.out.println("Before update the brand whose ID is B7-MS:");
        bt.listBrands();
        System.out.println("After update the brand whose ID is B7-MS:");
        bt.updateBrand("B7-MS", "BMW 730Li MS", "Sony", 4.150);
        bt.listBrands();
        System.out.println("");
    }

    public void test5() throws IOException {
        System.out.println("Test 5: save to file Brands.txt");
        bt.saveToFile("Brands.txt");
        System.out.println("done!");
        System.out.println("");
    }

    public void test6() throws IOException {
        System.out.println("Test 6: print the car list");
        ct.loadFromFile("Cars.txt");
        ct.listCars();
    }

    public void test7() {
        System.out.println("Test 7: search cars based on its brand name");
        System.out.println("Case '960' which should not show any result");
        ct.printBasedBrandName("960");
        System.out.println("");
        System.out.println("Case '730' which should show results");
        ct.printBasedBrandName("730");
        System.out.println("");
    }

    public void test8() {
        System.out.println("Test 8: add a new car");
        System.out.println("Before add:");
        ct.listCars();
        System.out.println("After add:");
        ct.addCar("C08", bt.getBrandFromID("B5-18"), "yellow", "F12352", "E12352");
        ct.listCars();
        System.out.println("");
    }

    public void test9() {
        System.out.println("Test 9: remove a car based on its ID:");
        System.out.println("remove a car do not have");
        if (ct.removeCar("C10")) {
            System.out.println("Error");
        } else {
            System.out.println("done!");
        }
        System.out.println("remove a car do have");
        if (ct.removeCar("C06")) {
            System.out.println("Error");
        } else {
            System.out.println("done!");
        }
        System.out.println("");
    }

    public void test10() {
        System.out.println("Test 10: update a car based on its ID");
        System.out.println("case do not have that ID in the list");
        if (ct.searchID("C10") == -1) {
            System.out.println("not found!");
        } else {
            System.out.println("Before update:");
            ct.listCars();
            System.out.println("After update:");
            ct.updateCar("C03", "B7-MS", "orange", "F12347", "E12347");
        }
        System.out.println("");
        System.out.println("case do have that ID in the list");
        if (ct.searchID("C03") == -1) {
            System.out.println("not found!");
        } else {
            System.out.println("Before update:");
            ct.listCars();
            System.out.println("After update:");
            ct.updateCar("C03", "B5-18", "brown", "F99999", "E99999");
        }
        System.out.println("");
    }

    public void test11() throws IOException {
        System.out.println("Test 11: save to file Cars.txt");
        if (ct.saveToFile("Cars.txt")) {
            System.out.println("done!");
        } else {
            System.out.println("Error");
        }
    }
}
