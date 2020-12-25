package Repositories;

import Entities.Employee;
import Infrastructure.IRepository;

import java.util.Collection;

public interface EmployeeRepository extends IRepository<Employee> {
    default Collection<Employee> getSlaves(Employee employee) {
        return find(slave -> slave.getMaster() != null && slave.getMaster().getID() == employee.getID());
    }
}
