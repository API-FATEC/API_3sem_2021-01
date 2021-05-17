package com.fatec.mom.test.domain.document;

import com.fatec.mom.domain.block.Block;
import com.fatec.mom.domain.block.BlockLink;
import com.fatec.mom.domain.block.BlockPage;
import com.fatec.mom.domain.block.BlockStatus;
import com.fatec.mom.domain.document.Document;
import com.fatec.mom.domain.revision.Revision;
import com.fatec.mom.domain.revision.RevisionName;
import com.fatec.mom.domain.revision.RevisionStatus;
import com.fatec.mom.domain.tag.Tag;
import com.fatec.mom.domain.tag.TypeTag;
import com.fatec.mom.domain.trait.Trait;
import com.google.gson.Gson;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class DocumentBuilderAssistant {

    @Test
    @DisplayName("Try to build a simple document")
    void tryToBuildASimpleDocument() {

        final var doc = DocumentBuilderAssistant.simpleDocument();

        assertThat(doc, notNullValue());
        assertThat(doc.getDocument(), equalTo("ABC-1234"));
        assertThat(doc.getBlocks().size(), equalTo(4));
        assertThat(doc.getRevisions().size(), equalTo(4));
        System.out.println(new Gson().toJson(doc));
    }

    public static Document simpleDocument() {
        var document = Document.builder()
                .name("ABC")
                .partNumber(1234)
                .createdDate(new Date())
                .tags(simpleTags())
                .build();

        simpleBlocks().forEach(document::addBlock);
        simpleRevisions().forEach(document::addRevision);
        simpleTraits().forEach(document::addTrait);

        return document;
    }

    private static Set<Block> simpleBlocks() {
        final var blocks = new HashSet<Block>();

        var block1 = buildBlock(Block.builder().section("00").subSection("00").number(1).name("Capa1").code(10).order(1).status(BlockStatus.REVISED).build());
        block1.addTrait(simpleFiftyTrait());
        block1.addTrait(simpleFiftyFiveTrait());
        var block2 = buildBlock(Block.builder().section("01").subSection("00").number(2).name("Capa2").code(12).order(2).status(BlockStatus.REVISED).build());
        block2.addTrait(simpleFiftyTrait());
        block2.addTrait(simpleSixtyTrait());
        var block3 = buildBlock(Block.builder().section("02").subSection("00").number(3).name("Capa3").code(14).order(3).status(BlockStatus.REVISED).build());
        block3.addTrait(simpleFiftyTrait());
        block3.addTrait(simpleFiftyFiveTrait());
        block3.addTrait(simpleSixtyTrait());
        var block4 = buildBlock(Block.builder().section("03").subSection("00").number(4).name("Capa4").code(16).order(4).status(BlockStatus.REVISED).build());
        block4.addTrait(simpleFiftyTrait());
        block4.addTrait(simpleFiftyFiveTrait());
        block4.addTrait(simpleSixtyTrait());


        blocks.add(block1);
        blocks.add(block2);
        blocks.add(block3);
        blocks.add(block4);

        return blocks;
    }

    private static Block buildBlock(Block block) {
        for (var link : simpleLinks(block)) {
            block.addLink(link);
        }
        block.setTags(simpleTags());

        return block;
    }

    private static Set<BlockLink> simpleLinks(Block block) {
        final var links = new HashSet<BlockLink>();

        links.add(BlockLink.builder().upload(new Date()).extension(".pdf").fileName(block.getName()).build());
        links.add(BlockLink.builder().upload(new Date()).extension(".docx").fileName(block.getName()).build());

        return links;
    }

    private static Set<Revision> simpleRevisions() {
        final var revisions = new HashSet<Revision>();

        revisions.add(Revision.builder().createdDate(new Date()).status(RevisionStatus.FINISHED).name(RevisionName.ORIGINAL.name()).build());
        revisions.add(Revision.builder().createdDate(new Date()).status(RevisionStatus.FINISHED).name(RevisionName.getRevName(1)).build());
        revisions.add(Revision.builder().createdDate(new Date()).status(RevisionStatus.FINISHED).name(RevisionName.getRevName(2)).build());
        revisions.add(Revision.builder().createdDate(new Date()).status(RevisionStatus.FINISHED).name(RevisionName.getRevName(3)).build());
        revisions.add(Revision.builder().createdDate(new Date()).status(RevisionStatus.OPENED).name(RevisionName.getRevName(4)).build());

        return revisions;
    }

    private static Set<Tag> simpleTags() {
        final var tags = new HashSet<Tag>();

        tags.add(Tag.builder().created(new Date()).name("Marte").typeTag(new TypeTag("Planeta")).build());
        tags.add(Tag.builder().created(new Date()).name("Terra").typeTag(new TypeTag("Planeta")).build());

        return tags;
    }

    private static Set<Trait> simpleTraits() {
        final var traits = new HashSet<Trait>();

        traits.add(simpleFiftyTrait());
        traits.add(simpleFiftyFiveTrait());
        traits.add(simpleSixtyTrait());

        return traits;
    }

    private static Trait simpleFiftyTrait() {
        return Trait.builder().number(50).build();
    }

    private static Trait simpleFiftyFiveTrait() {
        return Trait.builder().number(55).build();
    }

    private static Trait simpleSixtyTrait() {
        return Trait.builder().number(60).build();
    }


}
