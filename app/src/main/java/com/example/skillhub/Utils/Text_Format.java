package com.example.skillhub.Utils;

public class Text_Format
{
    public static String change(String originalText) {
        int wrapAfterWords = 5;
        int maxWords = 8;

        String[] words = originalText.split("\\s+"); // Split by space
        StringBuilder formatted = new StringBuilder();

        for (int i = 0; i < words.length && i < maxWords; i++) {
            formatted.append(words[i]);
            if ((i + 1) % wrapAfterWords == 0) {
                formatted.append("\n"); // Wrap after every 4 words
            } else {
                formatted.append(" ");
            }
        }

        // If there are more than maxWords, add "..."
        if (words.length > maxWords) {
            formatted.append("...");
        }
        return  formatted.toString().trim();
    }

}
