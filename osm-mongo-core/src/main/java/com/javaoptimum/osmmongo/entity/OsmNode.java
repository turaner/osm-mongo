package com.javaoptimum.osmmongo.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Map;

@Builder
@Document(collection = "osm_node")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class OsmNode {

    @Id
    private long id;

    private double longitude;

    private double latitude;

    private Map<String, String> tags;
}
