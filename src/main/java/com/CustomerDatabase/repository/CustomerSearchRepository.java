package com.CustomerDatabase.repository;

import com.CustomerDatabase.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public class CustomerSearchRepository {

    @Autowired
    MongoTemplate mongoTemplate;

    public Collection searchCust(String text) {
        return mongoTemplate.find(Query.query(new Criteria()
                .orOperator(Criteria.where("id").regex(text, "i"),
                        Criteria.where("name").regex(text, "i"))
        ), Customer.class);
    }

    public List<Customer> sortIDQueryASC() {
        Query query = new Query().with(Sort.by(Sort.Direction.ASC, "_id"));
        return mongoTemplate.find(query,Customer.class);
    }
    public List<Customer> sortIDQueryDESC() {
        Query query = new Query().with(Sort.by(Sort.Direction.DESC, "_id"));
        return mongoTemplate.find(query,Customer.class);
    }

    public List<Customer> sortNameQueryASC() {
        Query query = new Query().with(Sort.by(Sort.Direction.ASC, "name"));
        return mongoTemplate.find(query,Customer.class);
    }
    public List<Customer> sortNameQueryDESC() {
        Query query = new Query().with(Sort.by(Sort.Direction.DESC, "name"));
        return mongoTemplate.find(query,Customer.class);
    }
}
