package fr.afpa.covoiturafpa.dto;

import java.util.List;

/**
 * Classe encapsulant les informations à communiquer au client en cas de connexion étbalie avec succès.
 */
public class LoginResponseDto {
    /**
     * JWT a utilisé pour les échanges avec l'API
     */
    private String token;

    private int userId;

    /**
     * Temps avant l'expiration du JWT
     */
    private long expiresIn;
    
    private List<String> roles;

    public String getToken() {
        return token;
    }

    public LoginResponseDto setToken(String token) {
        this.token = token;
        return this;
    }

    public long getExpiresIn() {
        return expiresIn;
    }

    public LoginResponseDto setExpiresIn(long expiresIn) {
        this.expiresIn = expiresIn;
        return this;
    }

    
    public int getUserId() {
        return userId;
    }

    public LoginResponseDto setUserId(int userId) {
        this.userId = userId;
        return this;
    }

    public List<String> getRoles() {
        return roles;
    }

    public LoginResponseDto setRoles(List<String> roles) {
        this.roles = roles;
        return this;
    }

}