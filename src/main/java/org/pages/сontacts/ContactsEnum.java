package org.pages.сontacts;

public enum ContactsEnum {
    HEADER("Контакты");

    private final String text;

    ContactsEnum(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }
}
