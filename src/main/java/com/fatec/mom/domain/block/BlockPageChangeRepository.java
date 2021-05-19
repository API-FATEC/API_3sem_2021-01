package com.fatec.mom.domain.block;

import com.fatec.mom.domain.block.pagescomparator.changes.BlockPageChange;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BlockPageChangeRepository extends JpaRepository<BlockPageChange, Long> {

    Optional<BlockPageChange> findFirstByActualPageOrderBySavedDateDesc(final BlockPage page);
}
