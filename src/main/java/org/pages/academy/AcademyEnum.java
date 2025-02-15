package org.pages.academy;

public enum AcademyEnum {
    HEADER("TAGES ACADEMY");

    private final String text;

    AcademyEnum(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }
}


