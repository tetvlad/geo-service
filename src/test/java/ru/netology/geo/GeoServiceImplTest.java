package ru.netology.geo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.netology.entity.Country;
import ru.netology.entity.Location;

class GeoServiceImplTest {

    // Локация по Ip_Rus
    @Test
    void byIp_shouldReturnMoscow_forRussianIp() {
        // given:
        GeoService geoService = new GeoServiceImpl();
        String russianIp = "172.0.32.11";

        // when:
        Location result = geoService.byIp(russianIp);

        // then:
        Assertions.assertEquals(Country.RUSSIA, result.getCountry());
    }

    // Локация по Ip_USA
    @Test
    void byIp_shouldReturnNewYork_forUsaIp() {
        // given:
        GeoService geoService = new GeoServiceImpl();
        String usaIp = "96.44.183.149";

        // when:
        Location result = geoService.byIp(usaIp);

        // then:
        Assertions.assertEquals(Country.USA, result.getCountry());
    }
}