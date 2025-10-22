package ru.netology.sender;

import java.util.Map;

import ru.netology.entity.Country;
import ru.netology.entity.Location;
import ru.netology.geo.GeoService;
import ru.netology.i18n.LocalizationService;

public class MessageSenderImpl implements MessageSender {

    public static final String IP_ADDRESS_HEADER = "x-real-ip";
    private final GeoService geoService;

    private final LocalizationService localizationService;

    public MessageSenderImpl(GeoService geoService, LocalizationService localizationService) {
        this.geoService = geoService;
        this.localizationService = localizationService;
    }

    public String send(Map<String, String> headers) {
        String ipAddress = headers.get(IP_ADDRESS_HEADER);

        // Получаем локацию. Метод byIp никогда не должен возвращать null по контракту.
        Location location = geoService.byIp(ipAddress);

        // Проверяем, что страна в локации - Россия.
        // Эта проверка безопасно работает, даже если location.getCountry() вернет null.
        if (Country.RUSSIA.equals(location.getCountry())) {
            return localizationService.locale(Country.RUSSIA);
        }

        // Во всех остальных случаях (США, Германия, localhost/null) возвращаем английский текст.
        return localizationService.locale(Country.USA);
    }
}
