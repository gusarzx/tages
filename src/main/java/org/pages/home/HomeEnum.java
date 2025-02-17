package org.pages.home;

import org.interfaces.HasText;

public enum HomeEnum implements HasText {
    URL("https://tages.ru/"),
    TITLE("TAGES: цифровизация бизнес-процессов. Аналитики, интеграция, разработка ПО и мобильных приложений"),
    TITLE_TEXT("Скорее всего, вам порекомендовали нас. Если вы здесь, то вам нужно качественное технологическое решение."),
    NUMBER("+7 (495) 640-23-94"),
    MAIL("jump@tages.ru"),
    PRESS_NUMBER("+7 (915) 364-61-26"),
    PRESS_MAIL("pr@tages.ru"),
    COMMENT("Комментарий"),
    TITLE_CONTACT("Контактная информация"),
    SUB_TITLE_CONTACT("Напишите нам, и мы поможем Вам в решении бизнес задач."),
    FORM_URL("https://tages.ru/#form");

    private final String text;

    HomeEnum(String text) {
        this.text = text;
    }

    @Override
    public String getText() {
        return text;
    }


    public enum WhatWeDo {
        PROGRAM("Разработка программного обеспечения"),
        INTERFACE("Разработка пользовательских интерфейсов"),
        PIM("Построение процессов PIM / PXM"),
        API("API management"),
        DEVOPS("DevOps"),
        SUPER_API("Разработка SuperApp"),
        INTEGRATION("Интеграционные решения"),
        MICRO_SERVICE("Разработка Enterprise-решений на микросервисной архитектуре"),
        MOBILE("Разработка мобильных сервисов"),
        QA("Quality Assurance"),
        LOW_CODE("Композитная low code платформа"),
        ARCHITECTURE("Проектирование архитектуры"),
        HIGH_LOAD("Обеспечение бесперебойной работы приложений и инфраструктуры"),
        REESTR("Разработка и внедрение решений на базе распределенных реестров"),
        AUTO("Автоматизация жизненно важных бизнес-процессов");

        private final String text;

        WhatWeDo(String text) {
            this.text = text;
        }

        public String getText() {
            return text;
        }
    }

    public enum Partner {
        ROLF("Рольф"),
        MEDSI("Медси"),
        BOARD("omniboard360"),
        INGO("Ингосстрах"),
        M_VIDEO("М.ВидеоЭльдорадо"),
        LEROY("Леруа Мерлен"),
        TECHNO("Технониколь");

        private final String text;

        Partner(String text) {
            this.text = text;
        }

        public String getText() {
            return text;
        }
    }

    public enum LinkSocialNetwork {
        TG("https://t.me/tagesru"),
        VK("https://vk.com/tagesru"),
        YOU_TUBE("https://www.youtube.com/channel/UCBK5-lZpfKeu_yjH30DpvSQ");

        private final String text;

        LinkSocialNetwork(String text) {
            this.text = text;
        }

        public String getText() {
            return text;
        }
    }

    public enum ContactPlaceHolder {
        NAME("Имя*"),
        NUMBER("Телефон*"),
        COMPANY("Компания"),
        MAIL("Почта*");

        private final String text;

        ContactPlaceHolder(String text) {
            this.text = text;
        }

        public String getText() {
            return text;
        }
    }
}
