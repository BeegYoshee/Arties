package org.dreambot.articron.ui.bot.panels.room;

import org.dreambot.articron.data.MTARoom;
import org.dreambot.articron.data.MTASpell;
import org.dreambot.articron.data.MTAStave;
import org.dreambot.articron.loader.HImageLoader;
import org.dreambot.articron.swing.HPanel;
import org.dreambot.articron.swing.child.HImageComboBox;
import org.dreambot.articron.ui.bot.panels.reward.RewardIcon;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.image.BufferedImage;

public class MTARoomPanel extends HPanel {

	private HImageComboBox<DisplayObject> spellBox;
	private HImageComboBox<DisplayObject> staffBox;
	
	public MTARoomPanel(Border border, MTARoom room) {
		super(new BorderLayout(), border);
		RuneDisplay display = new RuneDisplay();
		MTASpell[] spells;
		DisplayObject[] objects = new DisplayObject[(spells = getAvailable(room)).length];
		for (int i = 0; i < objects.length; i++) {
			objects[i]=new DisplayObject(spells[i].getSpellName(), HImageLoader.loadImage(spells[i].getLink()));
		} 
		add(spellBox = new HImageComboBox<>(objects), BorderLayout.NORTH);
		spellBox.getEditor().setSpellIcon(new ImageIcon(new RewardIcon(objects[0].getImage())));
		display.update(spells[0]);
		add(staffBox = new HImageComboBox<>(new DisplayObject("None", null)));
		for (MTAStave staff : spells[0].getStaves()) {
			BufferedImage image = new RewardIcon(HImageLoader.loadImage(staff.getLink()));
			staffBox.addItem(new DisplayObject(staff.getName(), image));
		}
		spellBox.addActionListener(listener -> {
			String spell = spellBox.getSelectedItem().toString();
			MTASpell mtaspell = MTASpell.reverseSearch(spell);
			spellBox.getEditor()
					.setSpellIcon(new ImageIcon(new RewardIcon(HImageLoader.loadImage(mtaspell.getLink()))));
			staffBox.clear();
			display.update(mtaspell);
			if (mtaspell.getStaves() == null) {
				return;
			}
			for (MTAStave staff : mtaspell.getStaves()) {
				BufferedImage image = new RewardIcon(HImageLoader.loadImage(staff.getLink()));
				staffBox.addItem(new DisplayObject(staff.getName(), image));
			}
		});
		add(staffBox, BorderLayout.SOUTH);
		staffBox.addActionListener(listener -> {
			DisplayObject object = (DisplayObject) staffBox.getSelectedItem();
			staffBox.getEditor().setSpellIcon(new ImageIcon(new RewardIcon(object.getImage())));
		});
		add(display, BorderLayout.CENTER);
	}

	private MTASpell[] getAvailable(MTARoom room) {
		switch (room) {
		case ALCHEMY:
			return new MTASpell[] { MTASpell.LOW_ALCHEMY, MTASpell.HIGH_ALCHEMY };
		case ENCHANTING:
			return new MTASpell[] { MTASpell.LEVEL_1_ENCHANT, MTASpell.LEVEL_2_ENCHANT, MTASpell.LEVEL_3_ENCHANT,
					MTASpell.LEVEL_4_ENCHANT, MTASpell.LEVEL_5_ENCHANT, MTASpell.LEVEL_6_ENCHANT,
					MTASpell.LEVEL_7_ENCHANT };
		case GRAVEYARD:
			return new MTASpell[] { MTASpell.BONES_TO_BANANAS, MTASpell.BONES_TO_PEACHES };
		case TELEKINETIC:
			return new MTASpell[] { MTASpell.TELEKINETIC_GRAB };
		default:
			return new MTASpell[] { null };
		}
	}

	public MTASpell getSpell() {
		return MTASpell.reverseSearch(spellBox.getSelectedItem().toString());
	}
	
	public MTAStave getStaff() {
		return MTAStave.reverseSearch(staffBox.getSelectedItem().toString());
	}
}
