module ImagInLexis {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;
    requires json.simple;
    requires java.sql;

    opens com.xenakis  to javafx.fxml;

    exports com.xenakis.application;
    exports com.xenakis.screenController;
    exports com.xenakis.screenData;

}
