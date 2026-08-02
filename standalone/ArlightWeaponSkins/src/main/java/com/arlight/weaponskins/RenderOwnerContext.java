package com.arlight.weaponskins;

import java.util.ArrayDeque;
import java.util.Deque;
import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.LivingEntity;

public final class RenderOwnerContext {
    private static final ThreadLocal<Deque<LivingEntity>> OWNERS =
            ThreadLocal.withInitial(ArrayDeque::new);

    private RenderOwnerContext() { }

    public static void push(LivingEntity entity) {
        if (entity != null) OWNERS.get().push(entity);
    }

    public static void pop() {
        Deque<LivingEntity> stack = OWNERS.get();
        if (!stack.isEmpty()) stack.pop();
        if (stack.isEmpty()) OWNERS.remove();
    }

    public static LivingEntity currentEntity() {
        Deque<LivingEntity> stack = OWNERS.get();
        if (!stack.isEmpty()) return stack.peek();
        Minecraft minecraft = Minecraft.getInstance();
        return minecraft == null ? null : minecraft.player;
    }
}
