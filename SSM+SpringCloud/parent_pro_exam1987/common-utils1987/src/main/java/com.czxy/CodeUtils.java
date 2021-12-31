package com.czxy;

import java.util.Random;

public class CodeUtils {
    public static String Code(){
        String code = "";
        Random random = new Random();
        for (int i = 0; i < 6; i++) {
            code+=random.nextInt(10);
        }
        return code;
    }
}
