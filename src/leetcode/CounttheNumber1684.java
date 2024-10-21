package leetcode;

import java.util.HashSet;
import java.util.Set;

public class CounttheNumber1684 {
    public static void main(String[] args) {
        System.out.println(countConsistentStrings("abc", new String[]{"bce", "a"}));
    }

    public static int countConsistentStrings(String allowed, String[] words) {
        int count = 0;
        Set<Character> letras = new HashSet<>();
        for (int i = 0; i < allowed.length(); i++) {
            char temp =allowed.charAt(i);
            letras.add(temp);
        }
        for (String word : words) {
            boolean esConsistente = true;

            for (int j = 0; j < word.length(); j++) {
                if (!letras.contains(word.charAt(j))) {
                    esConsistente = false;
                    break;
                }
            }
            if (esConsistente) {
                count++;
            }
        }
        return count;
    }
}
