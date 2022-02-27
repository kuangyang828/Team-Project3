package junit;

import domain.Employee;
import org.junit.jupiter.api.Test;
import service.NameListService;
import service.TeamException;

/**
 * 对NameListService类的测试
 */

public class NameListServiceTest {

    @Test
    public void testGrtAllEmployess() {
        NameListService nameListService = new NameListService();
        Employee[] allEmployees = nameListService.getAllEmployees();
        for (int i = 0; i < allEmployees.length; i++) {
            System.out.println(allEmployees[i]);
        }
    }

    @Test
    public void testGetEmployee() {
        NameListService nameListService = new NameListService();
        int id = 1;
        id = 101;
        try {
            Employee employee = nameListService.getEmployee(id);
            System.out.println(employee);
        } catch (TeamException e) {
            System.out.println(e.getMessage());;
        }
    }
}
