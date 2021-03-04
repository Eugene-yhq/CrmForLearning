package com.demo.crm.workbench.service;

import com.demo.crm.workbench.domain.Clue;

public interface ClueService {
    boolean save(Clue c);

    Clue detail(String id);

    boolean unbund(String id);
}
