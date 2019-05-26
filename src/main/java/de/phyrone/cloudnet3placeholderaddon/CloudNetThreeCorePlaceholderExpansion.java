package de.phyrone.cloudnet3placeholderaddon;

import de.dytanic.cloudnet.driver.service.ServiceInfoSnapshot;
import de.dytanic.cloudnet.driver.service.ServiceTask;
import de.dytanic.cloudnet.wrapper.Wrapper;
import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.OfflinePlayer;

public class CloudNetThreeCorePlaceholderExpansion extends PlaceholderExpansion {
    private Cloudnet3PlaceholderAddon placeholderAddon;
    private Wrapper cloudnet = Wrapper.getInstance();
    private ServiceTask task = cloudnet.getServiceTask(cloudnet.getCurrentServiceInfoSnapshot().getServiceId().getTaskName());

    CloudNetThreeCorePlaceholderExpansion(Cloudnet3PlaceholderAddon placeholderAddon) {
        this.placeholderAddon = placeholderAddon;
    }

    @Override
    public String getIdentifier() {
        return "cloudnet";
    }

    @Override
    public String getAuthor() {
        return "Phyrone";
    }

    @Override
    public String getVersion() {
        return placeholderAddon.getDescription().getVersion();
    }

    @Override
    public String onRequest(OfflinePlayer p, String rawParameters) {
        ServiceInfoSnapshot curredServiceSnapshot = cloudnet.getCurrentServiceInfoSnapshot();
        String parameters = rawParameters.toLowerCase();
        switch (parameters) {
            case "service_name":
                return curredServiceSnapshot.getServiceId().getName();
            case "service_static":
                return "" + curredServiceSnapshot.getConfiguration().isStaticService();
            case "service_autodelete":
                return "" + curredServiceSnapshot.getConfiguration().isAutoDeleteOnStop();
            case "service_port":
                return "" + curredServiceSnapshot.getAddress().getPort();
            case "service_host":
                return "" + curredServiceSnapshot.getAddress().getHost();
            case "service_address":
                return curredServiceSnapshot.getAddress().getHost() + ":" + curredServiceSnapshot.getAddress().getPort();
            case "task_name":
                return curredServiceSnapshot.getServiceId().getTaskName();
            case "task_port":
                return "" + task.getStartPort();
        }
        return null;
    }
}
