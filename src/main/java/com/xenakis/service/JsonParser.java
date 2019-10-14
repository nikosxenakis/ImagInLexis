package com.xenakis.service;

import com.xenakis.ImagInLexis;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;

public class JsonParser {

    private static JSONParser parser = new JSONParser();

    public static JSONObject loadObject(String filePath){

        InputStream input = ImagInLexis.class.getResourceAsStream(filePath);
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new InputStreamReader(input,"UTF-8"));

        } catch (UnsupportedEncodingException e2) {
            // TODO Auto-generated catch block
            e2.printStackTrace();
        }

        StringBuilder out = new StringBuilder();
        String line;
        try {
            while ((line = reader.readLine()) != null) {
                out.append(line);
            }
            reader.close();

        } catch (IOException e1) {
            e1.printStackTrace();
        }

        Object obj = null;
        try {

            obj = parser.parse(out.toString());
        } catch (ParseException e) {
            e.printStackTrace();
        }


        return (JSONObject) obj;
    }
}
