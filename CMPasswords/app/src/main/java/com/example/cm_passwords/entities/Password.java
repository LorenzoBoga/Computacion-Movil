package com.example.cm_passwords.entities;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.regex.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Password {

    private Integer id;
    private String site;
    private String user;
    private String password;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

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

    public String hidePassword(){
        String returnPassword = password;
        returnPassword = returnPassword.replaceAll(".","*");
        System.out.println(returnPassword);
        return returnPassword;
    }

    public static void changeStrenghtBar(EditText passwordInput, ProgressBar progressBar, TextView strengthText){
        if(passwordInput.getText().toString().equals("")){
            progressBar.setProgress(0);
            strengthText.setText("");
        }else{
            Password fakePsw = new Password();
            fakePsw.setPassword(passwordInput.getText().toString());
            switch(fakePsw.measureStrength()){
                case 0:
                    progressBar.setProgress(20);
                    strengthText.setText("Very weak");
                    break;
                case 1:
                    progressBar.setProgress(40);
                    strengthText.setText("Weak");
                    break;
                case 2:
                    progressBar.setProgress(60);
                    strengthText.setText("Medium");
                    break;
                case 3:
                    progressBar.setProgress(80);
                    strengthText.setText("Strong");
                    break;
                case 4:
                    progressBar.setProgress(100);
                    strengthText.setText("Very strong");
                    break;
            }}
    }
}
