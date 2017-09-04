package hr.diskobolos.model.security;

import hr.diskobolos.model.IIdentifier;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "AUTHORITIES", schema = "DISKOBOLOS")
public class Authority implements IIdentifier, Serializable {

    @Id
    @Column(name = "id", updatable = false)
    @SequenceGenerator(name = "diskobolos.authority_id_seq", schema = "DISKOBOLOS", sequenceName = "diskobolos.authority_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "diskobolos.authority_id_seq")
    private Integer id;

    @Column(name = "ROLE")
    @Enumerated(EnumType.STRING)
    private Role role;

    @Column(name = "PERMISSION_LEVEL")
    private Integer permissionLevel;

    public Authority() {
    }

    public Authority(Integer id, Role role) {
        this.id = id;
        this.role = role;
    }

    @Override
    public Integer getId() {
        return id;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Integer getPermissionLevel() {
        return permissionLevel;
    }

    public void setPermissionLevel(Integer permissionLevel) {
        this.permissionLevel = permissionLevel;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Authority)) {
            return false;
        }

        Authority authority = (Authority) o;

        return id != null ? id.equals(authority.id) : authority.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "Authority{"
                + "id=" + id
                + ", role=" + role
                + ", permissionLevel=" + permissionLevel
                + '}';
    }
}
