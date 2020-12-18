package sample;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Dolgnost {
    private final SimpleStringProperty id_dolg = new SimpleStringProperty(this, "id");
    private final StringProperty name_dolg = new SimpleStringProperty(this, "name");
    public StringProperty idProperty() {
        return id_dolg ;
    }

    public final String getId() {
        return idProperty().get();
    }

    public final void setId(String id) {
        idProperty().set(id);
    }

    public StringProperty nameProperty() {
        return name_dolg ;
    }
    public final String getName() {
        return nameProperty().get();
    }

    public final void setName(String name) {
        nameProperty().set(name);
    }
    public Dolgnost(String id, String name) {
        setId(id);
        setName(name);
    }
}
