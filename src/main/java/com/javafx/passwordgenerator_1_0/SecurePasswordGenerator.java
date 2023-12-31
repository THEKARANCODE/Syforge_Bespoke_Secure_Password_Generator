package com.javafx.passwordgenerator_1_0;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Arrays;

public class SecurePasswordGenerator {
    public static String generateSecurePassword(int passwordLength, boolean includeLowerCaseChars, boolean includeUpperCaseChars, boolean includeNumericalChars, boolean includeSpecialChars, boolean includeSpace) {
        if (includeLowerCaseChars||includeUpperCaseChars||includeNumericalChars||includeSpecialChars||includeSpace) {
            char[] securePassword = new char[Math.max(12, passwordLength)];
            char[] charSet = getCharArray(includeLowerCaseChars, includeUpperCaseChars, includeNumericalChars, includeSpecialChars, includeSpace);

            if (charSet.length > 1) {
                for (int i = 0; i < securePassword.length; i++) {
                    securePassword[i] = charSet[getSecureRandomInteger(0, charSet.length - 1)];
                }

                if (includeSpace){securePassword[getSecureRandomInteger(8,11)] = SpaceChar[0];}
                if (includeSpecialChars){securePassword[4] = SpecialChars[getSecureRandomInteger(0,SpecialChars.length-1)];}
                if (includeNumericalChars){securePassword[5] = NumericalChars[getSecureRandomInteger(0,NumericalChars.length-1)];}
                if (includeUpperCaseChars){securePassword[6] = UpperCaseChars[getSecureRandomInteger(0,UpperCaseChars.length-1)];}
                if (includeLowerCaseChars){securePassword[7] = LowerCaseChars[getSecureRandomInteger(0,LowerCaseChars.length-1)];}

            } else {
                Arrays.fill(securePassword, charSet[0]);
            }

            return new String(securePassword);
        } else {
            return generateSecurePassword(12,false);
        }
    }

    public static String generateSecurePassword(int passwordLength) {
        char[] securePassword = new char[Math.max(12, passwordLength)];
        char[] charSet = getCharArray(true, true, true, true, true);
        if (charSet.length > 1) {
            for (int i = 0; i < securePassword.length; i++) {
                securePassword[i] = charSet[getSecureRandomInteger(0, charSet.length - 1)];
            }

            securePassword[getSecureRandomInteger(8,11)] = SpaceChar[0];
            securePassword[4] = SpecialChars[getSecureRandomInteger(0,SpecialChars.length-1)];
            securePassword[5] = NumericalChars[getSecureRandomInteger(0,NumericalChars.length-1)];
            securePassword[6] = UpperCaseChars[getSecureRandomInteger(0,UpperCaseChars.length-1)];
            securePassword[7] = LowerCaseChars[getSecureRandomInteger(0,LowerCaseChars.length-1)];

        } else {
            Arrays.fill(securePassword, charSet[0]);
        }
        return new String(securePassword);
    }

    public static String generateSecurePassword(int passwordLength, boolean includeSpace) {
        char[] securePassword = new char[Math.max(12, passwordLength)];
        char[] charSet = getCharArray(true, true, true, true, includeSpace);

        if (charSet.length > 1) {
            for (int i = 0; i < securePassword.length; i++) {
                securePassword[i] = charSet[getSecureRandomInteger(0, charSet.length - 1)];
            }

            if (includeSpace){securePassword[getSecureRandomInteger(8,11)] = SpaceChar[0];}
            securePassword[4] = SpecialChars[getSecureRandomInteger(0,SpecialChars.length-1)];
            securePassword[5] = NumericalChars[getSecureRandomInteger(0,NumericalChars.length-1)];
            securePassword[6] = UpperCaseChars[getSecureRandomInteger(0,UpperCaseChars.length-1)];
            securePassword[7] = LowerCaseChars[getSecureRandomInteger(0,LowerCaseChars.length-1)];

        } else {
            Arrays.fill(securePassword, charSet[0]);
        }

        return new String(securePassword);
    }

    public static int getSecureRandomInteger(int lowerBound, int upperBound) {
        int randomNumber = 0;
        if (upperBound <= lowerBound) {
            throw new IllegalArgumentException("Invalid range!!");
        }
        try {
            randomNumber = ((SecureRandom.getInstance("DRBG").nextInt(upperBound - lowerBound + 1)) + lowerBound);
        } catch (NoSuchAlgorithmException e) {
            System.err.println("There is no such Algorithm, " + e.getMessage());
        }
        return randomNumber;
    }


    /*----------------------------------=========================================--------------------------------------*/

    private static char[] getCharArray(boolean includeLowerCaseChars, boolean includeUpperCaseChars,
                                       boolean includeNumericalChars, boolean includeSpecialChars, boolean includeSpace) {
        int charSetLength = (includeLowerCaseChars ? LowerCaseChars.length : 0) + (includeUpperCaseChars ? UpperCaseChars.length : 0) + (includeNumericalChars ? NumericalChars.length : 0) + (includeSpecialChars ? SpecialChars.length : 0) + (includeSpace ? SpaceChar.length : 0);
        char[] charSet = new char[charSetLength];

        if (includeLowerCaseChars) {
            System.arraycopy(LowerCaseChars, 0, charSet, getFirstIndexOf(charSet, '\u0000'), LowerCaseChars.length);
        }

        if (includeUpperCaseChars) {
            System.arraycopy(UpperCaseChars, 0, charSet, getFirstIndexOf(charSet, '\u0000'), UpperCaseChars.length);
        }

        if (includeSpace) {
            System.arraycopy(SpaceChar, 0, charSet, getFirstIndexOf(charSet, '\u0000'), SpaceChar.length);
        }

        if (includeNumericalChars) {
            System.arraycopy(NumericalChars, 0, charSet, getFirstIndexOf(charSet, '\u0000'), NumericalChars.length);
        }

        if (includeSpecialChars) {
            System.arraycopy(SpecialChars, 0, charSet, getFirstIndexOf(charSet, '\u0000'), SpecialChars.length);
        }
        return charSet;
    }

    private static int getFirstIndexOf(char[] array, char target) {
        for (int i = 0; i < array.length; i++) {
            if (array[i] == target) {
                return i; // Return the index of the first occurrence
            }
        }
        return -1; // Return -1 if the character is not found in the array
    }

    private static final char[] LowerCaseChars = {'q', 'w', 'e', 'r', 't', 'y', 'u', 'i', 'o', 'p', 'a', 's', 'd', 'f', 'g', 'h', 'j', 'k', 'l', 'z', 'x', 'c', 'v', 'b', 'n', 'm'};
    private static final char[] UpperCaseChars = {'Q', 'A', 'Z', 'W', 'S', 'X', 'E', 'D', 'C', 'R', 'F', 'V', 'T', 'G', 'B', 'Y', 'H', 'N', 'U', 'J', 'M', 'I', 'K', 'O', 'L', 'P'};
    private static final char[] NumericalChars = {'1', '0', '9', '2', '3', '8', '7', '4', '5', '6'};
    private static final char[] SpecialChars = {'~', '!', '@', '#', '$', '%', '^', '&', '*', '(', ')', '_', '+', '{', '}', '|', '\\', ']', '[', '=', '-', ';', '`', ':', '"', '<', '>', '?', '/', '.', ',', '\''};
    private static final char[] SpaceChar = {' '};
}
