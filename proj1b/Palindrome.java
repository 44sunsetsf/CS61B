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
        Deque<Character> d = wordToDeque(word);
        while (d.size() > 1) {
            if (d.removeFirst() != d.removeLast()) {
                return false;
            }
        }
        return true;
    }
}
