package com.javaoptimum.osmmongo.repo;


import com.javaoptimum.osmmongo.entity.OsmNode;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface OsmNodeRepository extends MongoRepository<OsmNode, Long>{


}
