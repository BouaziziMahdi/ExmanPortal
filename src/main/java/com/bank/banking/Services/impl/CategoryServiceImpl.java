package com.bank.banking.Services.impl;

import com.bank.banking.Services.Category;
import com.bank.banking.dto.Categorydto;
import com.bank.banking.repositories.CategoryRepository;
import com.bank.banking.validator.ObjectsValidator;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements Category {
    private final CategoryRepository categoryRepository;
    private final ObjectsValidator<Categorydto> validator ;
    @Override
    public Integer addCategory(Categorydto categoryDto) {
        validator.validate( categoryDto );
        var category =Categorydto.toEntity( categoryDto );
        return categoryRepository.save( category ).getId();
    }

    @Override
    public Integer updateCategory(Categorydto categorydto) {
        validator.validate( categorydto );
        var category =Categorydto.toEntity( categorydto );
        return categoryRepository.save( category ).getId();
    }

    @Override
    public List<Categorydto> getAllCategories() {
        return categoryRepository.findAll().stream()
                .map( Categorydto::FromEntity )
                .collect( Collectors.toList() );

    }

    @Override
    public Categorydto getCategoryById(Integer id) {
        return categoryRepository.findById(id).stream()
                .map( Categorydto::FromEntity )
                .findFirst()
                .orElseThrow(() -> new EntityNotFoundException("No category was found with the ID :" + id));
    }

    @Override
    public void deleteCategory(Integer id) {
        categoryRepository.deleteById(id);
    }
}
