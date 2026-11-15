package cn.foggyhillside.ends_delight.client.render;

import cn.foggyhillside.ends_delight.block.EndStoveBlock;
import cn.foggyhillside.ends_delight.block.entity.EndStoveBlockEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.blockentity.state.BlockEntityRenderState;
import net.minecraft.client.renderer.item.ItemModelResolver;
import net.minecraft.client.renderer.item.ItemStackRenderState;
import net.minecraft.core.Direction;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.phys.Vec2;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;
import vectorwing.farmersdelight.refabricated.inventory.ItemStackHandler;

public class EndStoveRenderer implements BlockEntityRenderer<EndStoveBlockEntity, EndStoveRenderer.State> {

    public static class State extends BlockEntityRenderState {
        public ItemStackRenderState[] stoveStacks = new ItemStackRenderState[0];
        public Vec2[] stoveOffsets = new Vec2[0];
        public Direction facing = Direction.NORTH;
    }

    private final ItemModelResolver itemModelResolver;

    public EndStoveRenderer(BlockEntityRendererProvider.Context context) {
        this.itemModelResolver = context.itemModelResolver();
    }

    @Override
    public State createRenderState() {
        return new State();
    }

    @Override
    public void extractRenderState(EndStoveBlockEntity stoveEntity, State state, float partialTicks, Vec3 cameraPosition,
                                   net.minecraft.client.renderer.feature.ModelFeatureRenderer.CrumblingOverlay breakProgress) {
        net.minecraft.client.renderer.blockentity.state.BlockEntityRenderState.extractBase(stoveEntity, state, breakProgress);
        ItemStackHandler inventory = stoveEntity.getItems();
        int slots = inventory.getSlotCount();
        ItemStackRenderState[] stacks = new ItemStackRenderState[slots];
        Vec2[] offsets = new Vec2[slots];
        for (int i = 0; i < slots; ++i) {
            ItemStackRenderState stackRenderState = new ItemStackRenderState();
            this.itemModelResolver.updateForTopItem(stackRenderState, inventory.getStackInSlot(i),
                    ItemDisplayContext.FIXED, stoveEntity.getLevel(), null, (int) stoveEntity.getBlockPos().asLong() + i);
            stacks[i] = stackRenderState;
            offsets[i] = stoveEntity.getStoveItemOffset(i);
        }
        state.stoveStacks = stacks;
        state.stoveOffsets = offsets;
        state.facing = stoveEntity.getBlockState().getValue(EndStoveBlock.FACING).getOpposite();
    }

    @Override
    public void submit(State state, PoseStack poseStack, net.minecraft.client.renderer.SubmitNodeCollector submitNodeCollector,
                       net.minecraft.client.renderer.state.level.CameraRenderState camera) {
        Direction direction = state.facing;
        ItemStackRenderState[] items = state.stoveStacks;
        for (int i = 0; i < items.length; ++i) {
            ItemStackRenderState stoveStack = items[i];
            if (stoveStack.isEmpty()) {
                continue;
            }
            poseStack.pushPose();

            // Center item above the stove
            poseStack.translate(0.5D, 1.02D, 0.5D);

            // Rotate item to face the stove's front side
            float f = -direction.toYRot();
            poseStack.mulPose(Axis.YP.rotationDegrees(f));

            // Rotate item flat on the stove. Use X and Y from now on
            poseStack.mulPose(Axis.XP.rotationDegrees(90.0F));

            // Neatly align items according to their index
            Vec2 itemOffset = state.stoveOffsets[i];
            poseStack.translate(itemOffset.x, itemOffset.y, 0.0D);

            // Resize the items
            poseStack.scale(0.375F, 0.375F, 0.375F);

            stoveStack.submit(poseStack, submitNodeCollector, state.lightCoords, net.minecraft.client.renderer.texture.OverlayTexture.NO_OVERLAY, 0);
            poseStack.popPose();
        }
    }
}
