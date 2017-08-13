package com.javaoptimum.osmmongo.importer.service;


import lombok.extern.slf4j.Slf4j;
import org.openstreetmap.osmosis.core.container.v0_6.EntityContainer;
import org.openstreetmap.osmosis.core.domain.v0_6.Bound;
import org.openstreetmap.osmosis.core.domain.v0_6.Entity;
import org.openstreetmap.osmosis.core.domain.v0_6.Node;
import org.openstreetmap.osmosis.core.domain.v0_6.Relation;
import org.openstreetmap.osmosis.core.domain.v0_6.Way;
import org.openstreetmap.osmosis.core.task.v0_6.Sink;
import org.openstreetmap.osmosis.pbf2.v0_6.PbfReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.File;
import java.util.Map;

@Component
@Slf4j
public class OsmImporter {

    @Autowired
    private OsmImporterWorker osmExtractorWorker;

    @Value("${pbfPath}")
    private String pbfPath;

    private PbfReader reader;

    @PostConstruct
    public void init() {
        File osmFile = new File(pbfPath);
        reader = new PbfReader(osmFile, 1);
    }


    public void process() {

        Sink sinkImplementation = new Sink() {

            public void process(EntityContainer entityContainer) {

                Entity entity = entityContainer.getEntity();

                if (entity instanceof Node) {
                    osmExtractorWorker.saveNode((Node) entity);
                } else if (entity instanceof Way) {
                    osmExtractorWorker.saveWay((Way) entity);
                } else if (entity instanceof Relation) {
                    osmExtractorWorker.saveRelation((Relation) entity);
                } else if (entity instanceof Bound) {
                    osmExtractorWorker.saveBound((Bound) entity);
                } else {
                    log.warn("Unknow entity type " + entity.getClass().getCanonicalName());
                }
            }

            public void initialize(Map<String, Object> arg0) {
            }

            public void complete() {
            }

            public void release() {
            }

        };

        reader.setSink(sinkImplementation);
        reader.run();

        log.info("Done");
    }


}