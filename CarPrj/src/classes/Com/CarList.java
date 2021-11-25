package classes.Com;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;
import java.util.regex.Pattern;

public class CarList {

    ArrayList<Car> listCar = new ArrayList<>();
    BrandList brandList;

    public CarList(BrandList blist) {
        this.brandList = blist;
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
                String carID = data[0].trim();
                String brandID = data[1].trim();
                String color = data[2].trim();
                String frameID = data[3].trim();
                String engineID = data[4].trim();
                //carID, brandID, color, frameID, engineID
                int pos = brandList.searchID(brandID);
                //System.out.println(pos);
                Brand b = brandList.listBrand.get(pos);
                listCar.add(new Car(carID, b, color, frameID, engineID));
            }
            //close the file
        }
        return true;
    }

    public boolean saveToFile(String filename) throws IOException {
        FileWriter fw = new FileWriter(filename);
        BufferedWriter bw = new BufferedWriter(fw);
        for (Car c : listCar) {
            bw.write(c.saveData());
            bw.newLine();
        }
        bw.close();
        fw.close();
        return true;
    }

    public int searchID(String carID) {
        int N = listCar.size();
        //System.out.println(carID);
        for (int i = 0; i < N; i++) {
            //System.out.println(listCar.get(i).carID);
            if (listCar.get(i).carID.equals(carID)) {
                return i;
            }
        }
        return -1;
    }

    public int searchFrame(String fID) {
        int N = listCar.size();
        for (int i = 0; i < N; i++) {
            if (listCar.get(i).frameID.equals(fID)) {
                return i;
            }
        }
        return -1;
    }

    public int searchEngine(String eID) {
        int N = listCar.size();
        for (int i = 0; i < N; i++) {
            if (listCar.get(i).engineID.equals(eID)) {
                return i;
            }
        }
        return -1;
    }

    public void addCar(String newCarID, Brand b, String newColor, String newFrameID, String newEngineID) {
        //System.out.println("cao addcar roi ^^");
        listCar.add(new Car(newCarID, b, newColor, newFrameID, newEngineID));
    }

    public void printBasedBrandName(String aPartOfBrandName) {
        int N = listCar.size();
        int count = 0;
        for (int i = 0; i < N; i++) {
            Car c = listCar.get(i);
            if (c.brand.brandName.toLowerCase().contains(aPartOfBrandName.toLowerCase())) {
                System.out.println(c.screenString());
                System.out.println("");
                count++;
            }
        }
        if (count == 0) {
            System.out.println("No car is detected!");
        }
    }

    public boolean removeCar(String removeCarID) {
        int pos = searchID(removeCarID);
        if (pos < 0) {
            return false;
        } else {
            listCar.remove(pos);
            return true;
        }
    }

    public boolean updateCar(String updateCarID, Brand newBrand, String newColor, String newFrameID, String newEngineID) {
        int pos = searchID(updateCarID);
        listCar.get(pos).setBrand(newBrand);
        listCar.get(pos).setColor(newColor);
        listCar.get(pos).setFrameID(newFrameID);
        listCar.get(pos).setEngineID(newEngineID);
        return true;
    }

    public boolean updateCar(String updateCarID, String newBrandName, String newColor, String newFrameID, String newEngineID) {
        int pos = searchID(updateCarID);
        listCar.get(pos).setBrandName(newBrandName);
        listCar.get(pos).setColor(newColor);
        listCar.get(pos).setFrameID(newFrameID);
        listCar.get(pos).setEngineID(newEngineID);
        return true;
    }

    public void listCars() {
        Collections.sort(listCar, new Comparator<Car>() {
            @Override
            public int compare(Car t, Car t1) {
                return t.getBrand().brandName.compareTo(t1.getBrand().brandName);
            }
        });
        int N = listCar.size();
        for (int i = 0; i < N; i++) {
            Car c = listCar.get(i);
            System.out.println(c.screenString());
            System.out.println("");
        }
    }

    public boolean isNewCarIDValid(String newCarID) {
        return listCar.stream().noneMatch((o) -> (newCarID.equals(o.getCarID())));
    }

    public boolean isNewColorValid(String newColor) {
        return !"".equals(newColor);
    }

    public boolean isNewFrameIDValid(String newFrameID) {
        Pattern p = Pattern.compile("^[F]{1}[0-9]{5}$");
        return p.matcher(newFrameID).find();
    }

    public boolean isFrameIDExist(String newFrameID) {
        return listCar.stream().anyMatch((o) -> (newFrameID.equals(o.frameID)));
    }

    public boolean isNewEngineIDValid(String newEngineID) {
        Pattern p = Pattern.compile("^[E]{1}[0-9]{5}$");
        return p.matcher(newEngineID).find();
    }

    public boolean isEngineIDExist(String newEngineID) {
        return listCar.stream().anyMatch((o) -> (newEngineID.equals(o.frameID)));
    }

    public double getnewPrice() {
        Scanner sc = new Scanner(System.in);
        Pattern p = Pattern.compile("^[0-9]+$");
        String newPrice = "";
        while (true) {
            System.out.println("Input Price: ");
            newPrice = sc.nextLine();
            if (!(p.matcher(newPrice).find()) || Double.parseDouble(newPrice) <= 0) {
                System.out.println("This price is invalid");
            } else {
                break;
            }
        }
        return Double.parseDouble(newPrice);
    }
}
