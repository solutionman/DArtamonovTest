package ru.artamonov;

import java.util.List;

public interface ICompanyService {
    Company getTopLevelParent(Company child);

    long getEmployeeCountForCompanyAndChildren(Company company, List<Company> companies);
}
