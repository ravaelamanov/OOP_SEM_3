import DTO.Employee;
import util.AbstractRepositoryFactory;
import util.EntityConverter;

public class EmployeeManagementSystem extends BLLService{

    public EmployeeManagementSystem(AbstractRepositoryFactory factory) {
        super(factory);
    }

    public void createEmployee(Employee employee) {
        Entities.Employee dalEmployee = EntityConverter.convert(employee, employeeRepository);
        employeeRepository.add(dalEmployee);
        employee.setId(dalEmployee.getID());
    }

    public void updateEmployee(Employee employee) {
        employeeRepository.update(EntityConverter.convert(employee, employeeRepository));
    }

    public void addSlave(Employee master, Employee slave) {
        master.addSlave(slave);
        updateEmployee(master);
    }
}
