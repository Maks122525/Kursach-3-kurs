package sample;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Vid_Rab {
    private final SimpleStringProperty id = new SimpleStringProperty(this, "id");
    private final StringProperty name = new SimpleStringProperty(this, "name");
    public StringProperty idProperty() {
        return id ;
    }

    public final String getId() {
        return idProperty().get();
    }

    public final void setId(String id) {
        idProperty().set(id);
    }

    public StringProperty nameProperty() {
        return name ;
    }
    public final String getName() {
        return nameProperty().get();
    }

    public final void setName(String name) {
        nameProperty().set(name);
    }
    public Vid_Rab(String id, String name) {
        setId(id);
        setName(name);
    }
}
