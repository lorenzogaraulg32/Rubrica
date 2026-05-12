package com.rubrica.controller;

import com.rubrica.Navigator;
import com.rubrica.model.Utente;
import com.rubrica.persistenza.UsersDAO;
import com.rubrica.view.LoginView;

public class LoginController {

    LoginView loginView;
    Navigator nav;
    UsersDAO usersDAO;

    public LoginController(Navigator nav) {
        this.nav = nav;
        this.loginView = new LoginView();
        usersDAO = new UsersDAO();
        initListeners();
    }

    private void initListeners() {
        loginView.addLoginButtonListener(e -> {
            String username = loginView.getUsernameInput();
            String password = loginView.getPasswordInput();

            if (username.isBlank() || password.isBlank()) {
                loginView.showError("Inserire username e password.");
                return;
            }

            Utente user = usersDAO.fetchUsernameAndPassword(username, password);

            if (user == null) {
                loginView.showError("Credenziali errate");
                return;
            }
            nav.setCurrentUser(user);
            nav.showMainView();

        });
    }


    public void setNav(Navigator nav) {
        this.nav = nav;
    }

    public void showView() {
        loginView.setVisible(true);
    }

    public LoginView getView() {
        return this.loginView;
    }
}
