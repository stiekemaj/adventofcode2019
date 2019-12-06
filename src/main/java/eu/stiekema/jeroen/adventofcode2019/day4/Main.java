package eu.stiekema.jeroen.adventofcode2019.day4;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) {
        int passwordStartRange = 152085;
        int passwordEndRange = 670283;

        PasswordChecker passwordChecker = new PasswordChecker();
        passwordChecker.addRule(new PasswordFormatRule());
        passwordChecker.addRule(new DigitShouldNotDecreaseRule());
        passwordChecker.addRule(new TwoAdjacentDigitAreSameRule());

        System.out.println("Part 1: Number of possible password: " + calculatePossiblePasswords(passwordStartRange, passwordEndRange, passwordChecker).size());

        passwordChecker.addRule(new TwoAndOnlyTwoAdjacentDigitAreSameRule());

        System.out.println("Part 2: Number of possible password: " + calculatePossiblePasswords(passwordStartRange, passwordEndRange, passwordChecker).size());
    }

    private static List<Integer> calculatePossiblePasswords(int passwordStartRange, int passwordEndRange, PasswordChecker passwordChecker) {
        List<Integer> possiblePasswords = new ArrayList<>();

        for (int password = passwordStartRange; password <= passwordEndRange; password++) {
            if (passwordChecker.isValid(password)) {
                possiblePasswords.add(password);
            }
        }
        return possiblePasswords;
    }

    static class PasswordChecker {
        private List<PasswordRule> passwordRules = new ArrayList<>();

        void addRule(PasswordRule passwordRule) {
            passwordRules.add(passwordRule);
        }

        boolean isValid(int password) {
            String passwordString = Integer.toString(password);

            for (PasswordRule passwordRule : passwordRules) {
                if (!passwordRule.isValid(passwordString)) {
                    return false;
                }
            }

            return true;
        }
    }

    interface PasswordRule {
        boolean isValid(String password);
    }

    static class PasswordFormatRule implements PasswordRule {
        @Override
        public boolean isValid(String password) {
            return Pattern.matches("[0-9]{6}", password);
        }
    }

    static class TwoAdjacentDigitAreSameRule implements PasswordRule {
        @Override
        public boolean isValid(String password) {
            for (int i = 0; i < password.length(); i++) {
                if (i + 1 < password.length()) {
                    if (password.charAt(i) == password.charAt(i+1)) {
                        return true;
                    }
                }
            }
            return false;
        }
    }

    static class TwoAndOnlyTwoAdjacentDigitAreSameRule implements PasswordRule {
        @Override
        public boolean isValid(String password) {
            char currentChar = ' ';
            int currentCharCount = 0;

            for (int i = 0; i < password.length(); i++) {
                if (password.charAt(i) == currentChar) {
                    currentCharCount++;
                } else if (currentCharCount == 2) {
                    return true;
                } else {
                    currentChar = password.charAt(i);
                    currentCharCount = 1;
                }

            }

            if (currentCharCount == 2) {
                return true;
            } else {
                return false;
            }
        }
    }

    static class DigitShouldNotDecreaseRule implements PasswordRule {
        @Override
        public boolean isValid(String password) {
            char lastDigit = '0';
            for (int i = 0; i < password.length(); i++) {
                if (password.charAt(i) < lastDigit) {
                    return false;
                }
                lastDigit = password.charAt(i);
            }
            return true;
        }
    }
}
