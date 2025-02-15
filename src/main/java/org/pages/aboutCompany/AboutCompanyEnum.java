package org.pages.aboutCompany;

public enum AboutCompanyEnum {
    HEADER("Проектирование, разработка и поддержка индивидуальных решений для цифровизации бизнеса.");

    private final String text;

    AboutCompanyEnum(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }
}
