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
@Document(collection = "osm_way")
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Data
public class OsmWay {

    @Id
    private Long id;

    private List<Long> nodes;

    private Map<String, String> tags;
}
