package net.lilydev.endothermia.recipe;

import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;
import net.lilydev.endothermia.Endothermia;
import net.lilydev.endothermia.block.frostburner.FrostburnerInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.Recipe;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.RecipeType;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.World;

import java.util.Optional;

public class FrostburnerRecipe implements Recipe<FrostburnerInventory> {
    private final Ingredient input;
    private final ItemStack output;
    private final Identifier identifier;

    public FrostburnerRecipe(Identifier identifier, ItemStack output, Ingredient input) {
        this.input = input;
        this.output = output;
        this.identifier = identifier;
    }

    public Ingredient getInput() {
        return this.input;
    }

    @Override
    public boolean matches(FrostburnerInventory inventory, World world) {
        return this.input.test(inventory.getStack(1));
    }

    @Override
    public ItemStack craft(FrostburnerInventory inventory) {
        return this.getOutput().copy();
    }

    @Override
    public boolean fits(int width, int height) {
        return true;
    }

    @Override
    public ItemStack getOutput() {
        return this.output;
    }

    @Override
    public Identifier getId() {
        return this.identifier;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return Serializer.INSTANCE;
    }

    @Override
    public RecipeType<?> getType() {
        return Type.INSTANCE;
    }

    public static class Type implements RecipeType<FrostburnerRecipe> {
        private Type() {}
        public static final Type INSTANCE = new Type();
        public static final String NAME = "frostburner_recipe";
    }

    public static class Serializer implements RecipeSerializer<FrostburnerRecipe> {
        private Serializer() {}

        public static final Serializer INSTANCE = new Serializer();

        public static final Identifier IDENTIFIER = new Identifier(Endothermia.MODID, "frostburner_recipe");

        @Override
        public FrostburnerRecipe read(Identifier id, JsonObject json) {
            try {
                JsonObject input = json.get("input").getAsJsonObject();
                Identifier output = Identifier.tryParse(json.get("output").getAsString());
                int amount = json.get("amount").getAsInt();

                Optional<Item> optional = Registry.ITEM.getOrEmpty(output);
                if (optional.isPresent()) {
                    ItemStack outputStack = new ItemStack(optional.get(), amount);
                    return new FrostburnerRecipe(id, outputStack, Ingredient.fromJson(input));
                } else {
                    throw new JsonSyntaxException("Item '" + output + "' does not exist?");
                }
            } catch (JsonSyntaxException|IllegalStateException exception) {
                Endothermia.LOGGER.error("Frostburner recipe '" + id + "' is invalid! Reason: " + exception.getMessage());
            }

            return null;
        }

        @Override
        public FrostburnerRecipe read(Identifier id, PacketByteBuf buf) {
            Ingredient input = Ingredient.fromPacket(buf);
            ItemStack output = buf.readItemStack();
            return new FrostburnerRecipe(id, output, input);
        }

        @Override
        public void write(PacketByteBuf buf, FrostburnerRecipe recipe) {
            recipe.getInput().write(buf);
            buf.writeItemStack(recipe.getOutput());
        }
    }
}
