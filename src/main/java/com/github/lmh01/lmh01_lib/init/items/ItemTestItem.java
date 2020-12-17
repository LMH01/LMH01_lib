package com.github.lmh01.lmh01_lib.init.items;

import com.github.lmh01.lmh01_lib.helpers.DebugHelper;
import com.github.lmh01.lmh01_lib.helpers.UpdateCheckerHelper;
import com.github.lmh01.lmh01_lib.util.Debug;
import com.github.lmh01.lmh01_lib.util.LoadingSummary;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

import java.util.concurrent.TimeUnit;

public class ItemTestItem extends Item {
    public ItemTestItem(Properties properties) {
        super(properties);
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
        //LoadingSummary.showLoadingSummary();
        //DebugHelper.sendDebugInformation("timesRun: " + UpdateCheckerHelper.timesRun, 2, 0);
        Debug.testUpdateChecker();
        return super.onItemRightClick(worldIn, playerIn, handIn);
    }
}
