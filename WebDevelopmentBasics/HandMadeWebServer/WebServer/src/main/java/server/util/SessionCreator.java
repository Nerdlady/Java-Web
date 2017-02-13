package server.util;

import java.math.BigInteger;
import java.security.SecureRandom;

public class SessionCreator {
    private SecureRandom random;
    private static SessionCreator sessionCreator;

    public static SessionCreator getInstance(){
        if (sessionCreator == null){
            sessionCreator = new SessionCreator();
        }

        return sessionCreator;
    }


    private SessionCreator() {
        this.random = new SecureRandom();
    }

    public String generateSessionId() {
        return new BigInteger(130, this.random).toString(32);
    }

}
