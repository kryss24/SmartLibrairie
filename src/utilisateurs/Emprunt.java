package utilisateurs;
import java.sql.Date;
import java.time.LocalDate;
import java.util.*;

import documents.Document;

public class Emprunt {
    private int idAdh;
    private String code;
    private Date dateReservation;
    private Date dateRemise;
    private int etat;
    
    public Emprunt(int idAdh, String code, Date dateReservation, Date dateRemise, int etat) {
        this.idAdh = idAdh;
        this.code = code;
        this.dateReservation = dateReservation;
        this.dateRemise = dateRemise;
        this.etat = etat;
    }
    public String getCode() {
        return code;
    }
    public void setCode(String code) {
        this.code = code;
    }
    public Date getDateReservation() {
        return dateReservation;
    }
    public void setDateReservation(Date dateReservation) {
        this.dateReservation = dateReservation;
    }
    public Date getDateRemise() {
        return dateRemise;
    }
    public void setDateRemise(Date dateRemise) {
        this.dateRemise = dateRemise;
    }
    public int getEtat() {
        return etat;
    }
    public void setEtat(int etat) {
        this.etat = etat;
    }
    public boolean dateCompaire(){
        return (LocalDate.now()).isAfter(dateRemise.toLocalDate());
    }
    @Override
    public String toString() {
        return "Emprunt [idAdh=" + idAdh + ", code=" + code + ", dateReservation=" + dateReservation + ", dateRemise="
                + dateRemise + ", etat=" + etat + "]";
    }
    
}
