package com.rubrica;

import com.rubrica.controller.LoginController;
import com.rubrica.controller.MainViewController;
import com.rubrica.model.Utente;

public class Navigator {
    MainViewController mainViewController = null;
    LoginController loginController = null;

    Utente currentUser = null;

    public Navigator() {
    }

    public void showMainView() {
        if (mainViewController == null) {
            mainViewController = new MainViewController(this);
        }
        loginController.getView().dispose();
        mainViewController.showView();
    }

    public void showLoginView() {
        if (loginController == null) {
            loginController = new LoginController(this);
        }
        loginController.showView();
    }


    public Utente getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(Utente currentUser) {
        this.currentUser = currentUser;
    }
}
