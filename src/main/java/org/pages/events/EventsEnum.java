package org.pages.events;

public enum EventsEnum {
    HEADER("TAGES МЕРОПРИЯТИЯ");

    private final String text;

    EventsEnum(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }
}
