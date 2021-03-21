package com.fatec.mom.domain.user.authority;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "MOM_PERM")
@SequenceGenerator(name = "MOM_SQ_PERM", sequenceName = "MOM_SQ_PERM", allocationSize = 1)
@Data @Builder @AllArgsConstructor @NoArgsConstructor
public class GrantedAuthorityImpl implements GrantedAuthority {

    private static final long serialVersionUID = 1L;

    @Id
    @NotNull
    @GeneratedValue(generator = "MOM_SQ_PERM", strategy = GenerationType.SEQUENCE)
    @Column(name = "COD_PERM")
    private Long id;

    @Column(name = "PERM", nullable = false)
    @Enumerated(EnumType.STRING)
    private Role authority;

    @Override
    public String getAuthority() {
        return authority.name();
    }

    public GrantedAuthorityImpl(final Role role) {
        this.authority = role;
    }
}
