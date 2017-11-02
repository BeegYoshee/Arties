package org.dreambot.articron.ui;

import org.dreambot.articron.CronScript;
import org.dreambot.articron.data.MTARune;
import org.dreambot.articron.data.MuleLocation;
import org.dreambot.articron.data.ScriptMode;
import org.dreambot.articron.loader.HImageLoader;
import org.dreambot.articron.net.protocol.PacketType;
import org.dreambot.articron.swing.HFrame;
import org.dreambot.articron.swing.HPanel;
import org.dreambot.articron.swing.child.HButton;
import org.dreambot.articron.swing.child.HLabel;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class ModePicker extends HFrame {

    HPanel content;
    HPanel southernPanel;
    HButton muleButton;
    HButton mtaButton;

    public ModePicker(CronScript script) {
        super("ArtiMTA PRO", HImageLoader.loadImage("https://i.imgur.com/SGA9et4.png"));

        mtaButton = new HButton("Bot", (e) -> {
            script.getContext().shouldMule(true);
            script.getContext().setMuleLocation(MuleLocation.AL_KHARID_CHEST);

            script.getContext().setMuleClient(43594);

            try {
                if (script.getContext().getMuleClient().connect()) {
                    script.getContext().getMuleClient().getConnection().getStream().sendUTF(
                            script.getContext().getDB().getLocalPlayer().getName(),
                            PacketType.HANDSHAKE);
                }
            } catch (IOException io) {
                    System.out.println("Failed sending UTF to server");
            }

            new ProfilePicker(script);
            this.dispose();
        });

        muleButton = new HButton("Mule",(e) -> {

            script.getContext().setMuleLocation(MuleLocation.AL_KHARID_CHEST);
            script.getContext().getMuleHandler().getTrading().addRunesToTrade(MTARune.LAW_RUNE,500);
            script.getContext().getMuleHandler().getTrading().addRunesToTrade(MTARune.COSMIC_RUNE, 300);
            script.getContext().getMuleHandler().getTrading().addRunesToTrade(MTARune.NATURE_RUNE,1000);

            script.getContext().setMuleServer(43594,"SecretKey");
            script.getContext().getMuleServer().start();

            script.getContext().loadMode(ScriptMode.MULE);
            script.getContext().setShouldPaint(true);
            this.dispose();
        });

        contentPane.add(content = new HPanel(),BorderLayout.CENTER);
        content.setLayout(new BorderLayout());
        content.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        HLabel logo = new HLabel();
        HLabel text = new HLabel("Pick your mode of choice");
        text.setFont(new Font("Arial", Font.ITALIC, 12));
        text.setHorizontalAlignment(JLabel.CENTER);
        text.setBorder(BorderFactory.createEmptyBorder(10,0,10,10));
        logo.setIcon(HImageLoader.loadIconImage("https://i.imgur.com/IXscaCD.png"));
        content.add(logo, BorderLayout.NORTH);
        content.add(text, BorderLayout.CENTER);
        content.add(southernPanel = new HPanel(), BorderLayout.SOUTH);
        southernPanel.setLayout(new BorderLayout());
        southernPanel.add(muleButton, BorderLayout.NORTH);
        southernPanel.add(mtaButton, BorderLayout.SOUTH);
        prepare(null);
    }

    public static void main(String... args) {
        new ModePicker(null);
    }

}
