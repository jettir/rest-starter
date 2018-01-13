package com.jettir.modeler.service.dto;


import java.time.Instant;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A DTO for the MyAttribute entity.
 */
public class MyAttributeDTO implements Serializable {

    private Long id;

    private String name;

    private Instant created;

    private Instant modified;

    private String modifiedBy;

    private String createdBy;

    private Long myEntityId;

    private Long myAttributeTypeId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Instant getCreated() {
        return created;
    }

    public void setCreated(Instant created) {
        this.created = created;
    }

    public Instant getModified() {
        return modified;
    }

    public void setModified(Instant modified) {
        this.modified = modified;
    }

    public String getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Long getMyEntityId() {
        return myEntityId;
    }

    public void setMyEntityId(Long myEntityId) {
        this.myEntityId = myEntityId;
    }

    public Long getMyAttributeTypeId() {
        return myAttributeTypeId;
    }

    public void setMyAttributeTypeId(Long myAttributeTypeId) {
        this.myAttributeTypeId = myAttributeTypeId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        MyAttributeDTO myAttributeDTO = (MyAttributeDTO) o;
        if(myAttributeDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), myAttributeDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "MyAttributeDTO{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", created='" + getCreated() + "'" +
            ", modified='" + getModified() + "'" +
            ", modifiedBy='" + getModifiedBy() + "'" +
            ", createdBy='" + getCreatedBy() + "'" +
            "}";
    }
}
