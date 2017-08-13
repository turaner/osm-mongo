package com.javaoptimum.osmmongo.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.Map;


@Builder
@Document(collection = "osm_relation")
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Data
public class OsmRelation {

    @Id
    private Long id;

    private List<OsmRelationMember> members;

    private Map<String, String> tags;
}
