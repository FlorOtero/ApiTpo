package edu.uade.api.tpo.db;

import java.io.Serializable;

public interface Persistible extends Serializable {

    public void setId(String id);

    public String getId();

}
