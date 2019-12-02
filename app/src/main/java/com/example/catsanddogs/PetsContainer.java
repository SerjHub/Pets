package com.example.catsanddogs;

public class PetsContainer {
    private Model pets;

    public PetsContainer(Model model) {
        pets = model;
    }

    public String[] getPets() {
        return pets.createPetList();
    }
}
