package net.elidhan.anim_guns.client;

import net.elidhan.anim_guns.AnimatedGuns;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.RenderPhase;
import net.minecraft.client.render.VertexFormat;
import net.minecraft.client.render.VertexFormats;
import net.minecraft.util.Identifier;

public class AttachmentRenderType extends RenderLayer
{
    public AttachmentRenderType(String name, VertexFormat vertexFormat, VertexFormat.DrawMode drawMode, int expectedBufferSize, boolean hasCrumbling, boolean translucent, Runnable startAction, Runnable endAction) {
        super(name, vertexFormat, drawMode, expectedBufferSize, hasCrumbling, translucent, startAction, endAction);
    }

    public static RenderLayer getAttachment(int attachmentType, int attachmentID)
    {
        switch (attachmentType)
        {
            case 1 ->
            {
                MultiPhaseParameters multiPhaseParameters = MultiPhaseParameters.builder().program(ENTITY_TRANSLUCENT_PROGRAM).texture(new RenderPhase.Texture(new Identifier(AnimatedGuns.MOD_ID, "textures/misc/attach_si_"+attachmentID+".png"), false, false)).transparency(TRANSLUCENT_TRANSPARENCY).cull(DISABLE_CULLING).lightmap(ENABLE_LIGHTMAP).overlay(ENABLE_OVERLAY_COLOR).build(false);
                return RenderLayer.of(AnimatedGuns.MOD_ID+":"+1+"_"+attachmentID, VertexFormats.POSITION_COLOR_TEXTURE_OVERLAY_LIGHT_NORMAL, VertexFormat.DrawMode.QUADS, 256, true, false, multiPhaseParameters);
            }
            case 2 ->
            {
                MultiPhaseParameters multiPhaseParameters = MultiPhaseParameters.builder().program(ENTITY_TRANSLUCENT_PROGRAM).texture(new RenderPhase.Texture(new Identifier(AnimatedGuns.MOD_ID, "textures/misc/attach_gr_"+attachmentID+".png"), false, false)).transparency(TRANSLUCENT_TRANSPARENCY).cull(DISABLE_CULLING).lightmap(ENABLE_LIGHTMAP).overlay(ENABLE_OVERLAY_COLOR).build(false);
                return RenderLayer.of(AnimatedGuns.MOD_ID+":"+2+"_"+attachmentID, VertexFormats.POSITION_COLOR_TEXTURE_OVERLAY_LIGHT_NORMAL, VertexFormat.DrawMode.QUADS, 256, true, false, multiPhaseParameters);
            }
            case 3->
            {
                MultiPhaseParameters multiPhaseParameters = MultiPhaseParameters.builder().program(ENTITY_TRANSLUCENT_PROGRAM).texture(new RenderPhase.Texture(new Identifier(AnimatedGuns.MOD_ID, "textures/misc/attach_mz_"+attachmentID+".png"), false, false)).transparency(TRANSLUCENT_TRANSPARENCY).cull(DISABLE_CULLING).lightmap(ENABLE_LIGHTMAP).overlay(ENABLE_OVERLAY_COLOR).build(false);
                return RenderLayer.of(AnimatedGuns.MOD_ID+":"+3+"_"+attachmentID, VertexFormats.POSITION_COLOR_TEXTURE_OVERLAY_LIGHT_NORMAL, VertexFormat.DrawMode.QUADS, 256, true, false, multiPhaseParameters);
            }
        }
        return RenderLayer.getEntityTranslucent(new Identifier(AnimatedGuns.MOD_ID + "misc/attach_si_"+attachmentID));
    }
}
