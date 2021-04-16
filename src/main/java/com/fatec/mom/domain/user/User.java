package com.fatec.mom.domain.user;

import com.fatec.mom.domain.document.Document;
import lombok.*;
import javax.persistence.*;

/**
 * A classe <code>User</code> representa a entidade MOM_USER do banco de dados.
 *
 * @author Devanir
 * @version v01 15/04/2021
 */

@Entity
@Table(name = "MOM_USER")
@SequenceGenerator(sequenceName = "MOM_USER_SQ", name = "MOM_USER_SQ", allocationSize = 1)
@Data @Builder @AllArgsConstructor @NoArgsConstructor
@EqualsAndHashCode(of = {"email", "action", "document"})

public class User {

    @Id
    @GeneratedValue(generator = "MOM_USER_SQ", strategy = GenerationType.SEQUENCE)
    @Column(name = "USR_COD", nullable = false)
    private Long id;

    @Column(name = "USR_EMAIL", nullable = false)
    private String email;

    @Column(name = "USR_ACTION", nullable = false)
    private String action;

    @Column(name = "USR_DOC", nullable = false)
    private int document;

    public enum HistoricAction{
        EDIT_CODELIST("Usuário %s editou a codelist do documento %s"),
        NEW_CODELIST("Usuário %s criou uma codelist do documento %s"),
        NEW_BLOCK("Usuário %s criou um novo bloco no documento %s");

        @Getter
        String description;

        HistoricAction(String description) {
            this.description = description;
        }

        public String getDescription(String email, Document document) {
            return String.format(this.description, email, document.getName());
        }
    }
}
