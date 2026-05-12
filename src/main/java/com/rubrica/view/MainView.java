package com.rubrica.view;

import com.rubrica.style.AppStyle;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;

import static com.rubrica.style.AppStyle.*;


public class MainView extends JFrame {

    //campi tabella
    private JScrollPane contactsScroll;
    private JTable contactsTable;
    private final String[] columnNames = {"Nome", "Cognome", "Telefono"};
    private final DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);


    //toolbar
    private JToolBar toolbar;
    private JButton newButton;
    private JButton modButton;
    private JButton delButton;


    public MainView() {

        setTitle("Rubrica");
        setSize(600, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        initTable();
        initToolbar();
    }


    /*
      TOOLBAR
    */
    private void initToolbar() {
        toolbar = new JToolBar();

        newButton = new JButton("Nuovo");
        modButton = new JButton("Modifica");
        delButton = new JButton("Elimina");

        styleToolbarButton(newButton);
        styleToolbarButton(modButton);
        styleToolbarButton(delButton);

        toolbar.add(createToolbarSeparator());
        toolbar.add(newButton);
        toolbar.add(createToolbarSeparator());
        toolbar.add(modButton);
        toolbar.add(createToolbarSeparator());
        toolbar.add(delButton);
        toolbar.add(createToolbarSeparator());

        styleToolbar(toolbar);

        add(toolbar, BorderLayout.NORTH);
    }


    public void addNuovoButtonListener(ActionListener listener) {
        newButton.addActionListener(listener);
    }

    public void addModificaButtonListener(ActionListener listener) {
        modButton.addActionListener(listener);
    }

    public void addEliminaButtonListener(ActionListener listener) {
        delButton.addActionListener(listener);
    }

    /*
      TABELLA
    */
    private void initTable() {
        contactsTable = new JTable(tableModel);
        contactsScroll = new JScrollPane(contactsTable);
        add(contactsScroll, BorderLayout.CENTER);
        AppStyle.styleTable(contactsTable, contactsScroll);
        contactsTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        contactsTable.getTableHeader().setReorderingAllowed(false);
    }

    public void clearTable() {
        tableModel.setRowCount(0);
    }

    public void addPersonaToTable(String nome, String cognome, String telefono) {
        tableModel.addRow(new Object[]{nome, cognome, telefono});
    }

    public int getSelectedRow() {
        return contactsTable.getSelectedRow();
    }


     /*
       ALERT
     */

    public void showError(String message) {
        JOptionPane.showMessageDialog(
                this,
                message,
                "Errore",
                JOptionPane.ERROR_MESSAGE
        );
    }

    public boolean showDeleteConfirm(String nome, String cognome) {
        int result = JOptionPane.showConfirmDialog(
                this,
                "Eliminare la persona " + nome + " " + cognome + "?",
                "Conferma eliminazione",
                JOptionPane.YES_NO_OPTION
        );

        return result == JOptionPane.YES_OPTION;
    }

}
