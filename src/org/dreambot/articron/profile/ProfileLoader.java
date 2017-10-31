package org.dreambot.articron.profile;

import org.dreambot.articron.data.MTARoom;
import org.dreambot.articron.data.MTASpell;
import org.dreambot.articron.data.MTAStave;
import org.dreambot.articron.data.Reward;
import org.dreambot.articron.fw.ScriptContext;
import org.dreambot.articron.ui.MainUI;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ProfileLoader {

    private final String DIRECTORY_PATH = System.getProperty("user.home") + "\\" + "ArtiMTA-pro";

    private File directory;

    public ProfileLoader() {
        this.directory = new File(DIRECTORY_PATH);
        init();
    }

    public void init() {
        System.out.println("path = " + DIRECTORY_PATH);
        if (!directory.exists()) {
            if (directory.mkdirs()) {
                System.out.println("Profile directory created");
            }
        }
    }

    private Profile readProfile(String fileName) throws IOException {
        Profile profile = new Profile();
        File f = new File(DIRECTORY_PATH + "\\" + fileName + ".ser");
        if (f.exists()) {
            BufferedReader reader = new BufferedReader(new FileReader(f));
            String config;
            while (((config = reader.readLine()) != null)) {
                String[] subs = config.split("\\|");
                String[] configs = new String[subs.length - 1];
                String token = subs[0];
                for (int i = 1; i < subs.length; i++) {
                    configs[i - 1] = subs[i];
                }
                profile.putSetting(token,configs);
            }
            reader.close();
        }
        return profile;
    }

    public void loadProfile(String fileName, ScriptContext context) throws IOException {
        Profile profile = readProfile(fileName);
        String[] roomorder = profile.getConfigs("ORDER");
        for (String roomName : roomorder) {
            MTARoom room = MTARoom.reverseSearch(roomName);
            if (room != null) {
                context.getMTA().getRoomOrder().add(room);
            }
        }
        String[] teleConfig = profile.getConfigs("TELEKINETIC");
        String[] enchantConfig = profile.getConfigs("ENCHANTING");
        String[] alchConfig = profile.getConfigs("ALCHEMY");
        String[] graveyardConfig = profile.getConfigs("GRAVEYARD");
        String[] rewards = profile.getConfigs("REWARDS");
        context.getMTA().getTelekineticHandler().setStave(MTAStave.reverseSearch(teleConfig[0]));
        context.getMTA().getTelekineticHandler().setSpell(MTASpell.reverseSearch(teleConfig[1]));
        context.getMTA().getEnchantingHandler().setStave(MTAStave.reverseSearch(enchantConfig[0]));
        context.getMTA().getEnchantingHandler().setSpell(MTASpell.reverseSearch(enchantConfig[1]));
        context.getMTA().getAlchemyHandler().setStave(MTAStave.reverseSearch(alchConfig[0]));
        context.getMTA().getAlchemyHandler().setSpell(MTASpell.reverseSearch(alchConfig[1]));
        context.getMTA().getGraveyardHandler().setStave(MTAStave.reverseSearch(graveyardConfig[0]));
        context.getMTA().getGraveyardHandler().setSpell(MTASpell.reverseSearch(graveyardConfig[1]));
        Arrays.asList(rewards).forEach(
                reward -> context.getMTA().getRewardQueue().add(Reward.reverseSearch(reward))
        );
        context.loadScriptNodes();
    }

    private File createProfileFile(String name) {
        File file = new File(DIRECTORY_PATH + "\\" + name + ".ser");
        return file.exists() ? null : file;
    }

    public void saveProfile(String profileName, MainUI ui) {
        if (getProfileNames().contains(profileName)) {
            return;
        }
        File profile = createProfileFile(profileName);
        if (profile == null) {
            return;
        }

        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(profile));
            String enchantStave = ui.getRoomPanel().getEnchanting().getStaff().getName();
            String enchantSpell = ui.getRoomPanel().getEnchanting().getSpell().getSpellName();
            String telekineticStave = ui.getRoomPanel().getTelekinetic().getStaff().getName();
            String telekineticSpell = ui.getRoomPanel().getTelekinetic().getSpell().getSpellName();
            String alchemyStave = ui.getRoomPanel().getAlchemy().getStaff().getName();
            String alchemySpell = ui.getRoomPanel().getAlchemy().getSpell().getSpellName();
            String graveyardStave = ui.getRoomPanel().getGraveyard().getStaff().getName();
            String graveyardSpell = ui.getRoomPanel().getGraveyard().getSpell().getSpellName();
            Reward[] rewards = ui.getRewardPanel().getQueuedRewards();
            String[] rewardNames = new String[rewards.length];
            for (int i = 0; i < rewards.length; i++) {
                rewardNames[i] = rewards[i].name();
            }
            MTARoom[] roomorder = ui.getMiscellaneousPanel().getRoomPanel().getRoomOrder();
            String rooms[] = new String[roomorder.length];
            for (int i = 0; i < roomorder.length; i++) {
                rooms[i] = roomorder[i].name();
            }
            writer.write(getString(MTARoom.TELEKINETIC.name(), telekineticStave, telekineticSpell));
            writer.write(getString(MTARoom.ENCHANTING.name(), enchantStave, enchantSpell));
            writer.write(getString(MTARoom.ALCHEMY.name(), alchemyStave, alchemySpell));
            writer.write(getString(MTARoom.GRAVEYARD.name(), graveyardStave, graveyardSpell));
            writer.write(getString("REWARDS", rewardNames));
            writer.write(getString("ORDER", rooms));
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    private String getString(String token, String... values) {
        StringBuilder builder = new StringBuilder();
        builder.append(token).append("|");
        for (String value : values) {
            builder.append(value).append("|");
        }
        builder.append("\n");
        return builder.toString();
    }

    public List<String> getProfileNames() {
        List<String> profileList = new ArrayList<>();
        for (File f : directory.listFiles((dir, name) -> name.endsWith(".ser"))) {
            if (f != null) {
                if (f.isFile()) {
                        profileList.add(f.getName().replace(".ser", ""));
                }
            }
        }
        return profileList;
    }


    public static void main(String... args) {
        ProfileLoader loader = new ProfileLoader();
        for (String profile : loader.getProfileNames()) {
            System.out.println(profile);
        }
        MainUI ui = new MainUI("test",null,null);
        loader.saveProfile("kek",ui);
    }
}
