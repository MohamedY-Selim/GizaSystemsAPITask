package io.swagger.petstore.config;

public enum EndPoint {
    ADD_PET("/pet"),
    GET_PET_BY_ID("/pet/{petId}"),
    FIND_BY_STATUS("/pet/findByStatus");

    private final String path;

    EndPoint(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }

    public String getPath(long petId) {
        return path.replace("{petId}", String.valueOf(petId)); // من غير BASE_PATH
    }
}