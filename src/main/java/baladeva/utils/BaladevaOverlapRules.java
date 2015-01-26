package baladeva.utils;

import baladeva.entities.*;
import baladeva.entities.enemies.*;
import gameframework.motion.overlapping.OverlapRulesApplierDefaultImpl;

/**
 * Class extending the frameworks' OverlapRulesApplierDefaultImpl. Used to apply the overlap
 * rules
 * 
 * @author WISSOCQ Sarah, AGEZ Adrien, COJEZ Arnaud, MOEVI Alexandre, PETIT
 *         Antoine
 *
 */
public class BaladevaOverlapRules extends OverlapRulesApplierDefaultImpl {

	/**
	 * Overlap between a BaladevaPlayer and a BaladevaWolf 
	 * @param e1 BaladevaPlayer
	 * @param e2 BaladevaWolf 
	 */
	public void overlapRule(BaladevaPlayer e1, BaladevaWolf e2) {
		if (!e1.invincible() && !e2.invincible())
			e1.getHit();
	}

	/**
	 * Overlap between a BaladevaPlayer and a BaladevaRed 
	 * @param e1 BaladevaPlayer
	 * @param e2 BaladevaRed
	 */
	public void overlapRule(BaladevaPlayer e1, BaladevaRed e) {
		if (!e1.invincible() && !e.invincible())
			e1.getHit();
	}

	/**
	 * Overlap between a BaladevaPlayer and a BaladevaBat
	 * @param e1 BaladevaPlayer
	 * @param e2 BaladevaBat
	 */
	public void overlapRule(BaladevaPlayer e1, BaladevaBat e2) {
		if (!e1.invincible() && !e2.invincible())
			e1.getHit();
	}

	/**
	 * Overlap between a BaladevaPlayer and a BaladevaKnight
	 * @param e1 BaladevaPlayer
	 * @param e2 BaladevaKnight
	 */
	public void overlapRule(BaladevaPlayer e1, BaladevaKnight e2) {
		if (!e1.invincible() && !e2.invincible())
			e1.getHit();
	}

	/**
	 * Overlap between a BaladevaWolf and a BaladevaHit 
	 * @param e1 BaladevaWolf
	 * @param e2 BaladevaHit 
	 */
	public void overlapRule(BaladevaWolf e1, BaladevaHit e2) {
		if (!e1.invincible())
			e1.getHit();
	}

	/**
	 * Overlap between a BaladevaBat and a BaladevaHit 
	 * @param e1 BaladevaBat
	 * @param e2 BaladevaHit 
	 */
	public void overlapRule(BaladevaBat e1, BaladevaHit e2) {
		if (!e1.invincible())
			e1.getHit();
	}

	/**
	 * Overlap between a BaladevaKnight and a BaladevaHit 
	 * @param e1 BaladevaKnight
	 * @param e2 BaladevaHit
	 */
	public void overlapRule(BaladevaKnight e1, BaladevaHit e2) {
		if (!e1.invincible())
			e1.getHit();
	}

	/**
	 * Overlap between a BaladevaRed and a BaladevaHit 
	 * @param e1 BaladevaRed
	 * @param e2 BaladevaHit
	 */
	public void overlapRule(BaladevaRed e1, BaladevaHit e2) {
		if (!e1.invincible())
			e1.getHit();
	}

}
