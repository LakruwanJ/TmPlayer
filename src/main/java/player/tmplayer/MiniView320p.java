package player.tmplayer;

import com.jfoenix.controls.JFXSlider;
import javafx.fxml.FXML;

import java.io.IOException;

public class MiniView320p extends player{


    @FXML
    private JFXSlider playbar;

    public void gotomain() throws IOException {
        playbar.getScene().getWindow().hide();
        openNew.onlyOpen("player.fxml");
    }

}
