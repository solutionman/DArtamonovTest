package ru.artamonov;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class CompanyServiceTest {
    @Test
    public void whenCompanySingleThenTopTheSameCompany(){
        Company root = new Company(1,null, 0);
        ICompanyService service = new CompanyServiceImpl();
        Company result = service.getTopLevelParent(root);
        assertThat(result, is(root));
    }

    @Test
    public void whenCompanyInChainThenTopCompanyWithParentNull(){
        Company root = new Company(1,null, 0);
        Company manager = new Company(2,root, 0);
        Company test = new Company(3, manager, 0);
        ICompanyService service = new CompanyServiceImpl();
        Company result = service.getTopLevelParent(test);
        assertThat(result, is(root));
    }

    @Test
    public void whenCompanySingleThenEmployeesOnlyInIt(){
        Company root = new Company(1,null, 1);
        ICompanyService service = new CompanyServiceImpl();
        List<Company> companies = Arrays.asList(root);
        long result = service.getEmployeeCountForCompanyAndChildren(root, companies);
        assertThat(result, is(1L));
    }

    @Test
    public void whenThreeCompaniesThenSumEmployees(){
        Company root = new Company(1,null, 10);
        Company secondCompany = new Company(2, root, 12);
        Company thirdCompany = new Company(3, secondCompany, 15);
        ICompanyService service = new CompanyServiceImpl();
        List<Company> companies = Arrays.asList(root, secondCompany, thirdCompany);
        long result = service.getEmployeeCountForCompanyAndChildren(root, companies);
        assertThat(result, is(37L));
    }
}
