package baladeva.utils;

import baladeva.entities.*;
import baladeva.entities.enemies.*;
import gameframework.motion.overlapping.OverlapRulesApplierDefaultImpl;

public class BaladevaOverlapRules extends OverlapRulesApplierDefaultImpl {

	public void overlapRule(BaladevaPlayer e1, BaladevaWolf e2) {
		//System.out.println("Le loup t'as mangé, RIP in pepperonis");
	}
	
	public void overlapRule(BaladevaPlayer e1, BaladevaBat e2) {
		//System.out.println("La chauve-souris t'a touché, get rekt");
	}
	
	public void overlapRule(BaladevaHit e1, BaladevaBat e2) {
		System.out.println("Paan chauve-souris");
	}
	
	public void overlapRule(BaladevaHit e1, BaladevaWolf e2) {
		System.out.println("Paan paan Loup");
	}
	
}
