package natamobile.natamobile2.Entities;

import java.io.Serializable;

public class Observationmedia implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer id;
    private String mediaUrl;
    private Boolean suspicious;
    private String comment;
    private Observation idObservation;

    public Observationmedia() {
        this.setId(0);
    }

    public Observationmedia(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMediaUrl() {
        return mediaUrl;
    }

    public void setMediaUrl(String mediaUrl) {
        this.mediaUrl = mediaUrl;
    }

    public Boolean getSuspicious() {
        return suspicious;
    }

    public void setSuspicious(Boolean suspicious) {
        this.suspicious = suspicious;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Observation getIdObservation() {
        return idObservation;
    }

    public void setIdObservation(Observation idObservation) {
        this.idObservation = idObservation;
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
        if (!(object instanceof Observationmedia)) {
            return false;
        }
        Observationmedia other = (Observationmedia) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.googlevisionexemple.models.Observationmedia[ id=" + id + " ]";
    }
    
}
