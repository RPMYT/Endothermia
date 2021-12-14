package net.lilydev.endothermia.block;

import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.recipe.AbstractCookingRecipe;
import net.minecraft.recipe.RecipeType;
import net.minecraft.recipe.book.RecipeBookCategory;
import net.minecraft.screen.AbstractFurnaceScreenHandler;
import net.minecraft.screen.ScreenHandlerType;

public class FrostburnerScreenHandler extends AbstractFurnaceScreenHandler {
    protected FrostburnerScreenHandler(ScreenHandlerType<?> type, RecipeType<? extends AbstractCookingRecipe> recipeType, RecipeBookCategory category, int syncId, PlayerInventory playerInventory) {
        super(type, recipeType, category, syncId, playerInventory);
    }
}
