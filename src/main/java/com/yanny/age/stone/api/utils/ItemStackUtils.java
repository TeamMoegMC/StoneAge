package com.yanny.age.stone.api.utils;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.*;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.core.Direction;
import net.minecraft.core.NonNullList;
import net.minecraftforge.client.ForgeHooksClient;

import javax.annotation.Nonnull;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

import static net.minecraft.client.renderer.block.model.ItemTransforms.*;

import net.minecraft.client.renderer.entity.ItemRenderer;

public class ItemStackUtils {

    public static CompoundTag serializeStacks(@Nonnull NonNullList<ItemStack> stacks) {
        ListTag listNBT = new ListTag();
        CompoundTag compoundNBT = new CompoundTag();

        stacks.forEach(itemStack -> {
            CompoundTag nbt = new CompoundTag();
            itemStack.save(nbt);
            listNBT.add(nbt);
        });

        compoundNBT.put("Items", listNBT);
        return compoundNBT;
    }

    public static CompoundTag serializeIngredients(@Nonnull List<Ingredient> ingredients) {
        ListTag listNBT = new ListTag();
        CompoundTag compoundNBT = new CompoundTag();

        ingredients.forEach(ingredient -> {
            ListTag itemsNBT = new ListTag();
            CompoundTag itemsCompound = new CompoundTag();

            for (ItemStack itemStack : ingredient.getItems()) {
                CompoundTag nbt = new CompoundTag();
                itemStack.save(nbt);
                itemsNBT.add(nbt);
            }

            itemsCompound.put("Items", itemsNBT);
            listNBT.add(itemsCompound);
        });

        compoundNBT.put("Items", listNBT);
        return compoundNBT;
    }

    public static void deserializeStacks(@Nonnull CompoundTag compoundNBT, @Nonnull NonNullList<ItemStack> stacks) {
        assert compoundNBT.contains("Items", 9/*Constants.NBT.TAG_LIST*/);
        ListTag listNBT = compoundNBT.getList("Items", 10/*Constants.NBT.TAG_COMPOUND*/);
        assert listNBT.size() == stacks.size();
        AtomicInteger cnt = new AtomicInteger(0);

        listNBT.forEach(nbt -> {
            ItemStack itemStack = ItemStack.of((CompoundTag) nbt);
            stacks.set(cnt.getAndIncrement(), itemStack);
        });
    }

    public static void deserializeIngredients(@Nonnull CompoundTag compoundNBT, @Nonnull List<Ingredient> ingredients) {
        assert compoundNBT.contains("Items", 9/*Constants.NBT.TAG_LIST*/);
        ListTag listNBT = compoundNBT.getList("Items", 10/*Constants.NBT.TAG_COMPOUND*/);

        ingredients.clear();

        listNBT.forEach(nbt -> {
            assert ((CompoundTag) nbt).contains("Items", 9/*Constants.NBT.TAG_LIST*/);
            ListTag itemsNBT = ((CompoundTag) nbt).getList("Items", 10/*Constants.NBT.TAG_COMPOUND*/);
            ArrayList<ItemStack> itemStacks = new ArrayList<>();

            itemsNBT.forEach(itemNbt -> {
                ItemStack itemStack = ItemStack.of((CompoundTag) itemNbt);
                itemStacks.add(itemStack);
            });

            ingredients.add(Ingredient.of(itemStacks.toArray(new ItemStack[0])));
        });
    }

    public static void insertItems(@Nonnull List<ItemStack> input, List<ItemStack> output, int startIndex, int endIndex) {
        assert output.size() > startIndex && output.size() >= endIndex && startIndex < endIndex;

        for (ItemStack itemStack : input) {
            if (itemStack.isEmpty()) {
                return;
            }

            int index = getFirstFreeOrValid(itemStack, output, startIndex, endIndex);

            if (index < 0) {
                return;
            }

            int items = itemStack.getCount();

            if (!output.get(index).isEmpty()) {
                while (items > 0) {
                    ItemStack item = output.get(index);

                    int amount = item.getMaxStackSize() - item.getCount();

                    if (amount < items) {
                        item.grow(amount);
                        items -= amount;
                        index = getFirstFreeOrValid(itemStack, output, index, endIndex);
                    } else {
                        item.grow(items);
                        items = 0;
                    }
                }
            } else {
                output.set(index, itemStack.copy());
            }
        }
    }

    public static void renderItem(ItemStack itemStackIn, ItemDisplayContext transformTypeIn, int combinedLightIn, int combinedOverlayIn, PoseStack matrixStackIn, MultiBufferSource bufferIn, float alpha) {
        renderItem(itemStackIn, transformTypeIn, matrixStackIn, bufferIn, combinedLightIn, combinedOverlayIn, alpha);
    }

    private static void renderItem(ItemStack itemStackIn, ItemDisplayContext transformTypeIn, PoseStack matrixStackIn, MultiBufferSource bufferIn, int combinedLightIn, int combinedOverlayIn, float alpha) {
        if (!itemStackIn.isEmpty()) {
            BakedModel ibakedmodel = getItemModelWithOverrides(itemStackIn);
            renderItem(itemStackIn, transformTypeIn, matrixStackIn, bufferIn, combinedLightIn, combinedOverlayIn, ibakedmodel, alpha);
        }
    }

    private static BakedModel getItemModelWithOverrides(ItemStack stack) {
        Item item = stack.getItem();
        BakedModel ibakedmodel;
        if (item == Items.TRIDENT) {
            ibakedmodel = Minecraft.getInstance().getItemRenderer().getItemModelShaper().getModelManager().getModel(new ResourceLocation("minecraft:trident_in_hand#inventory"));
        } else {
            ibakedmodel = Minecraft.getInstance().getItemRenderer().getItemModelShaper().getItemModel(stack);
        }

        return getModelWithOverrides(ibakedmodel, stack);
    }

    private static BakedModel getModelWithOverrides(BakedModel model, ItemStack stack) {
        BakedModel ibakedmodel = model.getOverrides().resolve(model, stack, null, null,0);
        return ibakedmodel == null ? Minecraft.getInstance().getItemRenderer().getItemModelShaper().getModelManager().getMissingModel() : ibakedmodel;
    }

    private static void renderItem(ItemStack itemStackIn, ItemDisplayContext transformTypeIn, PoseStack matrixStackIn, MultiBufferSource bufferIn, int combinedLightIn, int combinedOverlayIn, BakedModel modelIn, float alpha) {
        if (!itemStackIn.isEmpty()) {
            matrixStackIn.pushPose();
            boolean flag = transformTypeIn == ItemDisplayContext.GUI;
            boolean flag1 = flag || transformTypeIn == ItemDisplayContext.GROUND || transformTypeIn == ItemDisplayContext.FIXED;
            if (itemStackIn.getItem() == Items.TRIDENT && flag1) {
                modelIn = Minecraft.getInstance().getItemRenderer().getItemModelShaper().getModelManager().getModel(new ResourceLocation("minecraft:trident#inventory"));
            }

            modelIn = ForgeHooksClient.handleCameraTransforms(matrixStackIn, modelIn, transformTypeIn, false);
            matrixStackIn.translate(-0.5D, -0.5D, -0.5D);
            if (modelIn.isCustomRenderer() || itemStackIn.getItem() == Items.TRIDENT && !flag1) {
//                itemStackIn.getItem().getItemStackTileEntityRenderer().renderByItem(itemStackIn, transformTypeIn, matrixStackIn, bufferIn, combinedLightIn, combinedOverlayIn);

            } else {
                RenderType rendertype = ItemBlockRenderTypes.getRenderType(itemStackIn, true);
                RenderType rendertype1;
                if (flag && Objects.equals(rendertype, Sheets.translucentCullBlockSheet())) {
                    rendertype1 = Sheets.translucentCullBlockSheet();
                } else {
                    rendertype1 = rendertype;
                }

                VertexConsumer ivertexbuilder = ItemRenderer.getFoilBuffer(bufferIn, rendertype1, true, itemStackIn.hasFoil());
                renderModel(modelIn, combinedLightIn, combinedOverlayIn, matrixStackIn, ivertexbuilder, alpha);
            }

            matrixStackIn.popPose();
        }
    }

    @SuppressWarnings("deprecation")
    private static void renderModel(BakedModel modelIn, int combinedLightIn, int combinedOverlayIn, PoseStack matrixStackIn, VertexConsumer bufferIn, float alpha) {
        RandomSource random = RandomSource.create();
        Direction[] var10 = Direction.values();

        for (Direction direction : var10) {
            random.setSeed(42L);
            renderQuads(matrixStackIn, bufferIn, modelIn.getQuads(null, direction, random), combinedLightIn, combinedOverlayIn, alpha);
        }

        random.setSeed(42L);
        renderQuads(matrixStackIn, bufferIn, modelIn.getQuads(null, null, random), combinedLightIn, combinedOverlayIn, alpha);
    }

    private static void renderQuads(PoseStack matrixStackIn, VertexConsumer bufferIn, List<BakedQuad> quadsIn, int combinedLightIn, int combinedOverlayIn, float alpha) {
        PoseStack.Pose matrixstack$entry = matrixStackIn.last();

        for (BakedQuad bakedquad : quadsIn) {
//            bufferIn.vertex()
            bufferIn.vertex(matrixstack$entry.pose(), 1, 1, 1);
        }
    }

    private static int getFirstFreeOrValid(ItemStack item, List<ItemStack> output, int startIndex, int endIndex) {
        for (int i = startIndex; i < endIndex; i++) {
            ItemStack itemStack = output.get(i);

            if (itemStack.is(item.getItem())) {
                if (itemStack.getCount() < itemStack.getMaxStackSize()) {
                    return i;
                }
            } else if (itemStack.isEmpty()) {
                return i;
            }
        }

        return -1;
    }
}
