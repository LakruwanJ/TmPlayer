module player.tmplayer {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.jfoenix;
    requires javafx.media;
    requires java.sql;


    opens player.tmplayer to javafx.fxml;
    exports player.tmplayer;
}