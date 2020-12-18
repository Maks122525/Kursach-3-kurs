package sample;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Reiting {
    private final SimpleStringProperty fio = new SimpleStringProperty(this, "fio");
    private final StringProperty name_g = new SimpleStringProperty(this, "name_g");
    private final StringProperty name_d = new SimpleStringProperty(this, "name_d");
    private final StringProperty sr_lab = new SimpleStringProperty(this, "sr_lab");
    private final StringProperty sr_usr = new SimpleStringProperty(this, "sr_usr");
    private final StringProperty atest = new SimpleStringProperty(this, "atest");
    private final StringProperty itog = new SimpleStringProperty(this, "itog");

    public StringProperty fioProperty() {
        return fio ;
    }
    public final String getFio() {
        return fioProperty().get();
    }
    public final void setFio(String id) {
        fioProperty().set(id);
    }

    public StringProperty name_gProperty() {
        return name_g ;
    }
    public final String getName_g() {
        return name_gProperty().get();
    }
    public final void setName_g(String id) {
        name_gProperty().set(id);
    }

    public StringProperty name_dProperty() {
        return name_d ;
    }
    public final String getName_d() {
        return name_dProperty().get();
    }
    public final void setName_d(String id) {
        name_dProperty().set(id);
    }

    public StringProperty sr_labProperty() {
        return sr_lab ;
    }
    public final String getSr_lab() {
        return sr_labProperty().get();
    }
    public final void setSr_lab(String id) {
        sr_labProperty().set(id);
    }

    public StringProperty sr_usrProperty() {
        return sr_usr ;
    }
    public final String getSr_usr() {
        return sr_usrProperty().get();
    }
    public final void setSr_usr(String id) {
        sr_usrProperty().set(id);
    }

    public StringProperty atestProperty() {
        return atest ;
    }
    public final String getAtest() {
        return atestProperty().get();
    }
    public final void setAtest(String id) {
        atestProperty().set(id);
    }

    public  void reit(){
        double a = Float.parseFloat(getSr_lab())*0.5+Float.parseFloat(getSr_usr())*0.4+Float.parseFloat(getAtest())*0.1;
        String b = String.valueOf(a);
        setItog(b);
    }
    public StringProperty itogProperty() {
        return itog ;
    }
    public final String getItog() {
        return itogProperty().get();
    }
    public final void setItog(String id) {
        itogProperty().set(id);
    }

    public void print(){
    }
    Reiting(){
        setSr_usr("0");
        setSr_lab("0");
        setAtest("0");
    }
    Reiting(String fio,String name_g,String name_d,String sr_lab,String sr_usr,String atest,String itog){
        setFio(fio);
        setName_g(name_g);
        setName_d(name_d);
        setSr_lab(sr_lab);
        setSr_usr(sr_usr);
        setAtest(atest);
        setItog(itog);
    }
}
