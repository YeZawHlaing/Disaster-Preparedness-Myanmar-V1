package com.backend.v1.config.geocodingConfig;


import com.backend.v1.model.Coordinates;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


@Service
public class GeocodingServiceImpl implements GeocodingService {
    private static final String NOMINATIM_URL = "https://nominatim.openstreetmap.org/search?q=%s&format=json&limit=1";

    @Override
    public Coordinates getCoordinatesFromLocation(String address) {
        try {
            String url = String.format(NOMINATIM_URL, address.replace(" ", "+"));
            RestTemplate restTemplate = new RestTemplate();

            HttpHeaders headers = new HttpHeaders();
            headers.set("User-Agent", "DisasterPreparednessApp (minthihaa408@email.com)"); // Change email
            HttpEntity<String> entity = new HttpEntity<>(headers);

            ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);

            // 🔍 Debugging: Print the API response
            System.out.println("🌍 Nominatim API Response: " + response.getBody());

            // Parse JSON response
            JSONArray jsonArray = new JSONArray(response.getBody());
            if (jsonArray.length() > 0) {
                JSONObject location = jsonArray.getJSONObject(0);
                double lat = location.optDouble("lat", 0.0);
                double lon = location.optDouble("lon", 0.0);

                if (lat != 0.0 && lon != 0.0) {
                    System.out.println("✅ Coordinates found: " + lat + ", " + lon);
                    return new Coordinates(lat, lon);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();  // Print any errors
        }

        System.out.println("⚠️ Invalid address. Unable to fetch coordinates.");
        return null;
    }




}



