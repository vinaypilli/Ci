package com.teamcomputers.service;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import com.teamcomputers.dto.CategoryDto;
import com.teamcomputers.model.Category;
import com.teamcomputers.repository.CategoryRepository;
import com.teamcomputers.service.CategoryService;

public class CategoryServiceTest {
	@InjectMocks
	   private  CategoryService categoryService;
		@Mock
		private CategoryRepository categoryrepository;
		@Test
		void addDto()
		{
			try
			{
			CategoryDto myDto = new CategoryDto();
			myDto.setCategoryId(1);
			myDto.setCategoryName("ravi");
			myDto.setCreatedBy(1);
			myDto.setCreatedDate(null);
			myDto.setStatus(true);
			myDto.setUpdatedBy(2);
			myDto.setUpdatedDate(null);
				
			Mockito.when(categoryrepository.save(any(Category.class))).thenReturn(new Category());
			categoryService.add(myDto);
			
			//CategoryDto newCatDto=categoryService.add(myDto);
			 ArgumentCaptor<Category> entityCaptor = ArgumentCaptor.forClass(Category.class);
		        verify(categoryrepository).save(entityCaptor.capture());
		        Category capturedEntity = entityCaptor.getValue();
		        assertEquals("ravi", capturedEntity.getCategoryName());
		       assertEquals(1, capturedEntity.getCategoryId());
			
		}catch(Exception e)
		{
			System.out.println(e);
		}
		
		}
		

}
