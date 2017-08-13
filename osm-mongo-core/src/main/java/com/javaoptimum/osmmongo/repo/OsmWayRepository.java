package com.javaoptimum.osmmongo.repo;


import com.javaoptimum.osmmongo.entity.OsmWay;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface OsmWayRepository extends MongoRepository<OsmWay, Long> {


}
