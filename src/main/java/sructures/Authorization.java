package sructures;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * Created by Max on 05.03.2017.
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Authorization", propOrder = {
        "personId",
        "status"
})
public class Authorization {
    @XmlElement(name = "personId")
    private long personId;

    @XmlElement(name = "status")
    private boolean status;

    public long getPersonId() {
        return personId;
    }

    public void setPersonId(long personId) {
        this.personId = personId;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }


}