package com.zisal.twit.crawl.core.pagerank;

import edu.uci.ics.jung.algorithms.importance.RelativeAuthorityRanker;
import edu.uci.ics.jung.graph.DirectedGraph;
import edu.uci.ics.jung.graph.Edge;
import edu.uci.ics.jung.graph.Vertex;
import edu.uci.ics.jung.utils.MutableDouble;
import edu.uci.ics.jung.utils.NumericalPrecision;
import edu.uci.ics.jung.utils.Pair;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * Created by Ladies Man on 11/8/2015.
 */
public class JungPageRank extends RelativeAuthorityRanker {

    public final static String KEY = "jung.algorithms.importance.PageRank.RankScore";
    private double mAlpha;
    private HashMap mPreviousRankingsMap;
    private Set mUnreachableVertices;
    private Set mReachableVertices;
    private Set mLeafNodes;

    /**
     * Basic constructor which initializes the algorithm
     * @param graph the graph whose nodes are to be ranked
     * @param bias the value (between 0 and 1) that indicates how much to dampen the underlying markov chain
     * with underlying uniform transitions over all nodes. Generally, values between 0.0-0.3 are used.
     */
    public JungPageRank(DirectedGraph graph, double bias) {
        initialize(graph, bias, null);
        initializeRankings(graph.getVertices(), new HashSet());
    }

    /**
     * Specialized constructor that allows the user to specify an edge key if edges already have user-defined
     * weights assigned to them.
     * @param graph the graph whose nodes are to be ranked
     * @param bias the value (between 0 and 1) that indicates how much to dampen the underlying markov chain
     * with underlying uniform transitions over all nodes. Generally, values between 0.0-0.3 are used.
     * @param edgeWeightKeyName if non-null, uses the user-defined weights to compute the transition probabilities;
     * if null then default transition probabilities (1/outdegree(u)) are used
     */
    public JungPageRank(DirectedGraph graph, double bias, String edgeWeightKeyName) {
        initialize(graph, bias, edgeWeightKeyName);
        initializeRankings(graph.getVertices(), new HashSet());
    }

    protected JungPageRank(DirectedGraph graph, double bias, String edgeWeightKeyName, Pair reachables) {
        initialize(graph, bias, edgeWeightKeyName);
        initializeRankings((Set) reachables.getFirst(), (Set) reachables.getSecond());
    }

    protected void initialize(DirectedGraph graph, double bias, String edgeWeightKeyName) {
        super.initialize(graph, true, false);
        if ((bias < 0) || (bias > 1.0)) {
            throw new IllegalArgumentException("Bias " + bias + " must be between 0 and 1.");
        }
        mAlpha = bias;
        if (edgeWeightKeyName == null) {
            assignDefaultEdgeTransitionWeights();
        } else {
            setUserDefinedEdgeWeightKey(edgeWeightKeyName);
            normalizeEdgeTransitionWeights();
        }

    }

    protected void initializeRankings(Set reachableVertices, Set unreachableVertices) {

        mReachableVertices = reachableVertices;
        double numVertices = reachableVertices.size();
        mPreviousRankingsMap = new HashMap();
        mLeafNodes = new HashSet();
        for (Iterator vIt = mReachableVertices.iterator(); vIt.hasNext();) {
            Vertex currentVertex = (Vertex) vIt.next();
            setRankScore(currentVertex, 1.0 / numVertices);
            setPriorRankScore(currentVertex, 1.0 / numVertices);
            mPreviousRankingsMap.put(currentVertex, new MutableDouble(1.0 / numVertices));
            if (currentVertex.outDegree() == 0) {
                mLeafNodes.add(currentVertex);
            }
        }

        mUnreachableVertices = unreachableVertices;
        for (Iterator vIt = mUnreachableVertices.iterator(); vIt.hasNext();) {
            Vertex currentVertex = (Vertex) vIt.next();
            setRankScore(currentVertex, 0);
            setPriorRankScore(currentVertex, 0);
            mPreviousRankingsMap.put(currentVertex, new MutableDouble(0));
        }
    }

    protected void reinitialize() {
        initializeRankings(mReachableVertices, mUnreachableVertices);
    }

    protected void updateRankings() {
        double totalSum = 0;

        for (Iterator vIt = mReachableVertices.iterator(); vIt.hasNext();) {
            Vertex currentVertex = (Vertex) vIt.next();

//            Set incomingEdges = null;
//            if (getGraph().isDirected()) {
//                incomingEdges = currentVertex.getInEdges();
//            } else {
//                incomingEdges = currentVertex.getIncidentEdges();
//            }
            Set incomingEdges = currentVertex.getInEdges();

            double currentPageRankSum = 0;
            for (Iterator edgeIt = incomingEdges.iterator(); edgeIt.hasNext();) {
                Edge incomingEdge = (Edge) edgeIt.next();
                if (mUnreachableVertices.contains(incomingEdge.getOpposite(currentVertex)))
                    continue;

                double currentWeight = getEdgeWeight(incomingEdge);
                currentPageRankSum += ((Number) mPreviousRankingsMap.get(incomingEdge.getOpposite(currentVertex))).doubleValue() * currentWeight;
            }

            if (getPriorRankScore(currentVertex) > 0) {
                for (Iterator leafIt = mLeafNodes.iterator(); leafIt.hasNext();) {
                    Vertex leafNode = (Vertex) leafIt.next();
                    double currentWeight = getPriorRankScore(currentVertex);
                    currentPageRankSum += ((Number) mPreviousRankingsMap.get(leafNode)).doubleValue() * currentWeight;
                }
            }

            //totalSum += currentPageRankSum;
            totalSum += currentPageRankSum * (1.0 - mAlpha) + mAlpha * getPriorRankScore(currentVertex);
            setRankScore(currentVertex, currentPageRankSum * (1.0 - mAlpha) + mAlpha * getPriorRankScore(currentVertex));
        }

        if (!NumericalPrecision.equal(totalSum, 1, .05)) {
            System.err.println("Page rank scores can not be generated because the specified graph is not connected.");
            System.out.println(totalSum);
        }
    }

    protected double evaluateIteration() {
        updateRankings();

        double rankingMSE = 0;

        //Normalize rankings and test for convergence
        for (Iterator vIt = mReachableVertices.iterator(); vIt.hasNext();) {
            Vertex currentVertex = (Vertex) vIt.next();
            MutableDouble previousRankScore = (MutableDouble) mPreviousRankingsMap.get(currentVertex);
            rankingMSE += Math.pow(getRankScore(currentVertex) - previousRankScore.doubleValue(), 2);
            previousRankScore.setDoubleValue(getRankScore(currentVertex));
        }

        rankingMSE = Math.pow(rankingMSE / getVertices().size(), 0.5);

        return rankingMSE;
    }

    /**
     * The user datum key used to store the rank scores.
     * @return the key
     */
    public String getRankScoreKey() {
        return KEY;
    }

}
