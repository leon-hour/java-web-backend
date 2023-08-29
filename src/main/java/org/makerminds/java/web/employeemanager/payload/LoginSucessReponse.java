package org.makerminds.java.web.employeemanager.payload;

public class LoginSucessReponse {
    private String token;

    public LoginSucessReponse() {
    	
    }
    
    public LoginSucessReponse(String token) {
        this.token = token;
    }


    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return "JWTLoginSucessReponse{"+
                " token='" + token + '\'' +
                '}';
    }
}