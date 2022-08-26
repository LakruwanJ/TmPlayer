package player.tmplayer;

import com.jfoenix.controls.JFXSlider;
import javafx.fxml.FXML;
import javafx.scene.media.MediaView;

import java.io.IOException;
import java.sql.SQLException;

public class MiniView240p extends player{

    @FXML
    private MediaView play;

    @FXML
    private JFXSlider playbar;

    public MiniView240p() throws SQLException {
    }

    //

    public void gotomain() throws IOException {
        playbar.getScene().getWindow().hide();
        openNew.onlyOpen("player.fxml");
    }



}
