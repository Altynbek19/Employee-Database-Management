package org.example.emplyeemanagerapp;


import java.util.ArrayList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.DatePicker;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import javafx.scene.control.ListView;


public class HelloController {

    private List<Employee> employees = new ArrayList<>();

    // Метод для поиска сотрудника по имени
    private Employee findEmployeeByName(String name) {
        for (Employee employee : employees) {
            if (employee.getName().equalsIgnoreCase(name)) {
                return employee; // Возвращаем найденного сотрудника
            }
        }
        return null; // Если сотрудник не найден
    }

    @FXML
    private Label employeeIdLabel;
    @FXML
    private TextField nameField;
    @FXML
    private TextField positionField;
    @FXML
    private TextField salaryField;
    @FXML
    private DatePicker hireDatePicker;
    @FXML
    private TextField searchField; // Поле для поиска сотрудников по имени или ID

    @FXML
    private ListView<String> employeeListView; // ListView для отображения списка сотрудников



    EmployeeDAO employeeDAO = new EmployeeDAO();

    @FXML
    void onCreate(ActionEvent event) {
        // Получаем текущую дату для hireDate
        LocalDate currentDate = LocalDate.now();
        Instant instant = currentDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant();
        Date hireDate = Date.from(instant);

        // Создаем нового сотрудника
        Employee newEmployee = new Employee(0, nameField.getText(), positionField.getText(), 0.0, hireDate);

        // Устанавливаем имя, должность и зарплату
        newEmployee.setName(nameField.getText());
        newEmployee.setPosition(positionField.getText());

        // Преобразуем строку из поля salaryField в число
        try {
            double salary = Double.parseDouble(salaryField.getText());
            newEmployee.setSalary(salary);
        } catch (NumberFormatException e) {
            System.out.println("Invalid salary format: " + salaryField.getText());
            return;
        }

        // Сохраняем нового сотрудника в базе данных и получаем его ID
        int newId = employeeDAO.insert(newEmployee);
        newEmployee.setId(newId);

        // Отображаем ID сотрудника
        employeeIdLabel.setText("Employee ID: " + newEmployee.getId());
    }

    @FXML
    void onUpdate(ActionEvent event) {
        String searchTerm = searchField.getText();
        List<Employee> employees = employeeDAO.findAll();

        Employee employeeToUpdate = null;
        for (Employee employee : employees) {
            if (employee.getName().equalsIgnoreCase(searchTerm) || Integer.toString(employee.getId()).equals(searchTerm)) {
                employeeToUpdate = employee;
                break;
            }
        }

        if (employeeToUpdate != null) {
            employeeToUpdate.setName(nameField.getText());
            employeeToUpdate.setPosition(positionField.getText());
            try {
                double salary = Double.parseDouble(salaryField.getText());
                employeeToUpdate.setSalary(salary);
            } catch (NumberFormatException e) {
                System.out.println("Invalid salary format: " + salaryField.getText());
                return;
            }

            employeeDAO.update(employeeToUpdate);
            employeeIdLabel.setText("Employee ID: " + employeeToUpdate.getId() + " has been updated");
        } else {
            System.out.println("Employee not found.");
        }
    }

    @FXML
    void onDelete(ActionEvent event) {
        String searchTerm = searchField.getText();
        List<Employee> employees = employeeDAO.findAll();

        Employee employeeToDelete = null;
        for (Employee employee : employees) {
            if (employee.getName().equalsIgnoreCase(searchTerm) || Integer.toString(employee.getId()).equals(searchTerm)) {
                employeeToDelete = employee;
                break;
            }
        }

        if (employeeToDelete != null) {
            employeeDAO.delete(employeeToDelete);
            employeeIdLabel.setText("Employee with ID " + employeeToDelete.getId() + " has been deleted.");
        } else {
            System.out.println("Employee not found.");
        }
    }
    @FXML
    private void onRead(ActionEvent event) {
        String name = nameField.getText();
        String position = positionField.getText();
        String salary = salaryField.getText();

        Employee employee = employeeDAO.findAll().stream()
                .filter(e -> e.getName().equalsIgnoreCase(name))
                .findFirst()
                .orElse(null);

        if (employee != null) {
            employeeIdLabel.setText("Employee ID: " + employee.getId());
            nameField.setText(employee.getName());
            positionField.setText(employee.getPosition());
            salaryField.setText(String.valueOf(employee.getSalary()));
        } else {
            employeeIdLabel.setText("Employee not found!");
        }
    }

    // Метод для отображения всех сотрудников
    @FXML
    private void onShowAllEmployees(ActionEvent event) {
        List<Employee> employees = employeeDAO.findAll();
        employeeListView.getItems().clear(); // Очищаем список перед обновлением

        for (Employee employee : employees) {
            employeeListView.getItems().add("ID: " + employee.getId() + ", Name: " + employee.getName() + ", Position: " + employee.getPosition()+", Salary: "+employee.getSalary()+ ", Hire date: " + employee.getHireDate());
        }
    }



}

