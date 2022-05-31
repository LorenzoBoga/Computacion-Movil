package com.example.cm_passwords.entities;
import java.util.regex.*;
public class Password {

    private String site;
    private String user;
    private String password;

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int measureStrength(){
        if(password.length() < 8)
            return 0 ;
        else if(password.length() <15)
            return 1;
        else
            return this.itsGreenOrYellow();
    }

    public int itsGreenOrYellow(){
        boolean hasNumbers = Pattern.matches(".*[0-9].*", this.password);
        boolean hasMayus = Pattern.matches(".*[A-Z].*", this.password);
        boolean hasMinus = Pattern.matches(".*[a-z].*", this.password);
        boolean hasSymbols = Pattern.matches(".*[`~!@#$%^&*()\\\\-_=+\\\\\\\\|\\\\[{\\\\]};:'\\\",<.>/?].*", this.password);
        System.out.println("minus:" + hasMinus);
        System.out.println("num:" + hasNumbers);
        System.out.println("symbol:" + hasSymbols);
        System.out.println("mayus:" + hasMayus);
        boolean[] requirements = {hasNumbers,hasMayus,hasMinus,hasSymbols};
        int requirementsMet = 0;
        for (int i = 0; i<requirements.length;i++) {
            if(requirements[i])
                requirementsMet++;
        }
        System.out.println(requirementsMet);
        if(requirementsMet == 4) return 4;
        if( (requirementsMet == 1) || (requirementsMet == 2 && !(hasMinus && hasMayus)))
            return 2;
        return 3;
    }
}
