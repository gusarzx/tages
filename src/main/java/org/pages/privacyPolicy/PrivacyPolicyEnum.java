package org.pages.privacyPolicy;

public enum PrivacyPolicyEnum {
    TITLE_POLICY("Политика в отношении обработки персональных данных"),
    POLICY_URL("https://tages.ru/policy");

    private final String text;

    PrivacyPolicyEnum(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }
}
