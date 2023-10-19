package com.CustomerDatabase.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="customers")
public class Customer {

    @Id
    private String id;
    private String name;
    private String desc;

    public Customer(String name,String desc) {
        this.name = name;
        this.desc = desc;
    }
    public void setName(String name){
        this.name = name;
    }

    public void setDesc(String desc){
        this.desc = desc;
    }

    public String getName(){
        return name;
    }

    public String getDesc(){
        return desc;
    }

    public String getId(){
        return id;
    }

    public void setId(String id){
        this.id = id;
    }

}
