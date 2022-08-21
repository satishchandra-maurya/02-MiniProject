package in.satish.service;

import java.util.List;

import in.satish.entity.PlanCategory;

public interface CategoryService {

	public String upsert(PlanCategory pc);
	public List<PlanCategory> getAllCategories();
	public PlanCategory getByCatId(Integer categoryId);
	public String deleteCategory(Integer categoryId);
	public boolean changeCategoryStatus(Integer categoryId, String status);
}
