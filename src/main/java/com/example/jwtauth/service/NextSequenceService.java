package com.example.jwtauth.service;

import static org.springframework.data.mongodb.core.FindAndModifyOptions.options;
import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import com.example.jwtauth.model.ApplicationUser;



@Service
public class NextSequenceService {
    @Autowired private MongoOperations mongo;

    public long getNextSequence(Long seqNo)
    {
    	ApplicationUser counter = mongo.findAndModify(
            query(where("_id").is(seqNo)),
            new Update().inc("seq",1),
            options().returnNew(true).upsert(true),
            ApplicationUser.class);
        return counter.getSeq();
    }
}