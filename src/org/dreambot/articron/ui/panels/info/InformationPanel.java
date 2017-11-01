package org.dreambot.articron.ui.panels.info;

import org.dreambot.articron.data.MTARoom;
import org.dreambot.articron.data.Reward;
import org.dreambot.articron.profile.ProfileLoader;
import org.dreambot.articron.swing.HFrame;
import org.dreambot.articron.swing.HPanel;
import org.dreambot.articron.swing.child.*;
import org.dreambot.articron.ui.MainUI;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class InformationPanel extends HPanel {

	private HTextArea log;
	private MainUI main;

	public InformationPanel(Border border, MainUI main) {
		super(new BorderLayout(), border);
		this.main = main;
		HPanel information = new HPanel(new GridLayout(0, 1));
		information.add(new HLabel("Script revision: 0.1"));
		information.add(new HLabel("Script author: Articron"));
		information.add(new HLabel("Script UI author: Dinh"));
		HPanel top = new HPanel(new BorderLayout());
		HPanel buttons = new HPanel(new GridLayout(0, 2, 5, 0));
		HButton button = new HButton("Script Thread", listener -> {
			try {
				Desktop.getDesktop().browse(new URI("https://dreambot.org/forums/index.php/user/60711-dinh/"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (URISyntaxException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
		buttons.add(button);
		buttons.add(new HButton("Start", listener -> {
			String profileName = JOptionPane.showInputDialog(this,"Enter a name for this profile:",
                    "Profile saving",JOptionPane.PLAIN_MESSAGE);
			if (profileName != null && !profileName.equals("")) {
                ProfileLoader loader = new ProfileLoader();
                loader.saveProfile(profileName,main);
            }
            startScript();
		}));
		top.add(information, BorderLayout.WEST);
		top.add(buttons, BorderLayout.EAST);
		top.setBorder(BorderFactory.createEmptyBorder(3, 3, 3, 3));
		add(top, BorderLayout.NORTH);

		HScrollPane change = new HScrollPane(log = new HTextArea(clog));
		add(change, BorderLayout.CENTER);
		change.setBorder(new EmptyBorder(0, 3, 3, 3));
		change.setBackground(HFrame.BACKGROUND);

		HScrollPane scroll = new HScrollPane(new HTextArea(welcome));
		add(scroll, BorderLayout.SOUTH);
		scroll.setBorder(new EmptyBorder(0, 3, 3, 3));
		scroll.setBackground(HFrame.BACKGROUND);
	}


	private void startScript() {
        main.getContext().getMTA().getTelekineticHandler().setStave(main.getRoomPanel().getTelekinetic().getStaff());
        main.getContext().getMTA().getTelekineticHandler().setSpell(main.getRoomPanel().getTelekinetic().getSpell());
        main.getContext().getMTA().getEnchantingHandler().setSpell(main.getRoomPanel().getEnchanting().getSpell());
        main.getContext().getMTA().getEnchantingHandler().setStave(main.getRoomPanel().getEnchanting().getStaff());
        main.getContext().getMTA().getAlchemyHandler().setSpell(main.getRoomPanel().getAlchemy().getSpell());
        main.getContext().getMTA().getAlchemyHandler().setStave(main.getRoomPanel().getAlchemy().getStaff());
        main.getContext().getMTA().getGraveyardHandler().setSpell(main.getRoomPanel().getGraveyard().getSpell());
        main.getContext().getMTA().getGraveyardHandler().setStave(main.getRoomPanel().getGraveyard().getStaff());
        for(Reward r : main.getRewardPanel().getQueuedRewards()) {
            main.getContext().getMTA().getRewardQueue().add(r);
        }
        for(MTARoom r : main.getMiscellaneousPanel().getRoomPanel().getRoomOrder()) {
            main.getContext().getMTA().getRoomOrder().add(r);
        }
        /**
         System.out.println("E-MAIL: "+main.getMiscellaneousPanel().getNotificationPanel().getEMail());
         System.out.println("Alias: "+main.getMiscellaneousPanel().getNotificationPanel().getAlias());
         System.out.println("Levelup: "+main.getMiscellaneousPanel().getNotificationPanel().getLevel().getSlider().isSelected());
         System.out.println("Reward: "+main.getMiscellaneousPanel().getNotificationPanel().getReward().getSlider().isSelected());
         System.out.println("Muled: "+main.getMiscellaneousPanel().getNotificationPanel().getAction().getSlider().isSelected());
         System.out.println("End: "+main.getMiscellaneousPanel().getNotificationPanel().getEnd().getSlider().isSelected());
         System.out.println("Mule-name: "+main.getMiscellaneousPanel().getMulePanel().getMuleName());
         System.out.println("Mule-loc: "+main.getMiscellaneousPanel().getMulePanel().getMuleLocation());
         System.out.println("Mule-time: "+main.getMiscellaneousPanel().getMulePanel().getMuleTime());
         **/
        main.getContext().loadScriptNodes();
        main.dispose();
    }

	public HTextArea getLog() {
		return log;
	}

	private final String welcome = "Thank you for your purchase!\nPlease feel free to suggest any additional features in the official script thread.\nIf you find any bugs, please report them to me ASAP to have them finished in a timely fashion.";
	private final String clog = "Changelog\n\nV0.1:\n    * Initial release";

}
