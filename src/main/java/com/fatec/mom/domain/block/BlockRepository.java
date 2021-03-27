package com.fatec.mom.domain.block;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * A interface <code>BlockRepository</code> provê o repositório para transações no banco de dados para objetos do tipo
 * <code>Block</code>.
 *
 * @author Tobias Lino
 * @version v01 26/03/2021
 */
@Repository
public interface BlockRepository extends JpaRepository<Block, Long> {

    List<Block> findAllBySection(String section);

    List<Block> findAllBySectionAndSubSection(String section, String subSection);
}
