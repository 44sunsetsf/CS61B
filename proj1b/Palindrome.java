import java.security.spec.PSSParameterSpec;

public class Palindrome {
    public Deque<Character> wordToDeque(String word) {
        Deque<Character> result = new ArrayDeque<>();
        for (int i = 0; i < word.length(); i++) {
           result.addLast(word.charAt(i));
        }
        return result;
    }
    public boolean isPalindrome(String word) {
        return isPalindromeHelper(wordToDeque(word));
    }
    public boolean isPalindromeHelper(Deque<Character> w) {
        while (w.size() > 1){
            return w.removeFirst() == w.removeLast() && isPalindromeHelper(w);
        }
        return true;
    }
    public boolean isPalindrome(String word, CharacterComparator cc) {
        return isPalindromeHelp(wordToDeque(word),cc);
    }
    public boolean isPalindromeHelp(Deque<Character> w, CharacterComparator cc){
        while (w.size() > 1){
            return cc.equalChars(w.removeFirst(), w.removeLast()) && isPalindromeHelp(w,cc);
        }
        return true;
    }
}
