package mechanical.modeling.algorithms;

import graph.algorithms.ShortestPathsToNode;
import graph.elements.WeightedGraph;

import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;

import mechanical.modeling.components.MechanicalEdge;
import mechanical.modeling.components.MechanicalSystem;
import mechanical.modeling.components.ReferenceFrame;

/**
 * This class can be used to find a set of modeling variables used
 * to generate the mechanical system's equations in a format that
 * will result in fast simulation times.
 * 
 * <p>
 * The current implementation assigns the number of modeling variables
 * associated with the component in the given domain as the weight of
 * each edge in each graph. Then, using the Shortest Paths to Node
 * algorithm of the graph theory package, a tree is selected that
 * minimizes the number of rotational and translational modeling
 * variable relating each of the system's reference frames to the
 * ground.
 * </p>
 * 
 * <p>
 * Note: This algorithm is in a state of flux. Currently, a simple and
 * effective algorithm has been temporarily implemented. Major
 * improvements will be made to this algorithm in the near future.
 * </p>
 *
 * @author Mathieu LŽger
 * @since Mar 23, 2014
 */
public final class OptimalModelingVariables {

    /*
     * This class contains only static utility methods. Therefore the constructor is private
     * so that nobody can instantiate it.
     */
    private OptimalModelingVariables(){
    }

    /**
     * Finds a set of modeling variables that can be used to generate the
     * mechanical system's equations in a format that will result in fast
     * simulation times.
     * 
     * @param mechanicalSystem is the mechanical system who's optimal
     *        modeling variables need to be found
     * @return a set of Strings defining the optimal modeling variables
     *        of the system.
     */
    public static Set<String> findVariables(MechanicalSystem mechanicalSystem){

        if (mechanicalSystem.getAllComponents().isEmpty()) {
            throw new IllegalArgumentException("The mechanical system must contain components");
        }

        ReferenceFrame ground = mechanicalSystem.getGround();

        WeightedGraph<ReferenceFrame, MechanicalEdge> rotationalGraph = mechanicalSystem.getRotationalGraph();
        WeightedGraph<ReferenceFrame, MechanicalEdge> translationalGraph = mechanicalSystem.getTranslationalGraph();

        Set<MechanicalEdge> rotationalTree = getTree(rotationalGraph, ground);
        Set<MechanicalEdge> translationalTree = getTree(translationalGraph, ground);

        Set<String> variables = new LinkedHashSet<String>();
        variables.addAll(getTreeEdgeVariables(rotationalTree));
        variables.addAll(getTreeEdgeVariables(translationalTree));
        return variables;
    }

    /*
     * Assigns the edge weights, runs the ShortestPathsToNode algorithm and
     * returns the tree edges that were found
     */
    protected static Set<MechanicalEdge> getTree(WeightedGraph<ReferenceFrame, MechanicalEdge> graph, ReferenceFrame ground) {
        for(MechanicalEdge edge : graph.getAllEdges()) {
            edge.setWeight(edge.getNumberOfVariables());
        }

        ShortestPathsToNode<ReferenceFrame, MechanicalEdge> findShortestPaths = new ShortestPathsToNode<ReferenceFrame, MechanicalEdge>(graph, ground);
        return findShortestPaths.getShortestPathsTreeEdges();
    }

    /*
     * Retrieves the variable strings of each edge in the set passed in and
     * returns it with each variable string being appends an underscore and
     * the edge name.
     */
    protected static Set<String> getTreeEdgeVariables(Set<MechanicalEdge> edges) {
        Set<String> variables = new LinkedHashSet<String>();
        for(MechanicalEdge edge : edges) {
            Collections.addAll(variables, edge.getVariables());
        }
        return variables;
    }
}
