package org.dreambot.articron.ui;

import org.dreambot.api.methods.MethodProvider;
import org.dreambot.articron.CronScript;
import org.dreambot.articron.loader.HImageLoader;
import org.dreambot.articron.profile.ProfileLoader;
import org.dreambot.articron.swing.HFrame;
import org.dreambot.articron.swing.HPanel;
import org.dreambot.articron.swing.child.HButton;
import org.dreambot.articron.swing.child.HComboBox;
import org.dreambot.articron.swing.child.HLabel;
import org.dreambot.articron.ui.bot.MainUI;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class ProfilePicker extends HFrame {
	
    ProfileLoader loader;

    public ProfilePicker(CronScript cs) {
        super("CronScript profiler", HImageLoader.loadImage("https://i.imgur.com/SGA9et4.png"));

        HLabel logo = new HLabel();
        loader = new ProfileLoader();
        logo.setIcon(HImageLoader.loadIconImage("https://i.imgur.com/IXscaCD.png"));
        HPanel mainPanel = new HPanel();
        HPanel buttonPanel = new HPanel();
        buttonPanel.setLayout(new BorderLayout());
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(10,0,0,0));
        mainPanel.setLayout(new BorderLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
        contentPane.add(mainPanel, BorderLayout.CENTER);
        HPanel southernPanel = new HPanel();
        southernPanel.setLayout(new BorderLayout());
        HLabel scriptname = new HLabel("ArtiMTA-pro by @Articron");
        scriptname.setBorder(BorderFactory.createEmptyBorder(10,0,10,0));
        ArrayList<String> profiles = new ArrayList<>();
        profiles.add("Create new...");
        Collections.addAll(profiles,loader.getProfileNames().toArray(new String[loader.getProfileNames().size()]));
        HComboBox<String> profileBox = new HComboBox<>(profiles.toArray(new String[profiles.size()]));
        scriptname.setFont(new Font("Arial",Font.PLAIN,10));
        scriptname.setHorizontalAlignment(JLabel.CENTER);
        southernPanel.add(scriptname, BorderLayout.NORTH);
        southernPanel.add(profileBox, BorderLayout.CENTER);
        southernPanel.add(buttonPanel, BorderLayout.SOUTH);
        HButton startButton = new HButton("Start", e -> {
            if (profileBox.getSelectedIndex() == 0) {
                MainUI ui = new MainUI(
                        cs.getManifest().name() + " V" + cs.getManifest().version(),
                        HImageLoader.loadImage("https://i.imgur.com/SGA9et4.png"),
                        cs.getContext());
                this.dispose();
            } else {
                String selectedProfile = profileBox.getItemAt(profileBox.getSelectedIndex());
                try {
                    loader.loadProfile(selectedProfile, cs.getContext());
                } catch (IOException e1) {
                    MethodProvider.log("Failed to load!");
                }
                this.dispose();
            }
        });

        buttonPanel.add(startButton, BorderLayout.CENTER);
        mainPanel.add(logo, BorderLayout.CENTER);
        mainPanel.add(southernPanel, BorderLayout.SOUTH);
        prepare(null);
    }
}
