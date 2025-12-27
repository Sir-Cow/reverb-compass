package sircow.reverbcompass.event;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.ClickEvent;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.HoverEvent;
import net.minecraft.network.chat.Style;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.ClientPlayerNetworkEvent;
import sircow.reverbcompass.Constants;
import sircow.reverbcompass.update.UpdateChecker;

import java.net.URI;
import java.util.Objects;

public class NeoforgeModEvents {
    private static boolean updateCheckScheduled = false;

    @EventBusSubscriber(modid = "reverbcompass")
    public static class EventHandler {
        @SubscribeEvent
        public static void updateCheck(ClientPlayerNetworkEvent.LoggingIn event) {
            if (!updateCheckScheduled) {
                updateCheckScheduled = true;

                UpdateChecker.checkAsync(() -> Objects.requireNonNull(event.getPlayer().level().getServer()).execute(() -> {
                    var player = event.getPlayer();

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

                        player.displayClientMessage(message, false);
                    }
                }));
            }
        }
    }
}
