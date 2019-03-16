package utils;

/**
 * Created by ValdoR on 2019-03-14.
 */
public class MessageParser {

    public static char KEY_MESSAGE = 'M';
    public static char KEY_PUBLIC_KEY= 'C';
    public static char KEY_UNKNOW = 'U';

    public final char [] codes = {KEY_MESSAGE, KEY_PUBLIC_KEY };

    public String message = null;

    /**
     *  Retourne le type du message
     * @param message
     */
    public MessageParser(String message) {
        this.message = message;
    }

    public char giveTypeOfMessage(){
        if (this.message.length() < 2){
            return KEY_UNKNOW;
        }
        return this.message.toUpperCase().charAt(0);
    }


    /***
     *  Retourne  le contenu du message.
     * @return null || String
     */
    public String getMessageContent(){
        if (this.message.length() < 2){
            return null;
        }
        return this.message.substring(1, message.length());
    }
}
