package com.rubrica.controller;

import com.rubrica.Navigator;
import com.rubrica.model.Persona;
import com.rubrica.model.Rubrica;
import com.rubrica.persistenza.PersoneDAO;
import com.rubrica.view.MainView;
import com.rubrica.view.PersonaEditor;

public class MainViewController {

    MainView mainView;
    Rubrica rubrica;
    PersoneDAO personeDAO;
    Navigator nav;

    public MainViewController(Navigator nav) {
        this.nav = nav;
        this.mainView = new MainView();
        this.personeDAO = new PersoneDAO();

        this.rubrica = personeDAO.fetchPersoneOfUser(nav.getCurrentUser());

        initListeners();
        fillTable();
    }

    public void showView() {
        mainView.setVisible(true);
    }


    public void fillTable() {
        mainView.clearTable();
        for (Persona p : rubrica.getPersone()) {
            mainView.addPersonaToTable(
                    p.getNome(),
                    p.getCognome(),
                    p.getTelefono()
            );
        }
    }

    private void initListeners() {
        mainView.addNuovoButtonListener(e -> onNuovoClicked());
        mainView.addModificaButtonListener(e -> onModificaClicked());
        mainView.addEliminaButtonListener(e -> onEliminaClicked());
    }

    private void onEliminaClicked() {
        int selected = mainView.getSelectedRow();
        if (selected == -1) {
            mainView.showError("E' necessario selezionare una persona");
            return;
        }

        Persona p = rubrica.getPersone().get(selected);

        if (mainView.showDeleteConfirm(p.getNome(), p.getCognome())) {

            boolean deleted = personeDAO.deletePersona(p.getId());

            if (!deleted) {
                mainView.showError("Errore durante l'eliminazione della persona.");
                return;
            }

            rubrica = personeDAO.fetchPersoneOfUser(nav.getCurrentUser());
            fillTable();
        }

    }

    private void onModificaClicked() {
        int selected = mainView.getSelectedRow();
        if (selected == -1) {
            mainView.showError("E' necessario selezionare una persona");
            return;
        }

        Persona p = rubrica.getPersone().get(selected);

        PersonaEditor editor = new PersonaEditor(mainView);

        editor.setPersona(p);

        editor.addAnnullaButtonListener(e -> editor.dispose());

        editor.addSalvaButtonListener(e -> {
            Persona personaMod = buildPersonaFromEditor(editor);
            if (personaMod == null) {
                return;
            }

            boolean modified = personeDAO.updatePersona(personaMod, p.getId() );

            if (!modified) {
                mainView.showError("Errore nella modifica del contatto");
                return;
            }

            rubrica = personeDAO.fetchPersoneOfUser(nav.getCurrentUser());
            fillTable();
            editor.dispose();

        });


        editor.setVisible(true);

    }

    private void onNuovoClicked() {
        PersonaEditor editor = new PersonaEditor(mainView);

        editor.addAnnullaButtonListener(e -> {
            editor.dispose();
        });


        editor.addSalvaButtonListener(e -> {
            Persona personaMod = buildPersonaFromEditor(editor);
            if (personaMod == null) {
                return;
            }

            boolean created = personeDAO.createPersona(personaMod, nav.getCurrentUser().getId());

            if (!created) {
                mainView.showError("Errore inserimento nuovo contatto");
                return;
            }

            rubrica = personeDAO.fetchPersoneOfUser(nav.getCurrentUser());
            fillTable();
            editor.dispose();
        });

        editor.setVisible(true);

    }


    private Persona buildPersonaFromEditor(PersonaEditor editor) {
        String nome = editor.getNomeInput();
        String cognome = editor.getCognomeInput();
        String indirizzo = editor.getIndirizzoInput();
        String telefono = editor.getTelefonoInput();
        String etaText = editor.getEtaInput();

        if (nome.isBlank() || cognome.isBlank() || indirizzo.isBlank()
                || telefono.isBlank() || etaText.isBlank()) {
            editor.showError("Tutti i campi sono obbligatori.");
            return null;
        }

        if (!telefono.matches("\\d+")) {
            editor.showError("Il telefono deve contenere solo numeri.");
            return null;
        }

        if (!etaText.matches("\\d+")) {
            editor.showError("L'età deve contenere solo numeri.");
            return null;
        }

        int eta = Integer.parseInt(etaText);

        return new Persona(nome, cognome, indirizzo, telefono, eta);
    }

}
