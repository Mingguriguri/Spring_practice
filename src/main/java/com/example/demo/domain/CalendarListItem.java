package com.example.demo.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class CalendarListItem {

    private String id;

    @JsonCreator
    public CalendarListItem(@JsonProperty("id") String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }


}
