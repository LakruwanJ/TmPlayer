package player.tmplayer;

import com.jfoenix.controls.JFXSlider;
import javafx.fxml.FXML;

import java.io.IOException;
import java.sql.SQLException;

public class MiniView320p extends player{


    @FXML
    private JFXSlider playbar;

    public MiniView320p() throws SQLException {
    }

    public void gotomain() throws IOException {
        playbar.getScene().getWindow().hide();
        openNew.onlyOpen("player.fxml");
    }

}
