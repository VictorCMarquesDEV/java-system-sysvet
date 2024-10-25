package com.sysvet.controller;

import java.io.IOException;
import com.sysvet.App;
import javafx.fxml.FXML;

public class LoginController {

    @FXML
    private void switchToInicio() throws IOException {
        App.setRoot("/view/inicio");
    }
    
}
