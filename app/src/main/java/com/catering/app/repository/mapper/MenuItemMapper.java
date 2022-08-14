package com.catering.app.repository.mapper;

import com.catering.app.domain.models.MenuItem;
import com.catering.app.repository.entity.MenuItemEntity;

public class MenuItemMapper {
    public MenuItem mapToMenuItem(MenuItemEntity menuItemEntity) {
        return new MenuItem(
            menuItemEntity.getId(),
            menuItemEntity.getName(),
            menuItemEntity.getWeightOutput(),
            menuItemEntity.getCoast()
        );
    }
}
