package in.satish.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import in.satish.entity.PlanCategory;
import in.satish.service.CategoryService;

@RestController
public class CategoryController {

	@Autowired
	private CategoryService cs;
	
	@PostMapping("/category")
	public ResponseEntity<String> saveCategory(@RequestBody PlanCategory planCategory){
		String msg = cs.upsert(planCategory);
		return new ResponseEntity<> (msg, HttpStatus.CREATED);
	}
	
	  @GetMapping("/allCatgory") public ResponseEntity<List<PlanCategory>>
	  findCategories(){ List<PlanCategory> allCategories = cs.getAllCategories();
	  return new ResponseEntity<> (allCategories, HttpStatus.OK); }
	 
	
	@DeleteMapping("/category/{categoryId}")
	public ResponseEntity<String> deleteCategory(@PathVariable Integer categoryId) {
		String msg = cs.deleteCategory(categoryId);
		return new ResponseEntity<>(msg, HttpStatus.OK);
	}
	
	@GetMapping("/category/{categoryId}")
	public ResponseEntity<PlanCategory> editCategory(@PathVariable Integer categoryId){
		PlanCategory plan = cs.getByCatId(categoryId);
		return new ResponseEntity<> (plan, HttpStatus.OK);
		
	}
	
	@PutMapping("/category")
	public ResponseEntity<String> updateCategory(@RequestBody PlanCategory planCategory){
		String msg = cs.upsert(planCategory);
		return new ResponseEntity<> (msg, HttpStatus.OK);
	}
	
	@PutMapping("/category/{categoryId}/{status}")
	public ResponseEntity<String> changeStatus(@PathVariable Integer categoryId, @PathVariable String status){
		String msg;
		boolean changeCategoryStatus = cs.changeCategoryStatus(categoryId, status);
		if(changeCategoryStatus)
			msg = "status changed !..";
		else
			msg = "status Not changed !..";
		return new ResponseEntity<> (msg, HttpStatus.OK);
		
	}
}
