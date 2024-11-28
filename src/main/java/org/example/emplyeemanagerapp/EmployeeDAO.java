package org.example.emplyeemanagerapp;

import java.sql.*;
import java.util.List;
import java.util.ArrayList; // Импортируем класс ArrayList

public class EmployeeDAO implements DAOInterface<Employee>{
    private Connection conn;

    public EmployeeDAO(){
        String url = "jdbc:postgresql://localhost:5432/employee_db"; // Employee_db is a name of localhost database
        String username = "postgres";
        String password = "190306";

        try {
            conn = DriverManager.getConnection(url, username, password);
            System.out.println("Database is successfully connected...");
        }
        catch(SQLException e){
            System.out.println(e.toString());
        }

    }

    @Override
    public int insert(Employee entity) {
        int newid = 0;

        try {
            String sql = "INSERT INTO Employee (name, position, salary, hireDate)" +
                    " VALUES (?, ?, ?, ?)";
            PreparedStatement preparedStatement = this.conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, entity.getName());
            preparedStatement.setString(2, entity.getPosition());
            preparedStatement.setDouble(3, entity.getSalary());
            preparedStatement.setDate(4, new Date(entity.getHireDate().getTime()));

            int affectedRows = preparedStatement.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Creating task failed.");
            }

            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {

                if (generatedKeys.next()) {
                    newid = generatedKeys.getInt(1);
                }
            }

            System.out.println("New record is added with id: " + newid);
        } catch (SQLException e) {
            System.out.println(e.toString());
        }


        return newid;
    }

    @Override
    public Employee read(int id) {
        Employee employee = null;
        try {
            String sql = "SELECT * FROM Employee WHERE id = ?";
            PreparedStatement preparedStatement = this.conn.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                employee = new Employee(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("position"),
                        resultSet.getDouble("salary"),
                        resultSet.getDate("hireDate")
                );
            }
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
        return employee;
    }

    @Override
    public void update(Employee entity) {
        try {
            String sql = "UPDATE Employee SET name = ?, position = ?, salary = ?, hireDate = ? WHERE id = ?";
            PreparedStatement preparedStatement = this.conn.prepareStatement(sql);
            preparedStatement.setString(1, entity.getName());
            preparedStatement.setString(2, entity.getPosition());
            preparedStatement.setDouble(3, entity.getSalary());
            preparedStatement.setDate(4, new Date(entity.getHireDate().getTime()));
            preparedStatement.setInt(5, entity.getId());

            int affectedRows = preparedStatement.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Updating employee failed.");
            }

            System.out.println("Employee with ID " + entity.getId() + " is updated.");
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
    }

    @Override
    public void delete(Employee entity) {
        try {
            String sql = "DELETE FROM Employee WHERE id = ?";
            PreparedStatement preparedStatement = this.conn.prepareStatement(sql);
            preparedStatement.setInt(1, entity.getId());

            int affectedRows = preparedStatement.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Deleting employee failed.");
            }

            System.out.println("Employee with ID " + entity.getId() + " is deleted.");
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
    }

    @Override
    public List<Employee> findAll() {
        List<Employee> employees = new ArrayList<>();
        try {
            String sql = "SELECT * FROM Employee";
            Statement statement = this.conn.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                Employee employee = new Employee(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("position"),
                        resultSet.getDouble("salary"),
                        resultSet.getDate("hireDate")
                );
                employees.add(employee);
            }
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
        return employees;
    }

}
