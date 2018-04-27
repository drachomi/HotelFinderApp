package com.richard.abigayle.hotelfinder.POJO;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by LENOVO on 4/26/2018.
 */

public class Rows {
    @SerializedName("elements")
    @Expose
    private List<Element> element = new ArrayList();

    public List<Element> getElement() {
        return element;
    }

    public void setElement(List<Element> element) {
        this.element = element;
    }
}
