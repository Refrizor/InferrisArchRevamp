package com.inferris.utils;

public class ChatUtils {

    public static String translateAlternateColorCodes(char altColorChar, String textToTranslate) {
        return textToTranslate.replace(altColorChar, '§');
    }
}
