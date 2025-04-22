public class User {
    public int userId;
    public String userName;
    public boolean active;

    public User() {}

    public User(int userId, String userName, boolean active) {
        this.userId = userId;
        this.userName = userName;
        this.active = active;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    @Override
    public String toString() {
        return "User [userId=" + userId + ", userName=" + userName + ", active=" + active + "]";
    }
    
}
