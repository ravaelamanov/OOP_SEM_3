import DTO.DailyReport;
import DTO.Employee;
import util.AbstractRepositoryFactory;

public class EmployeeManagementSystem extends BLLService{

    public EmployeeManagementSystem(AbstractRepositoryFactory factory) {
        super(factory);
    }

    public void createEmployee(Employee employee) {
        employee.setDailyReport(new DailyReport());
        Entities.Employee dalEmployee = EntityConverter.convert(employee);
        employeeRepository.add(dalEmployee);
        employee.setId(dalEmployee.getID());
    }

    public void updateEmployee(Employee employee) {
        employeeRepository.update(EntityConverter.convert(employee));
    }

    public void addSlave(Employee master, Employee slave) {
        master.addSlave(slave);
        updateEmployee(master);
    }
}
