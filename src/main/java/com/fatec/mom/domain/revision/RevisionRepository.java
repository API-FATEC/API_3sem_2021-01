package com.fatec.mom.domain.revision;


import org.springframework.data.jpa.repository.JpaRepository;
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

}
