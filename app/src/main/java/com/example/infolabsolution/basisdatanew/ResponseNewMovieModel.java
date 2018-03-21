package com.example.infolabsolution.basisdatanew;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResponseNewMovieModel {
    @SerializedName("results")
    private List<MovieConnection> results;

    public void setResults(List<MovieConnection> results) {
        this.results = results;
    }
    public List<MovieConnection> getResults() {
        return results;
    }

}
