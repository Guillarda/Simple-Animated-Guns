package net.elidhan.anim_guns.mixin.client;

import net.elidhan.anim_guns.AnimatedGunsClient;
import net.minecraft.client.model.ModelPart;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.math.MathHelper;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(BipedEntityModel.class)
public class BipedEntityModelMixin<T extends LivingEntity>
{
    @Final
    @Shadow
    public ModelPart rightArm;

    @Final
    @Shadow
    public ModelPart leftArm;

    @Final
    @Shadow
    public ModelPart head;

    @Shadow
    public boolean sneaking;

    @Shadow
    public BipedEntityModel.ArmPose rightArmPose;

    @Shadow
    @Final
    public ModelPart body;

    @Shadow @Final public ModelPart rightLeg;

    @Shadow @Final public ModelPart leftLeg;

    @Inject(method = "setAngles(Lnet/minecraft/entity/LivingEntity;FFFFF)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/util/math/MathHelper;cos(F)F", ordinal = 0))
    public void rotateBody(T livingEntity, float f, float g, float h, float i, float j, CallbackInfo ci)
    {
        if(this.rightArmPose == AnimatedGunsClient.HANDGUN_ONEHAND
                || this.rightArmPose == AnimatedGunsClient.HANDGUN_TWOHAND
                || this.rightArmPose == AnimatedGunsClient.LONG_GUNS
                || this.rightArmPose == AnimatedGunsClient.MINIGUN
                || this.rightArmPose == AnimatedGunsClient.REVOLVER_FANNING)
        {
            this.body.yaw = this.head.yaw;
            this.rightArm.pivotZ = MathHelper.sin(this.body.yaw) * 5.0f;
            this.rightArm.pivotX = -MathHelper.cos(this.body.yaw) * 5.0f;
            this.leftArm.pivotZ = -MathHelper.sin(this.body.yaw) * 5.0f;
            this.leftArm.pivotX = MathHelper.cos(this.body.yaw) * 5.0f;
        }
    }
    @Inject(method = "setAngles(Lnet/minecraft/entity/LivingEntity;FFFFF)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/model/ModelPart;copyTransform(Lnet/minecraft/client/model/ModelPart;)V"))
    public void rotateLegsWithBody(T livingEntity, float f, float g, float h, float i, float j, CallbackInfo ci)
    {
        if(this.rightArmPose == AnimatedGunsClient.HANDGUN_ONEHAND
                || this.rightArmPose == AnimatedGunsClient.HANDGUN_TWOHAND
                || this.rightArmPose == AnimatedGunsClient.LONG_GUNS
                || this.rightArmPose == AnimatedGunsClient.MINIGUN
                || this.rightArmPose == AnimatedGunsClient.REVOLVER_FANNING)
        {
            this.rightLeg.pivotX = -MathHelper.cos(this.body.yaw) * 2.0f + (this.sneaking ? MathHelper.sin(this.body.yaw) * 4 : 0);
            this.rightLeg.pivotZ = MathHelper.sin(this.body.yaw) * 2.0f + (this.sneaking ? MathHelper.cos(this.body.yaw) * 4 : 0);

            this.leftLeg.pivotX = MathHelper.cos(this.body.yaw) * 2.0f + (this.sneaking ? MathHelper.sin(this.body.yaw) * 4 : 0);
            this.leftLeg.pivotZ = -MathHelper.sin(this.body.yaw) * 2.0f + (this.sneaking ? MathHelper.cos(this.body.yaw) * 4 : 0);

            this.rightLeg.yaw = this.body.yaw;
            this.leftLeg.yaw = this.body.yaw;
        }
    }

    @Inject(method = "positionRightArm", at = @At("HEAD"), cancellable = true)
    public void gunRightArm(T entity, CallbackInfo ci)
    {
        if(this.rightArmPose == AnimatedGunsClient.HANDGUN_ONEHAND)
        {
            this.rightArm.pitch = this.head.pitch - 1.5707964f;
            this.rightArm.yaw = this.head.yaw;

            this.leftArm.yaw = this.head.yaw;
            ci.cancel();
        }
        if(this.rightArmPose == AnimatedGunsClient.HANDGUN_TWOHAND || this.rightArmPose == AnimatedGunsClient.LONG_GUNS)
        {
            this.rightArm.pitch = this.head.pitch - 1.5707964f;
            this.leftArm.pitch = this.head.pitch - 1.5707964f;
            this.rightArm.yaw = this.head.yaw;
            this.leftArm.yaw = this.head.yaw + 0.4f;
            ci.cancel();
        }
        if(this.rightArmPose == AnimatedGunsClient.REVOLVER_FANNING)
        {
            this.rightArm.pitch = this.head.pitch - 1.0f;
            this.rightArm.yaw = this.head.yaw;

            this.leftArm.pitch = this.head.pitch - 1.0f;
            this.leftArm.yaw = this.head.yaw + 0.75f;
            ci.cancel();
        }
        if(this.rightArmPose == AnimatedGunsClient.MINIGUN)
        {
            this.rightArm.pitch = this.head.pitch - 0.5707964f;
            this.rightArm.yaw = this.head.yaw;

            this.leftArm.pitch = this.head.pitch - 1.0f;
            this.leftArm.yaw = this.head.yaw + 0.75f;
            ci.cancel();
        }
    }

    /*
    @Inject(method = "positionLeftArm", at = @At("HEAD"), cancellable = true)
    public void gunLeftArm(T entity, CallbackInfo ci)
    {
        if(this.leftArmPose == AnimatedGunsClient.HANDGUN_ONEHAND)
        {
            this.leftArm.pitch = this.head.pitch - 1.5707964f;
            this.leftArm.yaw = this.head.yaw;

            ci.cancel();
        }
        if(this.leftArmPose == AnimatedGunsClient.HANDGUN_TWOHAND || this.rightArmPose == AnimatedGunsClient.LONG_GUNS)
        {
            this.leftArm.pitch = this.head.pitch - 1.5707964f;
            this.rightArm.pitch = this.head.pitch - 1.5707964f;
            this.leftArm.yaw = this.head.yaw;
            this.rightArm.yaw = this.head.yaw - 0.4f;
            ci.cancel();
        }
        if(this.leftArmPose == AnimatedGunsClient.REVOLVER_FANNING)
        {
            this.leftArm.pitch = this.head.pitch - 10.5707964f;
            this.leftArm.yaw = this.head.yaw;
            this.rightArm.pitch = this.head.pitch - 2.5707964f;
            this.rightArm.yaw = this.head.yaw;
            ci.cancel();
        }
    }
    */
}
