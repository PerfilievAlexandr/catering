package com.catering.app.servise.mapper;

import com.catering.app.model.domain.MenuItem;
import com.catering.app.model.entity.MenuItemEntity;

public class MenuItemEntityMapper {
    public MenuItem mapToMenuItem(MenuItemEntity menuItemEntity) {
        return new MenuItem(
            menuItemEntity.getId(),
            menuItemEntity.getName(),
            menuItemEntity.getWeightOutput(),
            menuItemEntity.getCoast()
        );
    }
}
