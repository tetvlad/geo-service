package ru.netology.sender;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import ru.netology.entity.Country;
import ru.netology.entity.Location;
import ru.netology.geo.GeoServiceImpl;
import ru.netology.i18n.LocalizationServiceImpl;


import java.util.HashMap;
import java.util.Map;

class MessageSenderImplTest {

    private GeoServiceImpl geoService;
    private LocalizationServiceImpl localizationService;
    private MessageSenderImpl messageSender;

    // Заглушки
    @BeforeEach
    void setUp() {
        geoService = Mockito.mock(GeoServiceImpl.class);
        localizationService = Mockito.mock(LocalizationServiceImpl.class);
        messageSender = new MessageSenderImpl(geoService, localizationService);
    }

    // Тест #1: RUSSIA IP
    @Test
    void testSend_whenIpIsRussian_shouldReturnRussianText() {

        //String russianIp = "172.00.00.00";
        String russianIp = "172.123.12.19";
        Location russianLocation = new Location("Moscow", Country.RUSSIA, null, 0);

        Mockito.when(geoService.byIp(russianIp)).thenReturn(russianLocation);
        Mockito.when(localizationService.locale(Country.RUSSIA)).thenReturn("Добро пожаловать");

        Map<String, String> headers = new HashMap<>();
        headers.put(MessageSenderImpl.IP_ADDRESS_HEADER, russianIp);


        String result = messageSender.send(headers);

        // Итог
        Assertions.assertEquals("Добро пожаловать", result);
    }

    // Тест #2: USA IP
    @Test
    void testSend_whenIpIsFromUsa_shouldReturnEnglishText() {

        String usaIp = "96.44.183.149";
        Location usaLocation = new Location("New York", Country.USA, null, 0);

        Mockito.when(geoService.byIp(usaIp)).thenReturn(usaLocation);
        Mockito.when(localizationService.locale(Country.USA)).thenReturn("Welcome");

        Map<String, String> headers = new HashMap<>();
        headers.put(MessageSenderImpl.IP_ADDRESS_HEADER, usaIp);


        String result = messageSender.send(headers);

        // Итог
        Assertions.assertEquals("Welcome", result);
    }

    // Тест 3: Germany IP
    @Test
    void testSend_whenIpIsFromGermany_shouldReturnEnglishText() {

        String germanIp = "195.25.201.10";
        Location germanLocation = new Location("Berlin", Country.GERMANY, null, 0);

        // eoService
        Mockito.when(geoService.byIp(germanIp)).thenReturn(germanLocation);

        // localizationService
        Mockito.when(localizationService.locale(Country.USA)).thenReturn("Welcome");

        Map<String, String> headers = new HashMap<>();
        headers.put(MessageSenderImpl.IP_ADDRESS_HEADER, germanIp);


        String result = messageSender.send(headers);

        // Итог
        Assertions.assertEquals("Welcome", result);
    }

    // Тест №4: localhost
    @Test
    void testSend_whenIpIsLocalhost_shouldReturnEnglishText() {

        String localIp = "127.0.0.1";
        // null
        Location localLocation = new Location(null, null, null, 0);

        Mockito.when(geoService.byIp(localIp)).thenReturn(localLocation);

        Mockito.when(localizationService.locale(Country.USA)).thenReturn("Welcome");

        Map<String, String> headers = new HashMap<>();
        headers.put(MessageSenderImpl.IP_ADDRESS_HEADER, localIp);


        String result = messageSender.send(headers);

        // Итог
        Assertions.assertEquals("Welcome", result);
    }
}