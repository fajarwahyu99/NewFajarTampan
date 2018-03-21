package com.example.infolabsolution.basisdatanew;

import org.json.JSONObject;



public class MovieItems {

    private int id;
    private String title;
    private String overview;
    private String tayang;
    private String ratings;
    private String banner;
    public MovieItems(JSONObject object) {
        try{
            this.title = object.getString("title");
            this.overview = object.getString("overview");
            this.tayang = object.getString("release_date");
            this.ratings = object.getString("vote_average");
            String urlBanner = object.getString("backdrop_path");
            this.banner = BuildConfig.BASE_URL_BANNER + urlBanner;
        }catch (Exception exept){
            exept.printStackTrace();
        }
    }

    public String getJudul(){
        return title;
    }

    public void setJudul(String title){
        this.title = title;
    }

    public String getDeskripsi(){
        return overview;
    }

    public void setDeskripsi(String overview){
        this.overview = overview;
    }

    public String getTayang(){
        return tayang;
    }
    public void setTayang(String tayang){
        this.tayang = tayang;
    }

    public String getRating () { return ratings; }

    public void setRating(String ratings) {
        this.ratings = ratings;
    }

    public String getBanner() {
        return banner;
    }

    public void setBanner(String banner) {
        this.banner = banner;
    }
}
