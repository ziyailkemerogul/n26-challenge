package com.n26.challenge.tests;

import com.n26.challenge.api.PetApiClient;
import com.n26.challenge.models.Pet;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class PetReadTests {
    private PetApiClient apiClient;

    @BeforeClass
    public void setup() {
        apiClient = new PetApiClient();
    }

    @Test
    public void testGetPetWithValidId() {
        // Create a pet to ensure it exists
        Pet pet = new Pet(200L, "Luna", "available");
        apiClient.createPet(pet);

        Response response = apiClient.getPet(200L);
        Assert.assertEquals(response.statusCode(), 200, "Expected status code 200");
        Pet retrievedPet = response.as(Pet.class);
        Assert.assertEquals(retrievedPet.getId(), pet.getId(), "Pet ID should match");
    }

    @Test
    public void testGetPetWithNonExistentId() {
        Response response = apiClient.getPet(9999L);
        Assert.assertEquals(response.statusCode(), 404, "Expected status code 404 for non-existent pet");
    }
}