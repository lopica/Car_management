package classes.Com;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Pattern;

public class BrandList {

    ArrayList<Brand> listBrand = new ArrayList<>();
    //CarManager cm = new CarManager();

    public BrandList() {
    }

    public Brand getOneBrand(int i) {
        return listBrand.get(i);
    }

    public boolean loadFromFile(String filename) throws FileNotFoundException, IOException {
        File f = new File(filename);
        Scanner sc = new Scanner(Paths.get(filename), "UTF-8");
        if (f == null) {
            return false;
        } else {
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                String[] data = line.split(",");
                String brandID = data[0].trim();
                //System.out.println(data.length);
                String brandName = data[1].trim();
                String data2 = data[2].trim();
                String[] s = data2.split(":");
                String soundBrand = s[0].trim();
                double price = Double.parseDouble(s[1]);
                listBrand.add(new Brand(brandID, brandName, soundBrand, price));
            }
            //close the file
        }
        return true;
    }

    public ArrayList<Brand> getListBrand() {
        return listBrand;
    }

    public boolean saveToFile(String filename) throws IOException {
        FileWriter fw = new FileWriter(filename);
        BufferedWriter bw = new BufferedWriter(fw);
        for (Brand b : listBrand) {
            bw.write(b.saveData());
            bw.newLine();
        }
        bw.close();
        fw.close();
        return true;
    }

    public int searchID(String bID) {
        int N = listBrand.size();
        //System.out.println(bID+ "\n");
        for (int i = 0; i < N; i++) {
            //System.out.println(listBrand.get(i).brandID);
            if (listBrand.get(i).brandID.equals(bID)) {
                return i;
            }
        }
        return -1;
    }

    public Brand getUserChoice() {
        Menu mnu = new Menu();
        Brand choice = mnu.ref_getChoice(listBrand);
        return choice;
    }

    public void addBrand(String newBrandID, String newBrandName, String newSoundBrand, String newPrice) {
        if (isNewBrandIDValid(newBrandID) && isNewBrandNameValid(newBrandName) && isNewSoundBrandValid(newSoundBrand) && isNewPriceValid(newPrice)) {
            listBrand.add(new Brand(newBrandID, newBrandName, newSoundBrand, Double.parseDouble(newPrice)));
        } else {
            System.out.println("Input is invalid");
        }
    }

    public void updateBrand(String brandID, String newBrandName, String newSoundBrand, double newPrice) {
        int pos = searchID(brandID);
        listBrand.get(pos).setBrandName(newBrandName);
        listBrand.get(pos).setSoundBrand(newSoundBrand);
        listBrand.get(pos).setPrice(newPrice);

    }

    public void listBrands() {
        int N = listBrand.size();
        System.out.println("+---------------+-----------------------------------+--------------------+--------------------+");
        System.out.format("%1s%-15s%1s%-35s%1s%-20s%1s%-20s%1s", "|", "Brand ID", "|", "Brand name", "|", "Sound Brand", "|", "Price","|");
        System.out.println("");
        System.out.println("+---------------+-----------------------------------+--------------------+--------------------+");
        for (int i = 0; i < N; i++) {
            System.out.println(listBrand.get(i));
        }
        System.out.println("+---------------+-----------------------------------+--------------------+--------------------+");
        System.out.println("");
    }

    public Brand getBrandFromID(String brandID) {

        int index = 0;
        for (int i = 0; i < listBrand.size(); i++) {
            if (brandID.equals(listBrand.get(i).brandID)) {
                index = i;
            }
        }
        return listBrand.get(index);
    }

    public boolean isNewBrandIDValid(String newBrandID) {
        while (true) {
            return getListBrand().stream().noneMatch((o) -> (newBrandID.equals(o.getBrandID())));
        }
    }

    public boolean isNewBrandNameValid(String newBrandName) {
        return !"".equals(newBrandName);
    }

    public boolean isNewBrandNameExist(String newBrandName) {
        return listBrand.stream().noneMatch((o) -> (o.getBrandName().equals(newBrandName)));
    }

    public boolean isNewSoundBrandValid(String newSoundBrand) {
        return !"".equals(newSoundBrand);
    }

    public boolean isNewPriceValid(String newPrice) {
        Pattern p = Pattern.compile("^[0-9]+$");
        return !(!(p.matcher(newPrice).find()) || Double.parseDouble(newPrice) <= 0);
    }
}
