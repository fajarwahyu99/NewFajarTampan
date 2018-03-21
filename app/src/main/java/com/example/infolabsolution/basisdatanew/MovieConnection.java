package com.example.infolabsolution.basisdatanew;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;


public class MovieConnection implements Parcelable{

    @SerializedName("title")
    private String title;
    @SerializedName("vote_average")
    private String ratings;
    @SerializedName("release_date")
    private String release_date;
    @SerializedName("overview")
    private String overview;
    @SerializedName("poster_path")
    private String poster;
    @SerializedName("backdrop_path")
    private String banner;

    protected MovieConnection(Parcel in) {
        title = in.readString();
        ratings = in.readString();
        release_date = in.readString();
        overview = in.readString();
        poster = in.readString();
        banner = in.readString();
    }

    public static final Creator<MovieConnection> CREATOR = new Creator<MovieConnection>() {
        @Override
        public MovieConnection createFromParcel(Parcel in) {
            return new MovieConnection(in);
        }

        @Override
        public MovieConnection[] newArray(int size) {
            return new MovieConnection[size];
        }
    };

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

    public String getPoster() {
        return BuildConfig.BASE_URL_POSTER + poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public String getBanner() {
        return BuildConfig.BASE_URL_BANNER + banner;
    }

    public void setBanner(String banner) {
        this.banner = banner;
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
        teksdesc.writeString(poster);
        teksdesc.writeString(banner);
    }
}
