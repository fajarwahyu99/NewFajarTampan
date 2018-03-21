package com.example.infolabsolution.basisdatanew;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.stream.Stream;


public class ModelMovie extends ArrayList<Parcelable> implements Parcelable {
    private String title;
    private String ratings;
    private String release_date;
    private int id;
    private String overview;
    private String banner;
    private String poster;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public static Creator<ModelMovie> getCREATOR() {
        return CREATOR;
    }



    @Override
    public void writeToParcel(Parcel teksdesc, int flags) {
        teksdesc.writeString(this.title);
        teksdesc.writeString(this.ratings);
        teksdesc.writeString(this.release_date);
        teksdesc.writeString(this.overview);
        teksdesc.writeString(this.banner);
        teksdesc.writeString(this.poster);
        teksdesc.writeInt(this.id);
    }

    public ModelMovie() {
    }

    protected ModelMovie(Parcel in) {
        this.title = in.readString();
        this.ratings = in.readString();
        this.release_date = in.readString();
        this.overview = in.readString();
        this.banner = in.readString();
        this.poster = in.readString();
        this.id = in.readInt();
    }
    @Override
    public int describeContents() {
        return 0;
    }
    public static final Parcelable.Creator<ModelMovie> CREATOR = new Parcelable.Creator<ModelMovie>() {
        @Override
        public ModelMovie createFromParcel(Parcel source) {
            return new ModelMovie(source);
        }

        @Override
        public ModelMovie[] newArray(int size) {
            return new ModelMovie[size];
        }
    };

    @Override
    public Stream<Parcelable> parallelStream() {
        return null;
    }
    @Override
    public Stream<Parcelable> stream() {
        return null;
    }
}
