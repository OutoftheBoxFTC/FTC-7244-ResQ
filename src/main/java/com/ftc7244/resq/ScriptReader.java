package com.qualcomm.ftcrobotcontroller.opmodes;

import android.app.Activity;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.FileReader;

import java.util.ArrayList;

/**
 * Created by Iam on 11/15/2015.
 */
public class ScriptReader extends Activity {
    public static final String FOLDER = "/downloads";
    public static final String ROOT = "/";
    private File scriptFile;
    private String scriptText;
    private ArrayList<String> scripts;

    public ScriptReader(String fileName){
        scriptFile = new File(ROOT + FOLDER + fileName);
        try {
            scriptText = readFile(scriptFile);
        } catch(Exception e){} finally {
            if(scriptText == null) scriptText = "";
        }
        scripts = parseStringToScripts(scriptText);
    }

    //Thanks random stackoverflow user
    public String readFile(File file) throws IOException{
        BufferedReader br;
        try{br = new BufferedReader(new FileReader(file));} catch(Exception e){return "Failed";}
        try {
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();

            while (line != null) {
                sb.append(line);
                sb.append("\n");
                line = br.readLine();
            }
            return sb.toString();
        } catch(Exception e){}
            finally {
            br.close();
        }
        return "";
    }

    public ArrayList<String> parseStringToScripts(String script) {
        ArrayList<String> scripts = new ArrayList<String>();
        String[] scriptsArray = script.split("/n");
        for(String text: scriptsArray) {
            scripts.add(text);
        }
        return scripts;
    }

    public String getNextScript() {
        if(!scripts.isEmpty()) {
            String returnScript = scripts.get(0);
            scripts.remove(0);
            return returnScript;
        } else {
            return "done";
        }
    }

    protected static ScriptReader buildScriptReader(String fileName) throws IOException{
        return new ScriptReader(fileName);
    }
}
