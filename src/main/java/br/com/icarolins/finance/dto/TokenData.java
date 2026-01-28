package br.com.icarolins.finance.dto;

public class TokenData {
    private String email;
    private Long userId;
    private Role roles;

    public TokenData(String email, Long userId, Role roles) {
        this.email = email;
        this.userId = userId;
        this.roles = roles;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public Long getUserId() {
        return userId;
    }
    public void setUserId(Long userId) {
        this.userId = userId;
    }
    public Role getRoles() {
        return roles;
    }
    public void setRoles(Role roles) {
        this.roles = roles;
    }
}
