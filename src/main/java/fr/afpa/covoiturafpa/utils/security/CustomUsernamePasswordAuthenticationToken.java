package fr.afpa.covoiturafpa.utils.security;

import java.util.Collection;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

public class CustomUsernamePasswordAuthenticationToken extends UsernamePasswordAuthenticationToken {

    private Integer idUser;

    public CustomUsernamePasswordAuthenticationToken(Object principal, Object credentials, Integer idUser) {
		super(principal, credentials);
        this.idUser = idUser;
	}

	public CustomUsernamePasswordAuthenticationToken(Object principal, Object credentials,
			Collection<? extends GrantedAuthority> authorities, Integer idUser) {
		super(principal, credentials, authorities);
        this.idUser = idUser;

	}

    public Integer getIdUser() {
        return idUser;
    }

    public void setIdUser(Integer idUser) {
        this.idUser = idUser;
    }
}
