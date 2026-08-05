package io.github.willowbranch.willowtweaks.feature;

import io.github.willowbranch.willowtweaks.config.FeatureToggle;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;


public class AutoFly
{
    private static boolean lastFallFlying = false;


    public static void tick()
    {
        Minecraft mc = Minecraft.getInstance();

        if (mc.player == null)
        {
            return;
        }


        if (!FeatureToggle.AUTO_FLY.getBooleanValue())
        {
            lastFallFlying = mc.player.isFallFlying();
            return;
        }


        LocalPlayer player = mc.player;

        boolean nowFlying = player.isFallFlying();


        if (nowFlying && !lastFallFlying)
        {
            tryStart(player);
        }


        lastFallFlying = nowFlying;
    }



    public static void tryStart(LocalPlayer player)
    {
        int slot = findBestRocketInHotbar(player);


        // 快捷栏已有火箭
        if (slot != -1)
        {
            player.getInventory().setSelectedSlot(slot);

            useRocket(player);

            return;
        }



        int rocketSlot = findBestRocket(player);


        if (rocketSlot == -1)
        {
            return;
        }



        int empty = findEmptyHotbar(player);


        // 有空快捷栏
        if (empty != -1)
        {
            swapInventoryToHotbar(
                    player,
                    rocketSlot,
                    empty
            );


            player.getInventory().setSelectedSlot(empty);

            useRocket(player);

            return;
        }



        // 没空位，交换当前手持槽
        int selected =
                player.getInventory().getSelectedSlot();


        swapInventoryToHotbar(
                player,
                rocketSlot,
                selected
        );


        useRocket(player);
    }




    private static void swapInventoryToHotbar(
            LocalPlayer player,
            int inventorySlot,
            int hotbarSlot
    )
    {
        Minecraft.getInstance()
                .gameMode
                .handleContainerInput(
                        player.inventoryMenu.containerId,
                        inventoryToContainerSlot(inventorySlot),
                        hotbarSlot,
                        net.minecraft.world.inventory.ContainerInput.SWAP,
                        player
                );
    }



    /**
     * Inventory index 转 Container slot
     */
    private static int inventoryToContainerSlot(int slot)
    {
        if (slot < 9)
        {
            return 36 + slot;
        }


        return slot;
    }




    private static void useRocket(LocalPlayer player)
    {
        Minecraft.getInstance()
                .gameMode
                .useItem(
                        player,
                        net.minecraft.world.InteractionHand.MAIN_HAND
                );
    }




    private static int findEmptyHotbar(LocalPlayer player)
    {
        for (int i = 0; i < 9; i++)
        {
            if (player.getInventory().getItem(i).isEmpty())
            {
                return i;
            }
        }

        return -1;
    }




    private static int findBestRocketInHotbar(LocalPlayer player)
    {
        int best = -1;
        int power = -1;


        for(int i = 0; i < 9; i++)
        {
            ItemStack stack =
                    player.getInventory().getItem(i);


            if(stack.is(Items.FIREWORK_ROCKET))
            {
                int p = getPower(stack);


                if(p > power)
                {
                    power = p;
                    best = i;
                }
            }
        }


        return best;
    }




    private static int findBestRocket(LocalPlayer player)
    {
        int bestSlot = -1;
        int bestPower = -1;


        for (int i = 0; i < player.getInventory().getContainerSize(); i++)
        {
            ItemStack stack =
                    player.getInventory().getItem(i);


            if (stack.is(Items.FIREWORK_ROCKET))
            {
                int power = getPower(stack);


                if (power > bestPower)
                {
                    bestPower = power;
                    bestSlot = i;
                }
            }
        }


        return bestSlot;
    }




    private static int getPower(ItemStack stack)
    {
        if (!stack.has(net.minecraft.core.component.DataComponents.FIREWORKS))
        {
            return 0;
        }


        var fireworks =
                stack.get(
                        net.minecraft.core.component.DataComponents.FIREWORKS
                );


        return fireworks.flightDuration();
    }
}