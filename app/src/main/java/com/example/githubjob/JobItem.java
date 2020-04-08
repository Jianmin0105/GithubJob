package com.example.githubjob;
import org.json.JSONException;
import org.json.JSONObject;
public class JobItem {
    private String url;
    private String company;
    private String location;
    private String title;
    private String logo;
    public JobItem(JSONObject obj) throws JSONException {

        this.url = obj.getString("url");
        this.company = obj.getString("company");
        this.location = obj.getString("location");
        this.title = obj.getString("title");
        this.logo = obj.getString("company_logo");
    }

    public String getUrl() {
        return url;
    }

    public String getCompany() {
        return company;
    }

    public String getLocation() {
        return location;
    }

    public String getTitle() {
        return title;
    }

    public String getLogo() {
        return logo;
    }
}
