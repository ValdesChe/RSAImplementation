import security.RSA;

import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;

/**
 * Created by ValdoR on 2019-03-13.
 */
public class TestCryptage {
    public static void main(String args[]) throws IOException {
        RSA crypto = new RSA(25);
        System.out.println(crypto.toString());

        byte [] messageByte = "aABCDEFdd54fdgdfgdfg5856+".getBytes();
        System.out.println("BYTES AVANT CRYPT");
        for (int i = 0; i < messageByte.length; i++) {
            System.out.print(messageByte[i] + " ** ");
        }

        BigInteger[] encripted_data = crypto.encrypt(messageByte);
        String encrypted_string = "";
        for( int i = 0 ; i < encripted_data.length ; i++ )
        {
            encrypted_string += " ** " + ((char)encripted_data[i].intValue());
        }

        System.out.print("\n BYTES APRES CRYPT");
        System.out.println("\n" + encrypted_string);

        System.out.println("\n BYTES APRES DECRYPTAGE ");
        String decrypted = crypto.decrypt(encripted_data);
        System.out.println(decrypted);


    }
}
