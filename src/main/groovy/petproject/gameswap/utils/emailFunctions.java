package petproject.gameswap.utils;

public class EmailFunctions {

    public static boolean isEmailValid(String email){
        return email.contains("@") && email.contains(".")
                && email.indexOf("@") < email.lastIndexOf(".");
    }
}
