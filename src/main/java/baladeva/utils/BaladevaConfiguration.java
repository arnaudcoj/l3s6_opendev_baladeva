package baladeva.utils;

import gameframework.game.GameConfiguration;
import gameframework.motion.overlapping.OverlapRulesApplier;

public class BaladevaConfiguration extends GameConfiguration {

	
	public BaladevaConfiguration(int i, int j, int k, int l) {
		super(i,j,k,l);
	}

	public OverlapRulesApplier createOverlapRulesApplier() {
		return new BaladevaOverlapRules();
	}
}
