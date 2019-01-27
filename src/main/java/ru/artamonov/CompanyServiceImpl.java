package ru.artamonov;

import java.util.List;

public class CompanyServiceImpl implements ICompanyService {
    @Override
    public Company getTopLevelParent(Company child){
        Company result = child;
        if(child.getParent() != null){
            result = this.getTopLevelParent(child.getParent());
        }
        return result;
    }

    @Override
    public long getEmployeeCountForCompanyAndChildren(Company company, List<Company> companies){
        long result = company.getEmployeeCount();
        for(Company com : companies){
            if(com.getParent() != null && com.getParent().equals(company)){
                result += this.getEmployeeCountForCompanyAndChildren(com, companies);
            }
        }
        return result;
    }
}
