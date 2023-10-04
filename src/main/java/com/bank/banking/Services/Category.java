package com.bank.banking.Services;

import com.bank.banking.dto.Categorydto;

import java.util.List;

public interface Category {
    public Integer addCategory(Categorydto category);
    public Integer updateCategory(Categorydto category);
    public List<Categorydto> getAllCategories();
    public Categorydto getCategoryById(Integer id);
    public void deleteCategory(Integer id);
}
