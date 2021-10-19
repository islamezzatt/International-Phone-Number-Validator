package com.example.internationalphonenumbervalidator.utils;

import java.util.regex.Pattern;

import com.example.internationalphonenumbervalidator.model.PhoneNumber;

public class PhoneNumberUtil {

    private static final String FRONT_ROUND_BRACKET = "(";
    private static final String BACK_ROUND_BRACKET = ")";
    private static final String CAMEROON_NUMBER_REGEX = "\\(237\\)\\ ?[2368]\\d{7,8}$";
    private static final String ETHIOPIA_NUMBER_REGEX = "\\(251\\)\\ ?[1-59]\\d{8}$";
    private static final String MOROCCO_NUMBER_REGEX = "\\(212\\)\\ ?[5-9]\\d{8}$";
    private static final String MOZAMBIQUE_NUMBER_REGEX = "\\(258\\)\\ ?[28]\\d{7,8}$";
    private static final String UGANDA_NUMBER_REGEX = "\\(256\\)\\ ?\\d{9}$";

    private PhoneNumberUtil() {
        throw new IllegalStateException("Utility Class");
    }

    public static PhoneNumber splitCountryCodeAndNumber(String phoneNumber) {
        if (phoneNumber == null || phoneNumber.isBlank()) {
            return null;
        }
        if (!phoneNumber.contains(FRONT_ROUND_BRACKET) || !phoneNumber.contains(BACK_ROUND_BRACKET)
                || !phoneNumber.contains(" ")) {
            return null;
        }
        String countryCode = phoneNumber.substring(phoneNumber.indexOf(FRONT_ROUND_BRACKET) + 1,
                phoneNumber.indexOf(BACK_ROUND_BRACKET));
        String localNumber = phoneNumber.substring(phoneNumber.lastIndexOf(" ") + 1);
        return new PhoneNumber(countryCode, localNumber);
    }

    public static boolean isValidPhoneNumber(String phoneNumber) {
        if (phoneNumber == null || phoneNumber.isBlank()) {
            return false;
        }
        String patterns = String.join("|", CAMEROON_NUMBER_REGEX, ETHIOPIA_NUMBER_REGEX, MOROCCO_NUMBER_REGEX,
                MOZAMBIQUE_NUMBER_REGEX, UGANDA_NUMBER_REGEX);
        Pattern pattern = Pattern.compile(patterns);
        return pattern.matcher(phoneNumber).matches();
    }

}
