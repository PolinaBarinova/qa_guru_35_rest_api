package models.lombok;

import lombok.Data;

import java.util.List;

@Data
public class ListUsersResponseModel {
    int page;
    int per_page;
    int total;
    int total_pages;
    List<UserResponseModels> data;

    private Support support;

    @lombok.Data
    public static class UserResponseModels {
        private Integer id;
        private String email;
        private String first_name;
        private String last_name;
        private String avatar;
    }

    @lombok.Data
    public static class Support {
        private String url;
        private String text;
    }

}
