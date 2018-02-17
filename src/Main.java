import java.io.FileReader;
import java.io.IOException;
import java.io.BufferedReader;
import java.math.BigInteger;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        FileReader fr;
        BufferedReader br;
        List<Product> data = new ArrayList<Product>();
        int milkas = 0;

        System.out.println("---------------------------READER-OPEN---------------------------");

        try {
            String sCurrentLine;

            fr = new FileReader("data.csv");
            br = new BufferedReader(fr);

            br.readLine();


            while ((sCurrentLine = br.readLine()) != null) {
                data.add(new Product(sCurrentLine));
            }
            fr = new FileReader("tags.txt");
            br = new BufferedReader(fr);

            while ((sCurrentLine = br.readLine()) != null) {
                Sgtin_96 bla;
                try {
                    bla = new Sgtin_96(sCurrentLine);

                    if(bla.isThisProduct(getMilka(data))) {
                        milkas++;
                    }

                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }

            }

            fr.close();
            br.close();
        } catch (IOException ioe) {
            System.out.println(ioe.getMessage());
        }
        System.out.println("---------------------------READER-CLOSE--------------------------");

        System.out.println("Postoji "+Sgtin_96.getValidSgtin_96()+" prozvoda");

        System.out.println("od toga "+milkas+" milka oreo");

        System.out.println("Invalid SGTIN_96: "+Sgtin_96.getInvalidSgtin_96());

    }

    static Product getMilka(List<Product> pr) {
        for (Product item : pr) {
            if(item.getItemName().toUpperCase().contains("MILKA OREO")) {
                return item;
            }
        }

        return null;
    }
}
