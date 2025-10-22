package ru.netology.i18n;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.netology.entity.Country;

class LocalizationServiceImplTest {

    // Rus_Test
    @Test
    void locale_shouldReturnRussianText_forRussia() {
        // given:
        LocalizationService localizationService = new LocalizationServiceImpl();
        String expectedText = "Добро пожаловать";

        // when:
        String resultText = localizationService.locale(Country.RUSSIA);

        // then:
        Assertions.assertEquals(expectedText, resultText);
    }

    // Eng_Test
    @Test
    void locale_shouldReturnEnglishText_forNotRussia() {
        // given:
        LocalizationService localizationService = new LocalizationServiceImpl();
        String expectedText = "Welcome";

        // when:
        String resultText = localizationService.locale(Country.USA);

        // then:
        Assertions.assertEquals(expectedText, resultText);
    }
}
