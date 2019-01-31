package uz.pdp.esg.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.esg.collection.Category;
import uz.pdp.esg.repository.CategoryRepository;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public void saveCategory(Category category) {
        try{
            categoryRepository.save(category);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
