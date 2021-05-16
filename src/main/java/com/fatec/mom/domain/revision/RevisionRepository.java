package com.fatec.mom.domain.revision;


import com.fatec.mom.domain.document.Document;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * A interface <code>RevisionRepository</code> é o repositório para transações no banco de dados para objetos do tipo
 * <code>Revision</code>.
 *
 * @author Devanir
 * @version v01 07/05/2021
 */

@Repository
public interface RevisionRepository extends JpaRepository<Revision, Long> {

    Optional<Revision> findByName(final String name);

    Optional<Revision> findByDocumentAndStatus(final Document document, final RevisionStatus status);

    @Query(value = "SELECT NUMERO FROM ( select replace(rev_cod,'REVISION','') as numero from mom_revisao r join mom_documento d on r.doc_cod = d.doc_cod where d.doc_cod = :documentId ORDER BY NUMERO DESC) WHERE ROWNUM = 1", nativeQuery = true)
    String getLastRevisionCode(@Param("documentId") final Long documentId);

    @Query(value = "update mom_revisao set rev_status = 'FINISHED' where rev_cod = (select * from (select rev_cod from mom_revisao where doc_cod = :documentId order by rev_created_date desc) where rownum = 1)", nativeQuery = true)
    void closeLastRevision(@Param("documentId") final Long documentId);

}
