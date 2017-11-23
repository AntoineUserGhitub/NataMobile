package natamobile.natamobile2.Entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Useraccount implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer id;
    private String usrEmail;
    private String usrLastName;
    private String usrFirstName;
    private String usrPasswords;
    private List<Observationsession> observationsessionList;

    public Useraccount() {
        this.setId(0);
        this.setObservationsessionList(new ArrayList<Observationsession>());
    }

    public Useraccount(Integer id) {
        this.id = id;
    }

    public Useraccount(Integer id, String usrEmail, String usrLastName, String usrFirstName, String usrPasswords) {
        this.id = id;
        this.usrEmail = usrEmail;
        this.usrLastName = usrLastName;
        this.usrFirstName = usrFirstName;
        this.usrPasswords = usrPasswords;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsrEmail() {
        return usrEmail;
    }

    public void setUsrEmail(String usrEmail) {
        this.usrEmail = usrEmail;
    }

    public String getUsrLastName() {
        return usrLastName;
    }

    public void setUsrLastName(String usrLastName) {
        this.usrLastName = usrLastName;
    }

    public String getUsrFirstName() {
        return usrFirstName;
    }

    public void setUsrFirstName(String usrFirstName) {
        this.usrFirstName = usrFirstName;
    }

    public String getUsrPasswords() {
        return usrPasswords;
    }

    public void setUsrPasswords(String usrPasswords) {
        this.usrPasswords = usrPasswords;
    }

    
    public List<Observationsession> getObservationsessionList() {
        return observationsessionList;
    }

    public void setObservationsessionList(List<Observationsession> observationsessionList) {
        this.observationsessionList = observationsessionList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Useraccount)) {
            return false;
        }
        Useraccount other = (Useraccount) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.googlevisionexemple.models.Useraccount[ id=" + id + " ]";
    }
    
}
