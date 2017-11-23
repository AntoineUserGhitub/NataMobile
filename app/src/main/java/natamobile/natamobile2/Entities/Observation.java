/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package natamobile.natamobile2.Entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class Observation implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer id;
    private Integer count;
    private Date date;
    private Bird idBird;
    private Observationsession idObsSession;
    private Forecast idForecast;
    private List<Observationmedia> observationmediaList;

    public Observation() {
        this.setId(0);
        this.setObservationmediaList(new ArrayList<Observationmedia>());
        this.setDate(new Date());
    }

    public Observation(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Bird getIdBird() {
        return idBird;
    }

    public void setIdBird(Bird idBird) {
        this.idBird = idBird;
    }

    public Observationsession getIdObsSession() {
        return idObsSession;
    }

    public void setIdObsSession(Observationsession idObsSession) {
        this.idObsSession = idObsSession;
    }

    public Forecast getIdForecast() {
        return idForecast;
    }

    public void setIdForecast(Forecast idForecast) {
        this.idForecast = idForecast;
    }

    public List<Observationmedia> getObservationmediaList() {
        return observationmediaList;
    }

    public void setObservationmediaList(List<Observationmedia> observationmediaList) {
        this.observationmediaList = observationmediaList;
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
        if (!(object instanceof Observation)) {
            return false;
        }
        Observation other = (Observation) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.googlevisionexemple.models.Observation[ id=" + id + " ]";
    }
    
}
