package com.n26.challenge.api;

import com.n26.challenge.models.Pet;
import com.n26.challenge.utils.Config;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class PetApiClient {

    public PetApiClient() {
    }

    public Response createPet(Pet pet) {
        return given()
                .contentType("application/json")
                .body(pet)
                .post(Config.BASE_URL + "/pet");
    }

    public Response getPet(Long petId) {
        return given()
                .accept("application/json, application/xml")
                .get(Config.BASE_URL + "/pet/" + petId);
    }

    public Response updatePet(Pet pet) {
        return given()
                .contentType("application/json")
                .body(pet)
                .put(Config.BASE_URL + "/pet");
    }

    public Response deletePet(Long petId) {
        return given()
                .accept("application/json, application/xml")
                .delete(Config.BASE_URL + "/pet/" + petId);
    }
}