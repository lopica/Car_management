package classes.Com;

public class Brand {
    String brandID;
    String brandName;
    String soundBrand;
    double price;

    public Brand() {
    }

    public Brand(String brandID, String brandName, String soundBrand, double price) {
        this.brandID = brandID;
        this.brandName = brandName;
        this.soundBrand = soundBrand;
        this.price = price;
    }

    public String getBrandID() {
        return brandID;
    }

    public void setBrandID(String brandID) {
        this.brandID = brandID;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public String getSoundBrand() {
        return soundBrand;
    }

    public void setSoundBrand(String soundBrand) {
        this.soundBrand = soundBrand;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return String.format("%1s%-15s%1s%-35s%1s%-20s%1s%-20.3f%1s","|",brandID,"|",brandName,"|",soundBrand,"|",price,"|");
    }
    
    public String saveData(){
        return brandID + "," + brandName + "," + soundBrand + ":" + price;
    }
}
