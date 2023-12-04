package nourl.mythicmetals.client.models;

import net.minecraft.client.model.*;

public class StarPlatCloakModel {
	public static final ModelPart CAPE_MODEL = createCape();

	private static ModelPart createCape() {
		ModelData data = new ModelData();
		var root = data.getRoot();
		
		root.addChild(
				"cape_bone",
				ModelPartBuilder.create()
						.uv(0, 0)
						.cuboid(-5.5F, 0.0F, -0.05F, 11.0F, 23.0F, 1.0F),
				ModelTransform.pivot(0.0F, 0.5F, 2.9F)
		);
		
		return data.getRoot().createPart(32, 32);
	}
	
}
