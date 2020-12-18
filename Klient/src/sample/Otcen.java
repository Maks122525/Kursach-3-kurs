package sample;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Otcen {
    private final SimpleStringProperty id = new SimpleStringProperty(this, "id");
    private final StringProperty name_s = new SimpleStringProperty(this, "name_s");
    private final StringProperty name_r = new SimpleStringProperty(this, "name_r");
    private final StringProperty otc = new SimpleStringProperty(this, "otc");
    private final StringProperty name_d = new SimpleStringProperty(this, "name_d");
    private final StringProperty name_p = new SimpleStringProperty(this, "name_p");

    public StringProperty idProperty() {
        return id ;
    }
    public final String getId() {
        return idProperty().get();
    }
    public final void setId(String id) {
        idProperty().set(id);
    }

    public StringProperty otcProperty() {
        return otc ;
    }
    public final String getOtc() {
        return otcProperty().get();
    }
    public final void setOtc(String otc) {
        otcProperty().set(otc);
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

    public StringProperty name_rProperty() {
        return name_r ;
    }
    public final String getName_r() {
        return name_rProperty().get();
    }
    public final void setName_r(String name) {
        name_rProperty().set(name);
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

    public  Otcen(String id,String Name_s,String Name_r,String Otc,String Name_d,String Name_p){
        setId(id);
        setOtc(Otc);
        setName_s(Name_s);
        setName_r(Name_r);
        setName_p(Name_p);
        setName_d(Name_d);
    }
}
