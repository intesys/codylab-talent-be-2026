package it.intesys.codylab.db.model;

public class UserActivity {

    private Long userId;
    private Long customerId;

    public UserActivity() {
    }

    public Long getUserId() {
        return userId;
    }

    public UserActivity setUserId(Long userId) {
        this.userId = userId;
        return this;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public UserActivity setCustomerId(Long customerId) {
        this.customerId = customerId;
        return this;
    }
}
