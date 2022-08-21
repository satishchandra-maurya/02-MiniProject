package in.satish.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.satish.entity.PlanCategory;
import in.satish.repo.PlanCategoryRepo;

@Service
public class CategoryServiceImpl implements CategoryService{

	@Autowired
	private PlanCategoryRepo pcr;
	
	@Override
	public String upsert(PlanCategory pc) {
		Integer categoryId = pc.getCategoryId();
		pcr.save(pc);
		if(categoryId==null)
			return "Saved Successfully!..";
		else
			return "Updated Successfully !..";
	}

	@Override
	public List<PlanCategory> getAllCategories() {
		//List<PlanCategory> findAll = pcr.findAll();
		return pcr.findAll();
	}

	@Override
	public PlanCategory getByCatId(Integer categoryId) {
		// TODO Auto-generated method stub
		return pcr.findById(categoryId).get();
	}

	@Override
	public String deleteCategory(Integer categoryId) {
		pcr.deleteById(categoryId);
		return "Category Deleted...";
	}

	@Override
	public boolean changeCategoryStatus(Integer categoryId, String status) {
		Optional<PlanCategory> findById = pcr.findById(categoryId);
		
		if(findById.isPresent())
		{
			PlanCategory planCategory = findById.get();
			planCategory.setActiveSw(status);
			pcr.save(planCategory);
			return true;
			
		}
		return false;
	}

}
