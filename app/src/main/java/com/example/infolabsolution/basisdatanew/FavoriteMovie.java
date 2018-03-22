
/*
 * Copyright (c) By Fajar Nugraha Wahyu on 3/22/18 10:26 AM 2018.
 * All rights reserved
 */

package com.example.infolabsolution.basisdatanew;

import android.os.Parcel;
import android.os.Parcelable;


public class FavoriteMovie implements Parcelable {

    private int id;
    private String title;
    private String ratings;
    private String release_date;
    private String overview;
    private String banner;
    private String poster;

    public FavoriteMovie(Parcel in) {
        title = in.readString();
        ratings = in.readString();
        release_date = in.readString();
        overview = in.readString();
        banner = in.readString();
        poster = in.readString();
        id= in.readInt();
    }

    public static final Creator<FavoriteMovie> CREATOR = new Creator<FavoriteMovie>() {
        @Override
        public FavoriteMovie createFromParcel(Parcel in) {
            return new FavoriteMovie(in);
        }

        @Override
        public FavoriteMovie[] newArray(int size) {
            return new FavoriteMovie[size];
        }
    };

    public FavoriteMovie() {

    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getRating() {
        return ratings;
    }

    public void setRating(String ratings) {
        this.ratings = ratings;
    }

    public String getRelease_date() {
        return release_date;
    }

    public void setRelease_date(String release_date) {
        this.release_date = release_date;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getBanner() {
        return banner;
    }

    public void setBanner(String banner) {
        this.banner = banner;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public static Creator<FavoriteMovie> getCREATOR() {
        return CREATOR;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel teksdesc, int flags) {
        teksdesc.writeString(title);
        teksdesc.writeString(ratings);
        teksdesc.writeString(release_date);
        teksdesc.writeString(overview);
        teksdesc.writeString(banner);
        teksdesc.writeString(poster);
        teksdesc.writeInt(id);
    }
}
