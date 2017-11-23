package natamobile.natamobile2.Entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
public class Observationsession implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer id;
    private boolean pending;
    private Boolean valid;
    private Date startDate;
    private Date endDate;
    private Float latitude;
    private Float longitude;
    private List<Observation> observationList;
    private Useraccount idUsr;

    public Observationsession() {
        this.setId(0);
        this.setObservationList(new ArrayList<Observation>());
        this.setStartDate(new Date());
        this.setValid(false);
        this.setPending(true);
    }

    public Observationsession(Integer id) {
        this.id = id;
    }

    public Observationsession(Integer id, boolean pending) {
        this.id = id;
        this.pending = pending;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public boolean getPending() {
        return pending;
    }

    public void setPending(boolean pending) {
        this.pending = pending;
    }

    public Boolean getValid() {
        return valid;
    }

    public void setValid(Boolean valid) {
        this.valid = valid;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Float getLatitude() {
        return latitude;
    }

    public void setLatitude(Float latitude) {
        this.latitude = latitude;
    }

    public Float getLongitude() {
        return longitude;
    }

    public void setLongitude(Float longitude) {
        this.longitude = longitude;
    }

    public List<Observation> getObservationList() {
        return observationList;
    }

    public void setObservationList(List<Observation> observationList) {
        this.observationList = observationList;
    }

    public Useraccount getIdUsr() {
        return idUsr;
    }

    public void setIdUsr(Useraccount idUsr) {
        this.idUsr = idUsr;
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
        if (!(object instanceof Observationsession)) {
            return false;
        }
        Observationsession other = (Observationsession) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.googlevisionexemple.models.Observationsession[ id=" + id + " ]";
    }
    
}
