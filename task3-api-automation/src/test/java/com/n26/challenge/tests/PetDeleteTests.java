package com.n26.challenge.tests;

import com.n26.challenge.api.PetApiClient;
import com.n26.challenge.models.Pet;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import io.restassured.response.Response;

import java.util.Random;

public class PetDeleteTests {
    private PetApiClient petApiClient;
    private Pet pet;

    @BeforeMethod
    public void setUp() {
        petApiClient = new PetApiClient();
        pet = new Pet();
        // Generate a unique pet ID to avoid interference
        long petId = 400L + new Random().nextInt(10000); // Random ID between 400 and 10400
        pet.setId(petId);
        pet.setName("Max");
        pet.setStatus("available");

        // Clean up: Attempt to delete the pet if it exists
        petApiClient.deletePet(pet.getId());
    }

    @Test
    public void testDeletePetWithValidId() {
        System.out.println("testDeletePetWithValidId is running! Pet ID: " + pet.getId());
        // Step 1: Create a pet and verify
        Response createResponse = petApiClient.createPet(pet);
        Assert.assertEquals(createResponse.getStatusCode(), 200, "Failed to create pet");

        // Step 2: Delete the pet and verify
        Response deleteResponse = petApiClient.deletePet(pet.getId());
        Assert.assertEquals(deleteResponse.getStatusCode(), 200, "Failed to delete pet");

        // Step 3: Retry mechanism to verify the pet is deleted
        boolean isDeleted = false;
        int maxRetries = 10;
        int retryIntervalMs = 1000;

        for (int i = 0; i < maxRetries; i++) {
            Response getResponse = petApiClient.getPet(pet.getId());
            System.out.println("Retry " + (i + 1) + ": GET response status code: " + getResponse.getStatusCode());
            if (getResponse.getStatusCode() == 404) {
                isDeleted = true;
                break;
            }
            try {
                Thread.sleep(retryIntervalMs);
            } catch (InterruptedException e) {
                System.out.println("Retry interrupted: " + e.getMessage());
            }
        }

        Assert.assertTrue(isDeleted, "Expected pet to be deleted (404), but it still exists after " + maxRetries + " retries");
    }

    @Test
    public void testDebug() {
        System.out.println("Debug test in PetDeleteTests is running!");
        Assert.assertTrue(true, "This is a debug test to ensure PetDeleteTests is executed");
    }
}