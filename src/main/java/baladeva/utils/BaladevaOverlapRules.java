package baladeva.utils;

import baladeva.entities.*;
import baladeva.entities.enemies.*;
import gameframework.motion.overlapping.OverlapRulesApplierDefaultImpl;

public class BaladevaOverlapRules extends OverlapRulesApplierDefaultImpl {

	public void overlapRule(BaladevaPlayer e1, BaladevaWolf e) {
		if (!e1.invincible() && !e.invincible())
			e1.getHit();
	}

	public void overlapRule(BaladevaPlayer e1, BaladevaKnight e) {
		if (!e1.invincible() && !e.invincible())
			e1.getHit();
	}
	
	public void overlapRule(BaladevaPlayer e1, BaladevaRed e) {
		if (!e1.invincible() && !e.invincible())
			e1.getHit();
	}

	public void overlapRule(BaladevaPlayer e1, BaladevaBat e2) {
		e1.getHit();
	}

	public void overlapRule(BaladevaWolf e1, BaladevaHit e2) {
		if (!e1.invincible())
			e1.getHit();
	}

	public void overlapRule(BaladevaBat e1, BaladevaHit e2) {
		if (!e1.invincible())
			e1.getHit();
	}
	
	public void overlapRule(BaladevaKnight e1, BaladevaHit e2) {
		if (!e1.invincible())
			e1.getHit();
	}
	
	public void overlapRule(BaladevaRed e1, BaladevaHit e2) {
		if (!e1.invincible())
			e1.getHit();
	}

}
