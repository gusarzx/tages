package org.pages.blog;

public enum BlogEnum {
    HEADER("Блог");

    private final String text;

    BlogEnum(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }
}
