package security;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Random;

/**
 * Created by ValdoR on 2019-03-12.
 */
@SuppressWarnings("JavaDoc")
public class RSA {

    private final static SecureRandom random = new SecureRandom();
    private final static BigInteger one = BigInteger.ONE;


    // Modulo <=> n

    private BigInteger modulo;

    private BigInteger clePublique;
    private BigInteger clePrivee;
    private int tailleBit = 105;

    /***
     * Constructeur security.RSA
     * @param tailleBit
     */
    public RSA(int tailleBit) {
        this.tailleBit = tailleBit;
        // Generation  de deux nombres premiers p et q
        BigInteger p = BigInteger.probablePrime(tailleBit / 2, new Random());
        BigInteger q = BigInteger.probablePrime(tailleBit / 2, new Random());

        // Determination du produit phi = (p-1) * (q-1)
        BigInteger phi = (p.subtract(one)).multiply(q.subtract(one));


        // n = p*q
        this.modulo = p.multiply(q);

        // e =
        this.clePublique = BigInteger.probablePrime (tailleBit/3 , new Random());
        // this.clePublique = new BigInteger("1531");
        // this.clePublique = new BigInteger(Integer.toString(new Random().nextInt(16547)));

        // { n ,e } is public Key

        // Cle privée calculée en inversant d mod phi
        this.clePrivee = clePublique.modInverse(phi);
    }

    /***
     *  Cryptage du message
     * @param message
     * @return
     */
    public BigInteger encrypt(BigInteger message){

        return message.modPow(clePublique, modulo);
    }

    /***
     * Decryptage du message
     * @param encrypted
     * @return
     */
    public BigInteger decrypt(BigInteger encrypted){
        return encrypted.modPow(clePrivee, modulo);
    }

    /***
     *
     * @param message
     * @return
     */
    public BigInteger[] encrypt(byte [] message){
        byte[] temp = new byte[1] ;
        BigInteger[] bigdigits = new BigInteger[message.length] ;
        for (int i = 0; i < message.length; i++) {
            temp[0] = message[i] ;
            bigdigits[i] = new BigInteger( temp ) ;
        }

        BigInteger[] encrypted = new BigInteger[bigdigits.length] ;
        String encrypted_string = "";
        for( int i = 0 ; i < bigdigits.length ; i++ )
        {
            // encrypted[i] = bigdigits[i].modPow( clePublique, modulo) ;
            encrypted[i] = this.encrypt(bigdigits[i]);
            //System.out.print(encrypted[i]);
            encrypted_string += encrypted[i].toString();
        }
        return encrypted;
    }

    /***
     * Décryptage du message
     * @param encrypted
     * @return
     */
    public String decrypt(BigInteger[] encrypted){

        BigInteger[] decrypted = new BigInteger[encrypted.length] ;
        for( int i = 0 ; i < decrypted.length ; i++ )
            // decrypted[i] = encrypted[i].modPow( clePrivee , modulo);
            decrypted[i] = this.decrypt(encrypted[i]);


        char[] charArray = new char[decrypted.length] ;
        for( int i = 0 ; i < charArray.length ; i++ )
            charArray[i] = (char) (decrypted[i].intValue());
        // System.out.println( new String(charArray)) ;

        return new String(charArray);
        // return this.decrypt(new BigInteger(message)).toString();
    }

    public BigInteger getModulus(){
        return this.modulo;
    }

    public String getPublicKey(){
        return this.clePublique.toString()+"-"+ this.modulo.toString();
    }

    @Override
    public String toString(){
        return 	"----------------------------------------\n" +
                "\nClé Publique:\t" + this.clePublique +
                "\nClé Privée :\t" + this.clePrivee +
                "\nModulo :\t" + this.modulo +
         	    "\n----------------------------------------\n" ;
    }
}
