package com.preciseIT.repos;

import com.preciseIT.entities.Company;
import org.springframework.data.repository.CrudRepository;

public interface CompanyRepository extends CrudRepository<Company, Integer> {
}
