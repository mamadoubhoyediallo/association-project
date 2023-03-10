package org.mbd.aeesgs.services;

import java.util.List;

public interface IBase <T> {
    public void delete(Long id);
    public List<T> findAll();
    public T findById(Long id);
}
