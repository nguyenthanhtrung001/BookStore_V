package management.login_google;

import com.google.gson.Gson;

public class UserGoogleDtoFactory {

    public static UserGoogleDto createUserGoogleDto(String id, String email, boolean verified_email, String name, String given_name, String family_name, String picture) {
        return new UserGoogleDto(id, email, verified_email, name, given_name, family_name, picture);
    }

    public static UserGoogleDto createUserGoogleDtoFromJson(String jsonResponse) {
        return new Gson().fromJson(jsonResponse, UserGoogleDto.class);
    }
}