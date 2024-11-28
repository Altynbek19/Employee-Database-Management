# Employee-Database-Management

![image](https://github.com/user-attachments/assets/1da08ec1-8027-4899-83ad-ddb0af4fe497)![image](https://github.com/user-attachments/assets/9eb1669e-c203-44b7-a6de-2e1fda98877c)

 Мой проект умеет выводить все содержимое таблицы нажав на кнопку READ

## Скриншот базы данных

![image](https://github.com/user-attachments/assets/1d581559-323d-4862-b4c0-7f20eaf28f21)

## Подключение базы данных

1) создаем базу данных под  названием employee_db
2) Создаем таблицу employee
  CREATE TABLE employee (
    id SERIAL PRIMARY KEY,
    name VARCHAR(100),
    position VARCHAR(100),
    salary DOUBLE PRECISION,
    hireDate DATE
  );
3) И подкючаем базу данных

   ![image](https://github.com/user-attachments/assets/de3dc987-fde1-41b9-9ddb-8c20d23ae4ca)

## Запускаем приложение
Открываем файл HelloApplication.java и нажимаем на кнопку запуска

![image](https://github.com/user-attachments/assets/1eaf0549-8299-46e2-bf17-a09119aca0a2)

## Скриншоты (Как работает и так далее)

### с пустыми полями
![image](https://github.com/user-attachments/assets/e94502e7-e542-40f4-9371-d75996d22310)

### при создании сотрудника 
![image](https://github.com/user-attachments/assets/dbeebd18-9448-41f8-9fd3-0e35bb9a7a2f)

### После создания сотрудника
![image](https://github.com/user-attachments/assets/b6fb6f13-3759-471e-ab90-e4ec5e941c6d)


### Чтобы посмотреть всех сотрудников с базы данных надо нажать на кнопку Read
![image](https://github.com/user-attachments/assets/9042de25-bc7a-4971-8de2-ba485c135dc8)


### Чтобы обновить сотрудника надо обратиться по имени в специальном поле Update by name "имя сотрдуника которого хотите обновить"
Например хочу повысить зарплату сотруднику "Yrys" с 2000 на 2500

### До нажаматия кнопки Update
![image](https://github.com/user-attachments/assets/3fd14be6-2f81-4aba-bddf-b3a394c69cfc)

### После нажатия на кнопку Update
![image](https://github.com/user-attachments/assets/ad49c578-8bd5-4535-8197-684e4716ed46)

### Чтобы удалить сотрудника надо так же обратиться по по имени в специальном поле Delete by name "имя сотрудника которого хотите удалить"
Например хочу уволить сотрудника Yrys
### До нажатия на кнопку Delete 
![image](https://github.com/user-attachments/assets/9db7bfb8-4cac-4729-b554-c4ad2b4b852b)

### После нажатия на кнопку Delete
![image](https://github.com/user-attachments/assets/6757401c-80bc-4c83-8e9f-cc6f2a9e9bf0)

