package com.javaoptimum.osmmongo.repo;


import com.javaoptimum.osmmongo.entity.OsmBound;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface OsmBoundRepository extends MongoRepository<OsmBound, Long> {


}
