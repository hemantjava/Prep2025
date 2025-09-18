# A country is big if:
it has an area of at least three million (i.e., 3000000 km2), or
it has a population of at least twenty-five million (i.e., 25000000).
```sql
SELECT name, population, area
FROM world
WHERE area>=3000000 OR population>=25000000
```

```sql
SELECT 
    D.dept_name AS dept_name, 
    COUNT(E.emp_id) AS employee_count, 
    AVG(E.salary) AS avg_sal
FROM departments D
LEFT JOIN employees E ON E.dept_id = B.dept_id
GROUP BY B.dept_name;
```
# Finding all the Employees Whose Salaries are Higher Than those of their Respective Departments
```sql
SELECT *
FROM geeksforgeeks g1
WHERE salary > (select avg(salary) from geeksforgeeks g2 where g2.department = g1.department);
```
  OR with join
```sql
SELECT A.name, B.dept_name, A.salary
FROM employees A
JOIN departments B
  ON A.dept_id = B.dept_id
WHERE A.salary > (
    SELECT AVG(e1.salary)
    FROM employees e1
    WHERE A.dept_id = e1.dept_id
);
``` 
## OTHER
```sql
elect
B.dept_name as dept_name, COUNT(A.emp_id) as employee_count, AVG(salary) as avg_sal
FROM departments B
LEFT JOIN employees A
ON  A.dept_id = B.dept_id
GROUP BY B.dept_name
```
## Employee Bonus
```sql
select E.name, B.bonus from Employee E
left join Bonus B on (E.empId = B.empId)
where B.bonus < 1000 or b.bonus is null;

```
