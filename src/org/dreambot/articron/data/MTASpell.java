package org.dreambot.articron.data;

import org.dreambot.api.methods.magic.Normal;
import org.dreambot.api.methods.magic.Spell;

/**
 * Author: Articron Date: 20/10/2017.
 */
public enum MTASpell {

	NONE("None", null, null, null, null, null), TELEKINETIC_GRAB("Telekinetic grab", MTARoom.TELEKINETIC,
			Normal.TELEKINETIC_GRAB,
			"https://vignette.wikia.nocookie.net/2007scape/images/3/33/Telekinetic_Grab_icon.png/revision/latest?cb=20130410040457",
			new RuneRequirement[] { new RuneRequirement(MTARune.AIR_RUNE, 1),
					new RuneRequirement(MTARune.LAW_RUNE, 1) },
			MTAStave.AIR_STAFF), LEVEL_1_ENCHANT("Level 1 enchant", MTARoom.ENCHANTING, Normal.LEVEL_1_ENCHANT,
					"https://vignette.wikia.nocookie.net/2007scape/images/5/55/Lvl-1_Enchant_icon.png/revision/latest?cb=20140107203320",
					new RuneRequirement[] { new RuneRequirement(MTARune.WATER_RUNE, 1),
							new RuneRequirement(MTARune.COSMIC_RUNE, 1) },
					MTAStave.WATER_STAFF), LEVEL_2_ENCHANT("Level 2 enchant", MTARoom.ENCHANTING,
							Normal.LEVEL_2_ENCHANT,
							"https://vignette.wikia.nocookie.net/2007scape/images/f/f6/Lvl-2_Enchant_icon.png/revision/latest?cb=20140107203302",
							new RuneRequirement[] { new RuneRequirement(MTARune.AIR_RUNE, 3),
									new RuneRequirement(MTARune.COSMIC_RUNE, 1) },
							MTAStave.AIR_STAFF), LEVEL_3_ENCHANT("Level 3 enchant", MTARoom.ENCHANTING,
									Normal.LEVEL_3_ENCHANT,
									"https://vignette.wikia.nocookie.net/2007scape/images/9/99/Lvl-3_Enchant_icon.png/revision/latest?cb=20130821145711",
									new RuneRequirement[] { new RuneRequirement(MTARune.FIRE_RUNE, 5),
											new RuneRequirement(MTARune.COSMIC_RUNE, 1) },
									MTAStave.FIRE_STAFF, MTAStave.LAVA_STAFF), LEVEL_4_ENCHANT("Level 4 enchant",
											MTARoom.ENCHANTING, Normal.LEVEL_4_ENCHANT,
											"https://vignette.wikia.nocookie.net/2007scape/images/2/2d/Lvl-4_Enchant_icon.png/revision/latest?cb=20140107203219",
											new RuneRequirement[] { new RuneRequirement(MTARune.EARTH_RUNE, 10),
													new RuneRequirement(MTARune.COSMIC_RUNE, 1) },
											MTAStave.EARTH_STAFF, MTAStave.MUD_STAFF), LEVEL_5_ENCHANT(
													"Level 5 enchant", MTARoom.ENCHANTING, Normal.LEVEL_5_ENCHANT,
													"https://vignette.wikia.nocookie.net/2007scape/images/2/23/Lvl-5_Enchant_icon.png/revision/latest?cb=20140107203155",
													new RuneRequirement[] { new RuneRequirement(MTARune.WATER_RUNE, 15),
															new RuneRequirement(MTARune.EARTH_RUNE, 15),
															new RuneRequirement(MTARune.COSMIC_RUNE, 1) },
													MTAStave.EARTH_STAFF, MTAStave.WATER_STAFF,
													MTAStave.MUD_STAFF), LEVEL_6_ENCHANT("Level 6 enchant",
															MTARoom.ENCHANTING, Normal.LEVEL_6_ENCHANT,
															"https://vignette.wikia.nocookie.net/2007scape/images/6/6d/Lvl-6_Enchant_icon.png/revision/latest?cb=20140107203113",
															new RuneRequirement[] {
																	new RuneRequirement(MTARune.FIRE_RUNE, 20),
																	new RuneRequirement(MTARune.EARTH_RUNE, 20),
																	new RuneRequirement(MTARune.COSMIC_RUNE, 1) },
															MTAStave.AIR_STAFF, MTAStave.EARTH_STAFF,
															MTAStave.WATER_STAFF, MTAStave.FIRE_STAFF,
															MTAStave.LAVA_STAFF, MTAStave.MUD_STAFF,
															MTAStave.LAVA_STAFF), LEVEL_7_ENCHANT("Level 7 enchant",
																	MTARoom.ENCHANTING, Normal.LEVEL_7_ENCHANT,
																	"https://vignette.wikia.nocookie.net/2007scape/images/0/02/Lvl-7_Enchant_icon.png/revision/latest?cb=20160506121314",
																	new RuneRequirement[] {
																			new RuneRequirement(MTARune.SOUL_RUNE, 20),
																			new RuneRequirement(MTARune.BLOOD_RUNE, 20),
																			new RuneRequirement(MTARune.COSMIC_RUNE,
																					1) },
																	MTAStave.FIRE_STAFF), LOW_ALCHEMY(
																			"Low level alchemy", MTARoom.ALCHEMY,
																			Normal.LOW_LEVEL_ALCHEMY,
																			"https://vignette.wikia.nocookie.net/2007scape/images/a/ab/Low_level_alchemy_icon.png/revision/latest?cb=20130802134837",
																			new RuneRequirement[] {
																					new RuneRequirement(
																							MTARune.NATURE_RUNE, 1),
																					new RuneRequirement(
																							MTARune.FIRE_RUNE, 3) },
																			MTAStave.FIRE_STAFF,
																			MTAStave.LAVA_STAFF), HIGH_ALCHEMY(
																					"High level alchemy",
																					MTARoom.ALCHEMY,
																					Normal.HIGH_LEVEL_ALCHEMY,
																					"https://vignette.wikia.nocookie.net/2007scape/images/c/c0/High_Level_Alchemy_icon.png/revision/latest?cb=20130821140401",
																					new RuneRequirement[] {
																							new RuneRequirement(
																									MTARune.NATURE_RUNE,
																									1),
																							new RuneRequirement(
																									MTARune.FIRE_RUNE,
																									5) },
																					MTAStave.FIRE_STAFF,
																					MTAStave.LAVA_STAFF), BONES_TO_BANANAS(
																							"Bones 2 bananas",
																							MTARoom.GRAVEYARD,
																							Normal.BONES_TO_BANANAS,
																							"https://vignette.wikia.nocookie.net/2007scape/images/c/ca/Bones_to_bananas_icon.png/revision/latest?cb=20130802134634",
																							new RuneRequirement[] {
																									new RuneRequirement(
																											MTARune.NATURE_RUNE,
																											1),
																									new RuneRequirement(
																											MTARune.EARTH_RUNE,
																											2),
																									new RuneRequirement(
																											MTARune.WATER_RUNE,
																											2) },
																							MTAStave.EARTH_STAFF,
																							MTAStave.WATER_STAFF,
																							MTAStave.MUD_STAFF), BONES_TO_PEACHES(
																									"Bones 2 peaches",
																									MTARoom.GRAVEYARD,
																									Normal.BONES_TO_PEACHES,
																									"https://vignette.wikia.nocookie.net/2007scape/images/f/fe/Bones_to_peaches_icon.png/revision/latest?cb=20130920213624",
																									new RuneRequirement[] {
																											new RuneRequirement(
																													MTARune.NATURE_RUNE,
																													2),
																											new RuneRequirement(
																													MTARune.EARTH_RUNE,
																													2),
																											new RuneRequirement(
																													MTARune.WATER_RUNE,
																													4) },
																									MTAStave.EARTH_STAFF,
																									MTAStave.WATER_STAFF,
																									MTAStave.MUD_STAFF);

	MTASpell(String spellName, MTARoom room, Spell spell, String link, RuneRequirement[] reqs, MTAStave... staves) {
		this.spell = spell;
		this.spellName = spellName;
		this.link = link;
		this.requirements = reqs;
		this.staves = staves;
	}

	private Spell spell;
	private String spellName, link;
	private RuneRequirement[] requirements;
	private MTAStave[] staves;

	public String getLink() {
		return link;
	}

	public String getSpellName() {
		return spellName;
	}

	public Spell getSpell() {
		return spell;
	}

	public RuneRequirement[] getRequirements() {
		return requirements;
	}

	public MTAStave[] getStaves() {
		return staves;
	}

	public static MTASpell reverseSearch(String name) {
		for (MTASpell spell : MTASpell.values()) {
			if (spell.getSpellName().equals(name))
				return spell;
		}
		return MTASpell.NONE;
	}
}
