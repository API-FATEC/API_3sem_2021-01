package com.fatec.mom.domain.block;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BlockRepository extends JpaRepository<Block, Long> {

    List<Block> findAllBySection(String section);

    List<Block> findAllBySectionAndSubSection(String section, String subSection);
}
