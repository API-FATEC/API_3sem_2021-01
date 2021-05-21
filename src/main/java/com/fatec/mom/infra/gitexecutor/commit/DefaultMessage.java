package com.fatec.mom.infra.gitexecutor.commit;

import lombok.AllArgsConstructor;

import java.text.SimpleDateFormat;
import java.util.Date;

@AllArgsConstructor
public enum DefaultMessage {

    REVISION_CLOSED("Revision %s CLOSED on %s."),
    BLOCK_EDITED("Block %s EDITED on %s");

    private final String message;

    public String getMessageBy(final String message) {
        final var dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        return String.format(this.message, message, dateFormat.format(new Date()));
    }
}
