package baladeva.utils;

import gameframework.game.GameConfiguration;

import gameframework.motion.overlapping.OverlapRulesApplier;

/**
 * Class extending the frameworks' configuration. Used to get the overlap rules
 * 
 * @author WISSOCQ Sarah, AGEZ Adrien, COJEZ Arnaud, MOEVI Alexandre, PETIT
 *         Antoine
 *
 */
public class BaladevaConfiguration extends GameConfiguration {
	
	/**
	 * Constructor with parameterisable size. Create a new GameConfiguration with the specified
	 * parameters. If 0 is specified as one of the parameter, the constructor uses the default
	 * value for this parameter. 
	 * @param nbRows The number of rows of the window (default value: {@value #DEFAULT_NBROWS}).
	 * @param nbColumns The number of columns of the window (default value: {@value #DEFAULT_NBCOL}). 
	 * @param spriteSize The size of the sprites displayed (default value: {@value #DEFAULT_SPRITESIZE}).
	 * @param nbLives The number of lives of the player (default value: {@value #DEFAULT_NBLIVES}).
	 */
	public BaladevaConfiguration(int nbRows, int nbColumns, int spriteSize,
			int nbLives) {
		super(nbRows,nbColumns,spriteSize,nbLives);
	}

	/*
	 * (non-Javadoc)
	 * @see gameframework.game.GameConfiguration#createOverlapRulesApplier()
	 */
	@Override
	public OverlapRulesApplier createOverlapRulesApplier() {
		return new BaladevaOverlapRules();
	}
}
