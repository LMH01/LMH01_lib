package com.github.lmh01.lmh01_lib.init;

import com.github.lmh01.lmh01_lib.ModEventSubscriber;
import com.github.lmh01.lmh01_lib.util.References;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;

public class ModItemGroups{

    public static final ItemGroup TAB_LMH01_LIB = new ItemGroup("lmh01_lib") {
        @Override
        public ItemStack createIcon() {
            return new ItemStack(ModItems.TEST_ITEM);
        }
    };
}
