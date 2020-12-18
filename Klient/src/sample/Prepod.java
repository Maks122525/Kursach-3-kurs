package sample;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Prepod {
    private final SimpleStringProperty id = new SimpleStringProperty(this, "id");
    private final StringProperty name_p = new SimpleStringProperty(this, "name_p");
    private final StringProperty name_d = new SimpleStringProperty(this, "name_d");


    public StringProperty idProperty() {
        return id ;
    }

    public final String getId() {
        return idProperty().get();
    }
    public final void setId(String id) {
        idProperty().set(id);
    }

    public StringProperty name_pProperty() {
        return name_p ;
    }
    public final String getName_p() {
        return name_pProperty().get();
    }
    public final void setName_p(String name) {
        name_pProperty().set(name);
    }

    public StringProperty name_dProperty(){ return name_d;}
    public  final String getName_d(){return  name_dProperty().get();}
    public  final void setName_d(String name_f){name_dProperty().set(name_f);}

    public  Prepod(String id,String Name_s,String Name_f){
        setId(id);
        setName_p(Name_s);
        setName_d(Name_f);
    }
}
