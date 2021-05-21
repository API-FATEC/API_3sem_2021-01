package com.fatec.mom.domain.revision;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface RevisionTagRepository extends JpaRepository<RevisionTag, Long> {

    @Query(value = "SELECT * FROM MOM_REVISAO_TAG WHERE RTAG_VALUE = (SELECT MAX(RTAG_VALUE) FROM MOM_REVISAO_TAG WHERE REV_COD = :revision_id)", nativeQuery = true)
    RevisionTag getLastTagFrom(@Param("revision_id") final Long revisionId);
}
