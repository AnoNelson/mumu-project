package gov.rw.brd.domain;

/**
 * Created by Nick on 4/3/2020.
 */
public class EmailChecker {
    private String email;
    private String did_you_mean;
    private String user;
    private String domain;
    private String mx_found;
    private Boolean smtp_check;
    private String catch_all;
    private String role;
    private String disposable;
    private String free;
    private String score;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDid_you_mean() {
        return did_you_mean;
    }

    public void setDid_you_mean(String did_you_mean) {
        this.did_you_mean = did_you_mean;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public String getMx_found() {
        return mx_found;
    }

    public void setMx_found(String mx_found) {
        this.mx_found = mx_found;
    }

    public Boolean getSmtp_check() {
        return smtp_check;
    }

    public void setSmtp_check(Boolean smtp_check) {
        this.smtp_check = smtp_check;
    }

    public String getCatch_all() {
        return catch_all;
    }

    public void setCatch_all(String catch_all) {
        this.catch_all = catch_all;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getDisposable() {
        return disposable;
    }

    public void setDisposable(String disposable) {
        this.disposable = disposable;
    }

    public String getFree() {
        return free;
    }

    public void setFree(String free) {
        this.free = free;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }
}
