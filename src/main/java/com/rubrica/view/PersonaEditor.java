package com.rubrica.view;

import com.rubrica.model.Persona;
import com.rubrica.style.AppStyle;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

import static com.rubrica.style.AppStyle.createToolbarSeparator;

public class PersonaEditor extends JDialog {

    private JTextField nomeField;
    private JTextField cognomeField;
    private JTextField indirizzoField;
    private JTextField telefonoField;
    private JTextField etaField;


    private JToolBar toolbar;
    private JButton salvaButton;
    private JButton annullaButton;


    public PersonaEditor(JFrame parent) {
        super(parent, "Editor Persona", true);

        setSize(400, 300);
        setLocationRelativeTo(parent);
        setLayout(new BorderLayout());

        initForm();
        initToolbar();
    }

    private void initToolbar() {
        toolbar = new JToolBar();


        salvaButton = new JButton("Salva");
        annullaButton = new JButton("Annulla");

        AppStyle.styleToolbarButton(salvaButton);
        AppStyle.styleToolbarButton(annullaButton);

        toolbar.add(createToolbarSeparator());
        toolbar.add(salvaButton);
        toolbar.add(createToolbarSeparator());
        toolbar.add(annullaButton);
        toolbar.add(createToolbarSeparator());

        AppStyle.styleToolbar(toolbar);

        add(toolbar, BorderLayout.NORTH);

    }

    private void initForm() {
        JPanel formPanel = new JPanel(new GridLayout(5, 2, 10, 10));
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        nomeField = new JTextField();
        cognomeField = new JTextField();
        indirizzoField = new JTextField();
        telefonoField = new JTextField();
        etaField = new JTextField();

        formPanel.add(new JLabel("Nome:"));
        formPanel.add(nomeField);

        formPanel.add(new JLabel("Cognome:"));
        formPanel.add(cognomeField);

        formPanel.add(new JLabel("Indirizzo:"));
        formPanel.add(indirizzoField);

        formPanel.add(new JLabel("Telefono:"));
        formPanel.add(telefonoField);

        formPanel.add(new JLabel("Età:"));
        formPanel.add(etaField);

        add(formPanel, BorderLayout.CENTER);
    }

    private void initButtons() {
        JPanel buttonPanel = new JPanel();


        buttonPanel.add(salvaButton);
        buttonPanel.add(annullaButton);

        add(buttonPanel, BorderLayout.SOUTH);

    }

    public void setPersona(Persona persona) {
        nomeField.setText(persona.getNome());
        cognomeField.setText(persona.getCognome());
        indirizzoField.setText(persona.getIndirizzo());
        telefonoField.setText(persona.getTelefono());
        etaField.setText(String.valueOf(persona.getEta()));
    }


    public void addSalvaButtonListener(ActionListener listener) {
        salvaButton.addActionListener(listener);
    }

    public void addAnnullaButtonListener(ActionListener listener) {
        annullaButton.addActionListener(listener);
    }

    public void showError(String message) {
        JOptionPane.showMessageDialog(
                this,
                message,
                "Errore",
                JOptionPane.ERROR_MESSAGE
        );
    }


    public String getNomeInput() {
        return nomeField.getText().trim();
    }

    public String getCognomeInput() {
        return cognomeField.getText().trim();
    }

    public String getIndirizzoInput() {
        return indirizzoField.getText().trim();
    }

    public String getTelefonoInput() {
        return telefonoField.getText().trim();
    }

    public String getEtaInput() {
        return etaField.getText().trim();
    }


}
