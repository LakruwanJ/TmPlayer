package player.tmplayer;

import javafx.beans.property.SimpleStringProperty;

public class TableData1 {

    private SimpleStringProperty tr1 = new SimpleStringProperty();
    private SimpleStringProperty tr2 = new SimpleStringProperty();
    private SimpleStringProperty tr3 = new SimpleStringProperty();
    private SimpleStringProperty tr4 = new SimpleStringProperty();
    private SimpleStringProperty tr5 = new SimpleStringProperty();

    public TableData1(String tr1, String tr2, String tr3, String tr4, String tr5) {
        this.tr1 = new SimpleStringProperty(tr1);
        this.tr2 = new SimpleStringProperty(tr2);
        this.tr3 = new SimpleStringProperty(tr3);
        this.tr4 = new SimpleStringProperty(tr4);
        this.tr5 = new SimpleStringProperty(tr5);
    }

    public String getTr1() {
        return tr1.get();
    }

    public SimpleStringProperty tr1Property() {
        return tr1;
    }

    public String getTr2() {
        return tr2.get();
    }

    public SimpleStringProperty tr2Property() {
        return tr2;
    }

    public String getTr3() {
        return tr3.get();
    }

    public SimpleStringProperty tr3Property() {
        return tr3;
    }

    public String getTr4() {
        return tr4.get();
    }

    public SimpleStringProperty tr4Property() {
        return tr4;
    }

    public String getTr5() {
        return tr5.get();
    }

    public SimpleStringProperty tr5Property() {
        return tr5;
    }
}
