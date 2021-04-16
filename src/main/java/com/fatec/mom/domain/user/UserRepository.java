package com.fatec.mom.domain.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * A interface <code>UserRepository</code> provê o repositório para transações no banco de dados para objetos do tipo
 * <code>User</code>.
 *
 * @author Devanir
 * @version v01 15/04/2021
 */

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    List<User> findAll();
}