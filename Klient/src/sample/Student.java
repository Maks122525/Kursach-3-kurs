package sample;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Student {
    private final SimpleStringProperty id = new SimpleStringProperty(this, "id");
    private final StringProperty name_s = new SimpleStringProperty(this, "name_s");
    private final StringProperty name_g = new SimpleStringProperty(this, "name_g");


    public StringProperty idProperty() {
        return id ;
    }
    public final String getId() {
        return idProperty().get();
    }
    public final void setId(String id) {
        idProperty().set(id);
    }

    public StringProperty name_sProperty() {
        return name_s ;
    }
    public final String getName_s() {
        return name_sProperty().get();
    }
    public final void setName_s(String name) {
        name_sProperty().set(name);
    }

    public StringProperty name_gProperty(){ return name_g;}
    public  final String getName_g(){return  name_gProperty().get();}
    public  final void setName_g(String name_f){name_gProperty().set(name_f);}

    public  Student(String id,String Name_s,String Name_f){
        setId(id);
        setName_s(Name_s);
        setName_g(Name_f);
    }
}
