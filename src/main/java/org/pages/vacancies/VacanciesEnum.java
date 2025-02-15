package org.pages.vacancies;

public enum VacanciesEnum {
    HEADER("#ДАВАЙСРАБОТАЕМСЯ");

    private final String text;

    VacanciesEnum(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }
}
