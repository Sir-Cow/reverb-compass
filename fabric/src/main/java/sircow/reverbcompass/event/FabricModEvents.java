package sircow.reverbcompass.event;

import net.fabricmc.api.EnvType;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayConnectionEvents;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.ClickEvent;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.HoverEvent;
import net.minecraft.network.chat.Style;
import sircow.reverbcompass.Constants;
import sircow.reverbcompass.update.UpdateChecker;

import java.net.URI;

public class FabricModEvents {
    private static boolean updateCheckScheduled = false;

    public static void updateCheck() {
        if (FabricLoader.getInstance().getEnvironmentType() == EnvType.CLIENT) {
            ClientPlayConnectionEvents.JOIN.register((handler, sender, client) -> {
                if (!updateCheckScheduled) {
                    updateCheckScheduled = true;

                    UpdateChecker.checkAsync(() -> {
                        client.execute(() -> {
                            if (client.player == null) return;

                            String current = Constants.INSTANCE.getVersion();
                            String latest = UpdateChecker.getLatest();

                            if (latest == null) return;

                            if (UpdateChecker.hasUpdate()) {
                                ClickEvent click = new ClickEvent.OpenUrl(URI.create("https://modrinth.com/mod/reverb-compass/version/" + latest));
                                HoverEvent hover = new HoverEvent.ShowText(Component.literal("Open version page"));
                                Style updateLink = Style.EMPTY
                                        .withClickEvent(click)
                                        .withHoverEvent(hover)
                                        .withUnderlined(true)
                                        .withColor(ChatFormatting.BLUE);

                                Component message = Component.literal("[Reverb Compass]").withStyle(ChatFormatting.RED)
                                        .append(Component.literal(" Update available: ").withStyle(ChatFormatting.WHITE))
                                        .append(Component.literal(latest).setStyle(updateLink))
                                        .append(Component.literal(" (current: " + current + ")").withStyle(ChatFormatting.WHITE));

                                client.player.displayClientMessage(message, false);
                            }
                        });
                    });
                }
            });
        }
    }
    public static void registerModEvents() {
        updateCheck();
    }
}
