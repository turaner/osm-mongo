package com.javaoptimum.osmmongo.repo;


import com.javaoptimum.osmmongo.entity.OsmRelation;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface OsmRelationRepository extends MongoRepository<OsmRelation, Long>{

}
