package com.javaoptimum.osmmongo.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.openstreetmap.osmosis.core.domain.v0_6.EntityType;


@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Data
public class OsmRelationMember {

    private long memberId;

    private EntityType memberType;

    private String memberRole;
}
