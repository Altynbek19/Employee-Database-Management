package org.example.emplyeemanagerapp;

import java.util.List;

public interface DAOInterface<E> {
    public int insert(Employee entity);

    public Employee read(int id);

    public void update(Employee entity);

    public void delete(Employee entity);

    public List<Employee> findAll();
}
