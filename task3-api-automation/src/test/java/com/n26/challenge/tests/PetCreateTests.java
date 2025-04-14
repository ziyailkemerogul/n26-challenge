package com.n26.challenge.tests;

import com.n26.challenge.api.PetApiClient;
import com.n26.challenge.models.Pet;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class PetCreateTests {
    private PetApiClient apiClient;

    @BeforeClass
    public void setup() {
        apiClient = new PetApiClient();
    }

    @Test
    public void testCreatePetWithValidData() {
        Pet pet = new Pet(100L, "Buddy", "available");
        Response response = apiClient.createPet(pet);

        Assert.assertEquals(response.statusCode(), 200, "Expected status code 200");
        Pet createdPet = response.as(Pet.class);
        Assert.assertEquals(createdPet.getId(), pet.getId(), "Pet ID should match");
        Assert.assertEquals(createdPet.getName(), pet.getName(), "Pet name should match");
        Assert.assertEquals(createdPet.getStatus(), pet.getStatus(), "Pet status should match");
    }

    @Test
    public void testCreatePetWithMissingName() {
        Pet pet = new Pet(101L, null, "available");
        Response response = apiClient.createPet(pet);

        Assert.assertEquals(response.statusCode(), 200, "Expected status code 200 as API accepts null name");
        Pet createdPet = response.as(Pet.class);
        Assert.assertNull(createdPet.getName(), "Pet name should be null");
    }

    @Test
    public void testCreatePetWithInvalidStatus() {
        Pet pet = new Pet(102L, "Max", "invalid_status");
        Response response = apiClient.createPet(pet);

        Assert.assertEquals(response.statusCode(), 200, "Expected status code 200 as API accepts invalid status");
        Pet createdPet = response.as(Pet.class);
        Assert.assertEquals(createdPet.getStatus(), "invalid_status", "Pet status should match the invalid status");
    }
}