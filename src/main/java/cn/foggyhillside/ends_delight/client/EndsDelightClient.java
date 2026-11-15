package cn.foggyhillside.ends_delight.client;

import cn.foggyhillside.ends_delight.client.render.EndStoveRenderer;
import cn.foggyhillside.ends_delight.registry.ModBlockEntityTypes;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.BlockEntityRendererRegistry;

public class EndsDelightClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		BlockEntityRendererRegistry.register(ModBlockEntityTypes.END_STOVE.get(), EndStoveRenderer::new);
	}
}