package org.truonglq.cqrs.service;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
//@RequiredArgsConstructor
public class MaterializedViewRefresher {

    private final EntityManager entityManager;

    public MaterializedViewRefresher(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Transactional
    @Scheduled(fixedRate = 5000L)
    public void refresh() {
        entityManager.createNativeQuery("call refresh_mat_view();").executeUpdate();
    }

}
