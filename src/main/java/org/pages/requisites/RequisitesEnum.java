package org.pages.requisites;

public enum RequisitesEnum {
    REQUISITES_URL("https://tages.ru/requisites"),
    TITLE_REQUISITES("Реквизиты"),
    SUB_TITLE_REQUISITES("Карточка предприятия ООО “Тагес Джамп”");

    private final String text;

    RequisitesEnum(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }
}
