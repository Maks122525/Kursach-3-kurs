package sample;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Special {
    private final SimpleStringProperty id = new SimpleStringProperty(this, "id");
    private final StringProperty name_s = new SimpleStringProperty(this, "name_s");
    private final StringProperty shifr = new SimpleStringProperty(this, "shifr");
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

    public StringProperty shifrProperty(){ return shifr;}
    public  final String getShifr(){return shifrProperty().get();}
    public  final void setShifr(String shifr){shifrProperty().set(shifr);}

    public StringProperty name_fProperty(){ return name_f;}
    public  final String getName_f(){return  name_fProperty().get();}
    public  final void setName_f(String name_f){name_fProperty().set(name_f);}

    public  Special(String id,String Name_s,String Shifr,String Name_f){
        setId(id);
        setName_s(Name_s);
        setShifr(Shifr);
        setName_f(Name_f);
    }
}
