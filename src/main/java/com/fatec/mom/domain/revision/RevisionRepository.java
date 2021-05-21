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

    Optional<Revision> findByNameAndDocument_Id(final String name, final Long docId);

    Optional<Revision> findByDocumentAndStatus(final Document document, final RevisionStatus status);

    @Query(value = "SELECT NUMERO FROM ( select replace(r.rev_name,'REVISION','') as numero from mom_revisao r join mom_documento d on r.doc_cod = d.doc_cod where d.doc_cod = :documentId ORDER BY NUMERO DESC) WHERE ROWNUM = 1", nativeQuery = true)
    String getLastRevisionCode(@Param("documentId") final Long documentId);

    @Query(value = "select * from mom_revisao where doc_cod = :documentId and rev_status='OPENED'", nativeQuery = true)
    Revision getOpenedRevision(@Param("documentId") final Long documentId);

    @Query(value = "SELECT * FROM (SELECT * FROM MOM_REVISAO R WHERE DOC_COD = :documentId order by rev_created_date desc) WHERE ROWNUM = 1", nativeQuery = true)
    Revision getLastRevision(@Param("documentId") Long documentId);
}
