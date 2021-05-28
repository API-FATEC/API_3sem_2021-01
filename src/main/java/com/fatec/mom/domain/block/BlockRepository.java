package com.fatec.mom.domain.block;

import com.fatec.mom.domain.trait.Trait;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.Set;

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

    //É pra pesquisar todos os blocks de acordo com o Status (closed, in revision, opened)
    List<Block> findAllByStatus(BlockStatus Status);

    @Query(value = "select b.* from mom_bloco b " +
            "join mom_traco_blc tb on b.blc_cod = tb.blc_cod " +
            "join mom_traco t on t.tra_cod = tb.tra_cod " +
            "where b.doc_cod = :document_id and t.tra_num = :trait_cod order by b.blc_order", nativeQuery = true)
    List<Block> findAllByTraitNumberAndDocumentId(@Param("trait_cod") Integer trait,
                                                  @Param("document_id") Long documentId);

    @Query(value = "select b.* from mom_bloco b " +
            "join mom_traco_blc tb on b.blc_cod = tb.blc_cod " +
            "join mom_traco t on t.tra_cod = tb.tra_cod " +
            "where b.doc_cod = :document_id " +
                "and t.tra_num = :trait " +
                "and b.blc_nome = 'LEP'", nativeQuery = true)
    Block findLEPOf(@Param("trait") Integer trait,
                    @Param("document_id") Long documentId);
}
