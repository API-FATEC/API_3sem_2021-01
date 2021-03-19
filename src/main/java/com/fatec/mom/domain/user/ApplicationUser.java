package com.fatec.mom.domain.user;

import com.fatec.mom.domain.user.authority.GrantedAuthorityImpl;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;
import java.util.Set;

@Entity
@Table(name = "MOM_USER")
@SequenceGenerator(name = "MOM_SQ_USER", sequenceName = "MOM_SQ_USER", allocationSize = 1)
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ApplicationUser implements UserDetails, Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "MOM_SQ_USER", strategy = GenerationType.SEQUENCE)
    @Column(name = "COD_USER", nullable = false)
    private Long id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "EMAIL")
    private String email;

    @Column(name = "USERNAME")
    private String username;

    @Column(name = "PASSWORD")
    private String password;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "COD_USER")
    @LazyCollection(LazyCollectionOption.FALSE)
    private Set<GrantedAuthorityImpl> grantedAuthorities;

    @Column(name = "ACTIVE")
    private boolean isEnabled;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return grantedAuthorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return isEnabled;
    }
}
