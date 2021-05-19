package com.fatec.mom.domain.block;

import com.fatec.mom.domain.block.pagescomparator.changes.BlockPageChange;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.LinkedList;
import java.util.List;

@Service
public class BlockPageChangesService {

    private final BlockPageChangeRepository blockPageChangeRepository;

    @Autowired
    public BlockPageChangesService(BlockPageChangeRepository blockPageChangeRepository) {
        this.blockPageChangeRepository = blockPageChangeRepository;
    }

    @Transactional
    public List<BlockPageChange> getAllChangesFrom(final Block block) {
        final List<BlockPageChange> changes = new LinkedList<>();

        block.getPages().forEach(page -> {
            final var lastPageChange = blockPageChangeRepository
                    .findFirstByActualPageOrderBySavedDateDesc(page);
            lastPageChange.ifPresent(changes::add);
        });

        return changes;
    }

    @Transactional
    public List<BlockPageChange> saveAll(final List<BlockPageChange> changes) {
        return blockPageChangeRepository.saveAll(changes);
    }
}
