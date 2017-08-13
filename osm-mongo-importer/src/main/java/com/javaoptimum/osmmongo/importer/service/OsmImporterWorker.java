package com.javaoptimum.osmmongo.importer.service;


import com.javaoptimum.osmmongo.entity.OsmBound;
import com.javaoptimum.osmmongo.entity.OsmNode;
import com.javaoptimum.osmmongo.entity.OsmRelation;
import com.javaoptimum.osmmongo.entity.OsmRelationMember;
import com.javaoptimum.osmmongo.entity.OsmWay;
import com.javaoptimum.osmmongo.repo.OsmBoundRepository;
import com.javaoptimum.osmmongo.repo.OsmNodeRepository;
import com.javaoptimum.osmmongo.repo.OsmRelationRepository;
import com.javaoptimum.osmmongo.repo.OsmWayRepository;
import com.vividsolutions.jts.geom.GeometryFactory;
import com.vividsolutions.jts.geom.PrecisionModel;
import org.openstreetmap.osmosis.core.domain.v0_6.Bound;
import org.openstreetmap.osmosis.core.domain.v0_6.Node;
import org.openstreetmap.osmosis.core.domain.v0_6.Relation;
import org.openstreetmap.osmosis.core.domain.v0_6.Tag;
import org.openstreetmap.osmosis.core.domain.v0_6.Way;
import org.openstreetmap.osmosis.core.domain.v0_6.WayNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class OsmImporterWorker {

    private static final GeometryFactory GF = new GeometryFactory(new PrecisionModel(), 4326);

    @Autowired
    private OsmNodeRepository osmNodeRepository;

    @Autowired
    private OsmWayRepository osmWayRepository;

    @Autowired
    private OsmRelationRepository osmRelationRepository;

    @Autowired
    private OsmBoundRepository osmBoundRepository;

    @Async("importerExecutor")
    public void saveNode(Node entity) {
        double longitude = (entity).getLongitude();
        double latitude = (entity).getLatitude();

        OsmNode node = OsmNode.builder()
                .id(entity.getId())
                .longitude(longitude)
                .latitude(latitude)
                .tags(entity.getTags().stream()
                        .collect(Collectors.toMap(x -> x.getKey().replace(".", "_"), Tag::getValue)))
                .build();
        osmNodeRepository.save(node);
    }

    @Async("importerExecutor")
    public void saveWay(Way entity) {
        OsmWay way = OsmWay.builder()
                .id(entity.getId())
                .nodes(entity.getWayNodes().stream().map(WayNode::getNodeId).collect(Collectors.toList()))
                .tags(entity.getTags().stream()
                        .collect(Collectors.toMap(x -> x.getKey().replace(".", "_"), Tag::getValue)))
                .build();
        osmWayRepository.save(way);
    }

    @Async("importerExecutor")
    public void saveRelation(Relation entity) {

        OsmRelation relation = OsmRelation.builder()
                .id(entity.getId())
                .members(entity.getMembers().stream().map(
                        x -> OsmRelationMember.builder()
                                .memberId(x.getMemberId())
                                .memberType(x.getMemberType())
                                .memberRole(x.getMemberRole())
                                .build()
                ).collect(Collectors.toList()))
                .tags(entity.getTags().stream()
                        .collect(Collectors.toMap(x -> x.getKey().replace(".", "_"), Tag::getValue)))
                .build();

        osmRelationRepository.save(relation);
    }

    @Async("importerExecutor")
    public void saveBound(Bound entity) {
        OsmBound bound = OsmBound.builder()
                .id(entity.getId())
                .right(entity.getRight())
                .left(entity.getLeft())
                .top(entity.getTop())
                .bottom(entity.getBottom())
                .origin(entity.getOrigin())
                .build();

        osmBoundRepository.save(bound);
    }


}
