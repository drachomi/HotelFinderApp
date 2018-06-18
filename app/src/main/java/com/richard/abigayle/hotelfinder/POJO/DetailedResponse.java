package com.richard.abigayle.hotelfinder.POJO;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by LENOVO on 6/1/2018.
 */

public class DetailedResponse {
    @SerializedName("html_attributions")
    @Expose
    private List<Object> html_attributions = new ArrayList<Object>();

    @SerializedName("next_page_token")
    @Expose
    private String next_page_token;

    @SerializedName("result")
    @Expose
    private Result results;



    @SerializedName("status")
    @Expose
    private String status;

    public List<Object> getHtml_attributions() {
        return html_attributions;
    }

    public void setHtml_attributions(List<Object> html_attributions) {
        this.html_attributions = html_attributions;
    }

    public String getNext_page_token() {
        return next_page_token;
    }

    public void setNext_page_token(String next_page_token) {
        this.next_page_token = next_page_token;
    }

    public Result getResults() {
        return results;
    }

    public void setResults(Result results) {
        this.results = results;
    }



    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
