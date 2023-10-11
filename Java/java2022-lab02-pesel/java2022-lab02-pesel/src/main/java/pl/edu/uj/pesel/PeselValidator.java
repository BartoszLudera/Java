package pl.edu.uj.pesel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.MessageFormat;
import java.io.*;

public class PeselValidator {

    private static final Logger logger = LoggerFactory.getLogger(PeselValidator.class);

    public static void main(String[] args) {
        // DONE // should read inp    ut file path and write to output file
        try {
            BufferedReader br1 = new BufferedReader(new FileReader("/home/bartosz/Java/tmp2/java2022/java2022-lab02-pesel/peselIDin.txt"));
            BufferedWriter br2 = new BufferedWriter(new FileWriter("/home/bartosz/Java/tmp2/java2022/java2022-lab02-pesel/peselIDout.txt"));
            String peselArg;

            while ((peselArg = br1.readLine()) != null) {
                Pesel pesel = new Pesel(peselArg);
                boolean valid = Pesel.check(pesel);
                if (valid == true) {
                    br2.write("VALID\n");
                } else {
                    br2.write("INVALID\n");
                }
            }
            br1.close();
            br2.close();
        } catch (Exception ex) {
            return;
        }
    }
}
