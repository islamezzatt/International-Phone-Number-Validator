package com.example.internationalphonenumbervalidator.enums;

public enum CountryCodeEnum {
    CAMEROON("Cameroon", "237"), ETHIOPIA("Ethiopia", "251"), MOROCCO("Morocco", "212"),
    MOZAMBIQUE("Mozambique", "258"), UGANDA("Uganda", "256");

    private String countryName;
    private String countryCode;

    CountryCodeEnum(String countryName, String countryCode) {
        this.countryName = countryName;
        this.countryCode = countryCode;
    }

    public String getCountryName() {
        return countryName;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public static CountryCodeEnum findByCountryCode(String countryCode) {
        for (CountryCodeEnum e : values()) {
            if (e.countryCode.equals(countryCode)) {
                return e;
            }
        }
        return null;
    }
}
