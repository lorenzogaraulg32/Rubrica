package com.rubrica.style;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.MouseAdapter;

public class AppStyle {

    //SCROLL
    public static final Color SCROLL_BG = new Color(18, 18, 18);
    public static final Color SCROLL_TXT = Color.WHITE;

    //TABLE
    public static final Color TABLE_BG = new Color(42, 42, 42);
    public static final Color TABLE_TXT = Color.WHITE;
    public static final Color TABLE_GRID_COLOR = Color.WHITE;

    //HEADER
    public static final Color HEADER_BG = new Color(24, 24, 24);
    public static final Color HEADER_TXT = Color.WHITE;

    //ENTRY
    public static final Color ENTRY_BG_SELECTED = Color.WHITE;
    public static final Color ENTRY_TXT_SELECTED = new Color(30, 30, 30);

    //TOOLBAR
    public static final Color TOOLBAR_BG = Color.WHITE;

    //BOTTONI TOOLBAR
    public static final Color TOOLBAR_BUTTON_BG = Color.WHITE;
    public static final Color TOOLBAR_BUTTON_TXT = new Color(45, 45, 45);
    public static final Color TOOLBAR_BUTTON_HOVER = new Color(60, 60, 60);
    public static final Color TOOLBAR_BUTTON_HOVER_TXT = Color.WHITE;

    //LOGIN
    public static final Color LOGIN_BG = new Color(18, 18, 18);
    public static final Color LOGIN_BUTTON_BG = Color.WHITE;
    public static final Color LOGIN_BUTTON_TXT = new Color(45, 45, 45);
    public static final Color LOGIN_BUTTON_HOVER = new Color(60, 60, 60);
    public static final Color LOGIN_BUTTON_HOVER_TXT = Color.WHITE;

    private AppStyle() {
    }


    /*
      LOGIN
    */
    public static void styleLoginPanel(JPanel panel) {
        panel.setBackground(LOGIN_BG);
        panel.setBorder(BorderFactory.createEmptyBorder(35, 45, 35, 45));
    }

    public static void styleLoginLabel(JLabel label) {
        label.setForeground(Color.WHITE);
        label.setFont(new Font(label.getFont().getFontName(), Font.BOLD, 15));
        label.setAlignmentX(Component.LEFT_ALIGNMENT);
    }

    public static void styleLoginButton(JButton button) {
        button.setBackground(LOGIN_BUTTON_BG);
        button.setForeground(LOGIN_BUTTON_TXT);
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setOpaque(true);

        button.setFont(new Font(button.getFont().getFontName(), Font.BOLD, 15));

        button.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
        button.setPreferredSize(new Dimension(280, 40));
        button.setAlignmentX(Component.LEFT_ALIGNMENT);

        button.setCursor(new Cursor(Cursor.HAND_CURSOR));

        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent e) {
                button.setBackground(LOGIN_BUTTON_HOVER);
                button.setForeground(LOGIN_BUTTON_HOVER_TXT);
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent e) {
                button.setBackground(LOGIN_BUTTON_BG);
                button.setForeground(LOGIN_BUTTON_TXT);
            }
        });

    }

    public static void styleLoginField(JTextField field) {
        field.setMaximumSize(new Dimension(Integer.MAX_VALUE, 38));
        field.setPreferredSize(new Dimension(280, 38));


        field.setFont(new Font(field.getFont().getFontName(), Font.PLAIN, 15));


        field.setAlignmentX(Component.LEFT_ALIGNMENT);
    }



     /*
       TABELLA
     */

    public static void styleTable(JTable table, JScrollPane scroll) {
        scroll.setBackground(SCROLL_BG);
        scroll.setForeground(SCROLL_TXT);

        table.setBackground(TABLE_BG);
        table.setForeground(TABLE_TXT);

        table.setSelectionBackground(ENTRY_BG_SELECTED);
        table.setSelectionForeground(ENTRY_TXT_SELECTED);

        table.setGridColor(TABLE_GRID_COLOR);
        table.setRowHeight(36);
        table.setFillsViewportHeight(true);

        table.setDefaultRenderer(Object.class, createCellRenderer());


        JTableHeader header = table.getTableHeader();

        header.setBackground(HEADER_BG);
        header.setForeground(HEADER_TXT);
        header.setPreferredSize(new Dimension(header.getPreferredSize().width, 40));
        header.setFont(new Font(header.getFont().getFontName(), Font.BOLD, 18));

    }

    private static DefaultTableCellRenderer createCellRenderer() {
        return new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(
                    JTable table,
                    Object value,
                    boolean isSelected,
                    boolean hasFocus,
                    int row,
                    int column
            ) {
                Component component = super.getTableCellRendererComponent(
                        table,
                        value,
                        isSelected,
                        hasFocus,
                        row,
                        column
                );

                setBorder(BorderFactory.createEmptyBorder(0, 18, 0, 0));
                setFont(new Font(getFont().getFontName(), Font.PLAIN, 15));

                return component;
            }
        };
    }



    /*
      TOOLBAR
    */

    public static void styleToolbar(JToolBar toolBar) {
        toolBar.setBackground(TOOLBAR_BG);
        toolBar.setFloatable(false);
        toolBar.setBorderPainted(false);
        toolBar.setOpaque(true);
    }

    public static void styleToolbarButton(JButton button) {
        button.setBackground(TOOLBAR_BUTTON_BG);
        button.setForeground(TOOLBAR_BUTTON_TXT);
        button.setFont(new Font(button.getFont().getFontName(), Font.PLAIN, 15));

        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setOpaque(true);

        button.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(180, 180, 180)),
                BorderFactory.createEmptyBorder(8, 16, 8, 16)
        ));


        button.setPreferredSize(new Dimension(110, 40));
        button.setMinimumSize(new Dimension(110, 40));
        button.setMaximumSize(new Dimension(110, 40));

        button.setCursor(new Cursor(Cursor.HAND_CURSOR));

        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent e) {
                button.setBackground(TOOLBAR_BUTTON_HOVER);
                button.setForeground(TOOLBAR_BUTTON_HOVER_TXT);
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent e) {
                button.setBackground(TOOLBAR_BUTTON_BG);
                button.setForeground(TOOLBAR_BUTTON_TXT);
            }
        });
    }

    public static Component createToolbarSeparator() {
        JPanel separator = new JPanel();
        separator.setBackground(Color.BLACK);
        separator.setPreferredSize(new Dimension(2, 40));
        separator.setMaximumSize(new Dimension(2, 40));
        return separator;
    }

}
