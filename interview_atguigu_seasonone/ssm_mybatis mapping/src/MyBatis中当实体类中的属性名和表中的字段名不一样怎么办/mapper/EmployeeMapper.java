package MyBatis中当实体类中的属性名和表中的字段名不一样怎么办.mapper;


import MyBatis中当实体类中的属性名和表中的字段名不一样怎么办.entities.Employee;

public interface EmployeeMapper {

	Employee getEmployeeById(Integer id);
}
