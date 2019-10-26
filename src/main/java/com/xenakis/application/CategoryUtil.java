package com.xenakis.application;

import com.xenakis.model.Category;
import com.xenakis.service.DatabaseUtil;

public class CategoryUtil {

    public static String getCategoryGreekName(String name) {
        Category category;
        try {
            category = DatabaseUtil.getCategory(name);
        }
        catch (Exception e) {
            return "-";
        }
        return category.getGreekName();
    }
}
