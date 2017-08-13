package com.javaoptimum.osmmongo.api.controller;

import com.javaoptimum.osmmongo.entity.OsmBound;
import com.javaoptimum.osmmongo.entity.OsmNode;
import com.javaoptimum.osmmongo.entity.OsmRelation;
import com.javaoptimum.osmmongo.entity.OsmWay;
import com.javaoptimum.osmmongo.repo.OsmBoundRepository;
import com.javaoptimum.osmmongo.repo.OsmNodeRepository;
import com.javaoptimum.osmmongo.repo.OsmRelationRepository;
import com.javaoptimum.osmmongo.repo.OsmWayRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OsmMongoApiController {


    @Autowired
    private OsmBoundRepository osmBoundRepository;

    @Autowired
    private OsmRelationRepository osmRelationRepository;

    @Autowired
    private OsmWayRepository osmWayRepository;

    @Autowired
    private OsmNodeRepository osmNodeRepository;


    @RequestMapping(value = "bound", method = RequestMethod.GET)
    public @ResponseBody OsmBound bound(){
        return osmBoundRepository.findAll().iterator().next();
    }

    @RequestMapping(value = "node", method = RequestMethod.GET)
    public @ResponseBody OsmNode node(@RequestParam long id){
        return osmNodeRepository.findOne(id);
    }

    @RequestMapping(value = "way", method = RequestMethod.GET)
    public @ResponseBody OsmWay way(@RequestParam long id){
        return osmWayRepository.findOne(id);
    }

    @RequestMapping(value = "relation", method = RequestMethod.GET)
    public @ResponseBody OsmRelation relation(@RequestParam long id){
        return osmRelationRepository.findOne(id);
    }
}
