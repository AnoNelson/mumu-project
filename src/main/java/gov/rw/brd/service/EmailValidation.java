package gov.rw.brd.service;

import com.google.gson.Gson;
import gov.rw.brd.domain.EmailChecker;
import org.springframework.stereotype.Service;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Nick on 4/8/2020.
 */
@Service
public class EmailValidation {
    private static final String apikey="3b26fab0027edaee028dbbd84900fb99";


    public Boolean validate(String email)throws Exception{

        String url = "https://apilayer.net/api/check?access_key="+apikey+"&email="+email+"&smtp=1&format=1";

        URL urlobj = new URL(url);

        HttpURLConnection con = (HttpURLConnection) urlobj.openConnection();

        // optional default is GET
        con.setRequestMethod("GET");

        //add request header
        con.setRequestProperty("User-Agent", "Mozilla/17.0");


        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        //print result
        System.out.println(response.toString());
        Gson g = new Gson();
        EmailChecker emailChecker = g.fromJson(response.toString(),EmailChecker.class);
        if(emailChecker !=null && (emailChecker.getSmtp_check().equals(true) && Double.parseDouble(emailChecker.getScore())>=0.8)){
            System.out.println("me");
            return true;
        }else{
            return false;
        }
    }
}
