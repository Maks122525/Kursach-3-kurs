package sample;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Grupa {
    private final SimpleStringProperty id = new SimpleStringProperty(this, "id");
    private final StringProperty name_g = new SimpleStringProperty(this, "name_g");
    private final StringProperty name_s = new SimpleStringProperty(this, "name_s");
    private final StringProperty name_f = new SimpleStringProperty(this, "name_f");

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
    public  final String getName_g(){return name_gProperty().get();}
    public  final void setName_g(String name_g){name_gProperty().set(name_g);}

    public StringProperty name_fProperty(){ return name_f;}
    public  final String getName_f(){return  name_fProperty().get();}
    public  final void setName_f(String name_f){name_fProperty().set(name_f);}

    public  Grupa(String id,String Name_g,String Name_s,String Name_f){
        setId(id);
        setName_g(Name_g);
        setName_s(Name_s);
        setName_f(Name_f);
    }
}
