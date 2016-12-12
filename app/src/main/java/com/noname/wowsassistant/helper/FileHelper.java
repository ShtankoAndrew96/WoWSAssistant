package com.noname.wowsassistant.helper;

public class FileHelper {
    private static String [] extentions = {".png",".jpeg",".gif"};

    public static boolean checkForValidImageName(String filename){
        for(int i=0; i<extentions.length; i++){
            if(filename.contains(extentions[i])){
                return true;
            }
        }
        return false;
    }
}
