package men.ngopi.sans.myinstaapp.models;

import java.util.ArrayList;

import men.ngopi.sans.myinstaapp.dbHelper.PostHelper;

public class UserModel {
    private int id;
    private String name;
    private String username;
    private String profilePicture;
    private static UserModel instance;

    public static UserModel getInstance() {
        return instance;
    }

    public UserModel() {
        instance = this;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(String profilePicture) {
        this.profilePicture = profilePicture;
    }

    public ArrayList<PostModel> fetchPosts() {
        return PostHelper.getInstance().fetchPostsByUserId(id);
    }

    public long addPost(PostModel postModel) {
        postModel.setUserId(id);
        return PostHelper.getInstance().insert(postModel);
    }

    public long deletePost(int id) {
        return PostHelper.getInstance().delete(id);
    }
}
