package org.dreambot.articron.ui.panels.info;

import java.awt.BorderLayout;
import java.awt.Desktop;
import java.awt.GridLayout;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import javax.swing.BorderFactory;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

import org.dreambot.articron.data.MTARoom;
import org.dreambot.articron.data.Reward;
import org.dreambot.articron.swing.HFrame;
import org.dreambot.articron.swing.HPanel;
import org.dreambot.articron.swing.child.HButton;
import org.dreambot.articron.swing.child.HLabel;
import org.dreambot.articron.swing.child.HScrollPane;
import org.dreambot.articron.swing.child.HTextArea;
import org.dreambot.articron.ui.MainUI;

public class InformationPanel extends HPanel {

	private HTextArea log;

	public InformationPanel(Border border, MainUI main) {
		super(new BorderLayout(), border);
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
			System.out.println("Room-Panel");
			System.out.println("Telekinetic");
			System.out.println(main.getRoomPanel().getTelekinetic().getSpell().name());
			System.out.println(main.getRoomPanel().getTelekinetic().getStaff().name());
			System.out.println("Enchanters");
			System.out.println(main.getRoomPanel().getEnchanting().getSpell().name());
			System.out.println(main.getRoomPanel().getEnchanting().getStaff().name());
			System.out.println("Alchemists");
			System.out.println(main.getRoomPanel().getAlchemy().getSpell().name());
			System.out.println(main.getRoomPanel().getAlchemy().getStaff().name());
			System.out.println("Graveyard");
			System.out.println(main.getRoomPanel().getGraveyard().getSpell().name());
			System.out.println(main.getRoomPanel().getGraveyard().getStaff().name());
			System.out.println("Reward-Panel");
			for(Reward r : main.getRewardPanel().getQueuedRewards()) {
				System.out.println(r.name());
			}
			System.out.println("Miscellaneous-Panel");
			for(MTARoom r : main.getMiscellaneousPanel().getRoomPanel().getRoomOrder()) {
				System.out.println(r.name());
			}
			System.out.println("E-MAIL: "+main.getMiscellaneousPanel().getNotificationPanel().getEMail());
			System.out.println("Alias: "+main.getMiscellaneousPanel().getNotificationPanel().getAlias());
			System.out.println("Levelup: "+main.getMiscellaneousPanel().getNotificationPanel().getLevel().getSlider().isSelected());
			System.out.println("Reward: "+main.getMiscellaneousPanel().getNotificationPanel().getReward().getSlider().isSelected());
			System.out.println("Muled: "+main.getMiscellaneousPanel().getNotificationPanel().getAction().getSlider().isSelected());
			System.out.println("End: "+main.getMiscellaneousPanel().getNotificationPanel().getEnd().getSlider().isSelected());
			System.out.println("Mule-name: "+main.getMiscellaneousPanel().getMulePanel().getMuleName());
			System.out.println("Mule-loc: "+main.getMiscellaneousPanel().getMulePanel().getMuleLocation());
			System.out.println("Mule-time: "+main.getMiscellaneousPanel().getMulePanel().getMuleTime());
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

	public HTextArea getLog() {
		return log;
	}

	private final String welcome = "Thank you for your purchase!\nPlease feel free to suggest any additional features in the official script thread.\nIf you find any bugs, please report them to me ASAP to have them finished in a timely fashion.";
	private final String clog = "Changelog\n\nV0.1:\n    * Initial release";

}
