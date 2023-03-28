//package com.adamstraub.tonsoftacos.tonsoftacos.entities;
//
//import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
//import lombok.AllArgsConstructor;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//import lombok.Setter;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//
//import javax.persistence.*;
//import java.util.Collection;
//import java.util.List;
//
//@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
//@Entity
//@Table(name ="owners")
//@Getter
//@Setter
//@NoArgsConstructor
//@AllArgsConstructor
//public class Owner implements UserDetails {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column (name = "owners_pk")
//    private Integer ownerId;
//
//    @Column(name = "name")
//    private String name;
//
//    @Column(name = "username")
//    private String username;
//
//    @Column(name = "psswrd")
//    private String psswrd;
//
//    @Column(name = "contact")
//    private String contact;
//    @Enumerated(EnumType.STRING)
//    private Role role;
//
//
//    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//        return List.of(new SimpleGrantedAuthority(role.name()));
//    }
//
//
//
//    @Override
//    public String getPassword() {
//        return psswrd;
//    }
//
//    @Override
//    public boolean isAccountNonExpired() {
//        return true;
//    }
//
//    @Override
//    public boolean isAccountNonLocked() {
//        return true;
//    }
//
//    @Override
//    public boolean isCredentialsNonExpired() {
//        return true;
//    }
//
//    @Override
//    public boolean isEnabled() {
//        return true;
//    }
//
//    private enum Role {
//    ADMIN, USER
//
//    }
//}
