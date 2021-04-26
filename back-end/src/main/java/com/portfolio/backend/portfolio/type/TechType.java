package com.portfolio.backend.portfolio.type;

public enum TechType {
    HTML(100L), CSS(101L), JavaScript(102L), jQuery(103L),
    ReactJs(104L), ZeptoJs(105L), Angular(106L), VueJs(107L),
    BackBone(108L), Ember(109L), Ruby(110L),RubyOnRails(111L), Python(112L),
    Django(113L), Flask(114L), Pylons(115L),PHP(116L), Laravel(117L), Java(118L),
    Spring(119L), SpringBoot(120L), Scala(121L), Play(122L), NodeJS(123L),
    MySQL(124L), PostgreSQL(125L), MongoDB(126L), RelationalSQL(127L), NonRelationalSQL(128L),
    Apache(129L), Nginx(130L), Bootstrap(131L), redis(132L), AWS(133L), Enzyme(134L), Chai(135L),
    Travis(136L), GitLabCI(137L), BitBucketPipeLine(138L), Jenkins(139L), GCP(140L), GoogleCloudPlatform(141L);

    private Long value;

    private TechType(Long value){
        this.value = value;
    }

    public Long getValue() {
        return this.value;
    }
}
