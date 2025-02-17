package org.pages.anotherWebSite;

import org.interfaces.HasText;

public enum AnotherWebSiteEnum implements HasText {
    URL_ROLF("https://www.rolf.ru/"),
    TITLE_ROLF("РОЛЬФ - крупнейший автомобильный дилер России"),
    TEXT_ROLF("РОЛЬФ"),

    URL_MEDSI("https://medsi.ru/"),
    TITLE_MEDSI("Сеть клиник и медицинских центров МЕДСИ - Клинико-диагностические центры, детские клиники и клинические больницы - Официальный сайт"),
    TEXT_MEDSI("МЕДСИ"),

    URL_OMNI("https://navigator.sk.ru/orn/1123852"),
    TITLE_OMNI("ОМНИ360 | Участник проекта «Сколково»"),
    TEXT_OMNI("ОМНИ360"),

    URL_INGO("https://www.ingos.ru/"),
    TITLE_INGO("Страхование от СПАО «Ингосстрах» ✅ – официальный сайт крупнейшей страховой компании России"),
    TEXT_INGO("Ингосстрах"),

    URL_MVideo("https://www.mvideoeldorado.ru/ru/"),
    TITLE_MVideo("Главная | Группа «М.Видео — Эльдорадо»"),
    TEXT_MVideo("М.Видео"),

    URL_LEMANA("https://lemanapro.ru/"),
    TITLE_LEMANA("Лемана ПРО (Леруа Мерлен) – товары для строительства и ремонта в Москве по низким ценам"),
    TEXT_LEMANA("Лемана"),

    URL_TECHNO("https://www.tn.ru/"),
    TITLE_TECHNO("ТЕХНОНИКОЛЬ - материалы для кровли, гидроизоляции и теплоизоляции"),
    TEXT_TECHNO("ТЕХНОНИКОЛЬ"),

    URL_TG("https://t.me/tagesru"),
    TITLE_TG("Telegram: Contact @tagesru"),
    TEXT_TG("TAGES"),

    URL_VK("https://vk.com/tagesru"),
    TITLE_VK("TAGES | ВКонтакте"),
    TEXT_VK("TAGES"),

    URL_YOUTUBE("https://www.youtube.com/channel/UCBK5-lZpfKeu_yjH30DpvSQ"),
    TITLE_YOUTUBE("www.youtube.com"),
    TEXT_YOUTUBE("");

    private final String text;

    AnotherWebSiteEnum(String text) {
        this.text = text;
    }

    @Override
    public String getText() {
        return text;
    }
}
