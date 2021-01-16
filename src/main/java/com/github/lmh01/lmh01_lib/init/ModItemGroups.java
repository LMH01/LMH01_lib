package com.github.lmh01.lmh01_lib.init;

import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;

public class ModItemGroups{

    public static final ItemGroup TAB_LMH01_LIB = new ItemGroup("lmh01_lib") {
        @Override
        public ItemStack createIcon() {
            return new ItemStack(ModItems.TEST_ITEM);
        }
    };
}
