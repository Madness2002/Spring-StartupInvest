package pe.edu.upc.SpringStartupInvest.service.crud.impl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import pe.edu.upc.SpringStartupInvest.model.entity.Category;
import pe.edu.upc.SpringStartupInvest.model.repository.CategoryRepository;
import pe.edu.upc.SpringStartupInvest.service.crud.CategoryService;

import org.springframework.data.jpa.repository.JpaRepository;

@Service
public class CategoryServiceImpl implements CategoryService {
	

	
	@Autowired
	private CategoryRepository categoryRepository;

	@Override
	public JpaRepository<Category, Integer> getJpaRepository() {
		return categoryRepository;
	}

	@Override
	public List<Category> findByState(boolean state) throws Exception {
		return categoryRepository.findByState(state);
	}

	@Override
	public List<Category> findByName(String name) throws Exception {
		
		return categoryRepository.findByName(name);
	}

	
	
}
