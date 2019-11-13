package com.xenakis.screenController;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;

public class TestActionsController {

    @FXML
    protected Button submitButton;

    @FXML
    protected Button nextButton;

    private QuestionScreenController questionScreenController;

    private final BooleanProperty submitButtonValue = new SimpleBooleanProperty();

    public BooleanProperty submitButtonProperty() {
        return submitButtonValue;
    }

    public void clicked(MouseEvent e){
        submitButtonValue.set(true);
    }

    public void setParentController(QuestionScreenController questionScreenController) {
        this.questionScreenController = questionScreenController;
    }

}
