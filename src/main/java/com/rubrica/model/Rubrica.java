package com.rubrica.model;

import java.util.ArrayList;
import java.util.List;

public class Rubrica {

    private final List<Persona> persone;

    public Rubrica() {
        this.persone = new ArrayList<>();
    }

    public void aggiungiPersona(Persona persona) {
        persone.add(persona);
    }

    public void eliminaPersona(Persona persona) {
        persone.remove(persona);
    }

    public List<Persona> getPersone() {
        return persone;
    }
}
