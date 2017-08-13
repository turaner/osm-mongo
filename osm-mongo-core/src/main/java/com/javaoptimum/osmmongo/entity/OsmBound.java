package com.javaoptimum.osmmongo.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Builder
@Document(collection = "osm_bound")
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Data
public class OsmBound {

    @Id
    private Long id;

    private double right;

    private double left;

    private double top;

    private double bottom;

    private String origin;
}
