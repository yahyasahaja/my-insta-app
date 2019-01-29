package men.ngopi.sans.myinstaapp.models;

import men.ngopi.sans.myinstaapp.dbHelper.PostHelper;
import men.ngopi.sans.myinstaapp.dbHelper.UserHelper;

public class PostModel {
    private int id;
    private int userId;
    private String image;
    private String description;

    public UserModel getUser() {
        return user;
    }

    public void setUser(UserModel user) {
        this.user = user;
    }

    private UserModel user;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public UserModel fetchUser() {
        return this.user = UserHelper.getInstance().fetchUserById(userId);
    }
}
