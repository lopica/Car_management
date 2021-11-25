package classes.Com;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Menu {

    public int int_getChoice(ArrayList<String> options) {
        String response;
        int N = options.size();
        System.out.println("+---+---------------+-----------------------------------+--------------------+--------------------+");
        for (int i = 0; i < N; i++) {
            System.out.format("%1s%-2d%1s%-94s","|",(i + 1), ".",options.get(i));
            System.out.println("");
        }
        System.out.println("+---+---------------+-----------------------------------+--------------------+--------------------+");
        System.out.println("Please choose an option 1..." + N + " ");
        Scanner sc = new Scanner(System.in);
        Pattern p = Pattern.compile("^[0-9]+$");
        while(true){
            response = sc.nextLine().trim();
            if (!(p.matcher(response).find()) || Integer.parseInt(response) > N) {
                    System.out.println("This option is invalid");
            } else { break;} 
        }
        return Integer.parseInt(response);
    }

    public Brand ref_getChoice(ArrayList<Brand> options) {
        int response;
        int N = options.size();
        do {
            ArrayList<String> trans = new ArrayList<>();
            for (int i = 0; i < options.size(); i++) {
                trans.add(i, options.get(i).toString());
            }
            response = int_getChoice(trans);
        } while (response < 0 || response > N);
        return options.get(response - 1);
    }
}
