package Repositories;

import Entities.Employee;
import Infrastructure.IRepository;

public class EmployeeFileRepository implements IRepository<Employee> {
    @Override
    public Iterable<Employee> getAll() {
        return null;
    }

    @Override
    public Employee get(int id) {
        return null;
    }

    @Override
    public void add(Employee entity) {

    }

    @Override
    public void update(Employee entity) {

    }

    @Override
    public void delete(int id) {

    }
}
