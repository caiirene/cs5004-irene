package organization;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

import util.ContractEmployee;
import util.Gender;
import util.Organization;

/**
 * This class represents an organization with employees.
 */

public class OrganizationImpl implements Organization {
  private Employee root;

  public OrganizationImpl(String nameCEO, double pay, Gender gender) {
    root = new InternalEmployee(nameCEO,pay,gender);
  }

  @Override
  public void addEmployee(String name, double pay, Gender gender, String supervisorName) {
    Employee newEmployee = new NonManagerEmployee(name,pay,gender);
    root = root.addSupervisee(supervisorName,newEmployee);
  }

  @Override
  public void addContractEmployee(String name, double pay, Gender gender, int
          endDate, int endMonth, int endYear, String supervisorName) {
    Employee newEmployee = new ContractEmployee(name,pay,gender,endDate,endMonth,
            endYear);
    root = root.addSupervisee(supervisorName,newEmployee);
  }

  @Override
  public int getSize() {
    return root.count(b -> true);
  }

  @Override
  public int getSizeByGender(Gender gender) {
    return root.count(b -> b.getGender() == gender);
  }

  @Override
  public List<String> allEmployees() {
    return root.toList().stream().map(e->e.getName()).collect(Collectors
            .toList());
  }

  @Override
  public int countPayAbove(double amount) {
    return root.count(b -> b.getAnnualPay() > amount);
  }

  @Override
  public int terminatedBefore(int date,int month,int year) {
    LocalDate threshold;

    try {
      threshold = LocalDate.of(year,month,date);
    }
    catch (DateTimeException e) {
      return 0;
    }
    return root.count(b->{
      if (b.getEmploymentEndDate().equals("XXXXXXXX"))
        return false;
      else {
        LocalDate d = LocalDate.parse(b.getEmploymentEndDate(),
                DateTimeFormatter.ofPattern("MMddyyyy"));
        return d.compareTo(threshold)<0;
      }
    });
  }
}