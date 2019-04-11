package de.nhd.localsearch.algorithms;

import de.nhd.localsearch.problem.MOptProblem;
import de.nhd.localsearch.problem.OptProblem;
import de.nhd.localsearch.solutions.GeometryBasedSolution;
import de.nhd.localsearch.solutions.RuleBasedSolution;
import de.nhd.localsearch.solutions.Solution;

/**
 * Abstract class represents the class of neighborhood-based optimization
 * algorithms
 *
 */
public abstract class NeighborhoodBasedAlgo implements IOptAlgo {

	public static final String NEIGHBORHOOD_GEO = "GEOMETRY_BASED";
	public static final String NEIGHBORHOOD_PERM = "PERMUTATION";

	/**
	 * The optimization problem instance this algorithm has to solve
	 */
	protected final OptProblem optProblem;

	public OptProblem getOptProblem() {
		return optProblem;
	}

	/**
	 * The neighborhood relation used by this algorithm
	 */
	protected final String neighborhood;

	// TODO correct ? Should a neighborhood-based optimization algorithm hold a
	// current solution regarding the theory?
	protected Solution currentSolution;

	protected Solution startSolution;

	public NeighborhoodBasedAlgo(OptProblem optProblem, String neighborhood) {
		this.optProblem = optProblem;
		this.neighborhood = neighborhood;
		try {
			switch (this.neighborhood) {
				case NEIGHBORHOOD_GEO :
					this.startSolution = new GeometryBasedSolution(this.optProblem, null);
					break;
				case NEIGHBORHOOD_PERM :
					this.startSolution = new RuleBasedSolution(this.optProblem,
							((MOptProblem) this.optProblem).getRechtangles());
					break;
				default :
					throw new RuntimeException(
							"Unknown neighborhood: " + this.neighborhood);
			}
			this.currentSolution = (Solution) startSolution.clone();

		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
	}
	
	public abstract boolean isFinished();
}