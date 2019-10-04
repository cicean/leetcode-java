package eBay;

import java.util.*;

public class ImplementHashCodeAndEqualsFunction {

  // Java program to create a map of employee
// and address using overriding
// hashCode and equals methods
import HashMap;
import Map;

  class Employee {
    private int empId;
    private String name;

    public Employee(int empId, String name)
    {
      this.empId = empId;
      this.name = name;
    }

    @Override public int hashCode()
    {
      final int prime = 31;
      int result = 1;
      result = prime * result + empId;
      result = prime * result +
          ((name == null) ? 0 : name.hashCode());
      return result;
    }

    @Override public boolean equals(Object obj)
    {
      if (this == obj)
        return true;
      if (obj == null)
        return false;
      if (getClass() != obj.getClass())
        return false;
      Employee other = (Employee)obj;
      if (empId != other.empId)
        return false;
      if (name == null) {
        if (other.name != null)
          return false;
      } else if (!name.equals(other.name))
        return false;
      return true;
    }
  }

  class Address {
    private int houseNo;
    private String streetName;
    private String city;
    private int pinCode;

    public Address(int houseNo, String streetName,
        String city, int pinCode)
    {
      this.houseNo = houseNo;
      this.streetName = streetName;
      this.city = city;
      this.pinCode = pinCode;
    }

    public String getAddress()
    {
      return houseNo + ", " + streetName + ", " +
          city + ", " + pinCode;
    }

  }

  public class Test {
    public static String getAddress(Map map, Employee emp)
    {
      Address adrs = (Address)map.get(emp);
      return adrs.getAddress();
    }
    public static void main(String[] args)
    {
      Employee emp1 = new Employee(110, "Sajid Ali Khan");
      Address adrs1 = new Address(304, "Marol Mahrisi",
          "Mumbai", 400069);

      Employee emp2 = new Employee(111, "Jaspreet Singh");
      Address adrs2 = new Address(203, "Seepz", "Mumbai",
          400093);

      Map<Employee, Address> map = new HashMap<>();
      map.put(emp1, adrs1);
      map.put(emp2, adrs2);

      System.out.println(Test.getAddress(map, new Employee(110,
          "Sajid Ali Khan")));
    }
  }

}
