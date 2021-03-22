package com.peasch.repository.dao.Impl;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


@Transactional
@Repository
public class UserDaoImpl{

    @PersistenceContext
    private EntityManager em;

}
