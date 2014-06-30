Mathematica Notebook and Package Discription

All of the notebooks employs different packages written by us.

0. StolperSamuelson - Graph.nb
We recommend to start with StolperSamuelson - Graph.nb
It allows to play around with the settings and see how prices change.

1. SSGraphs.nb

This notebook generates the Stolper-Samueslon Graphs and Regressions.

2. HistandStats.nb

This notebook generates the histograms and comparative statistics of the ACE and GE models.

3. DynamicsGraphs.nb

This notebook generates the dynamics graphs.

4. agentpack2.m

This package calls the agent based model and calculates the values for the different parameters 

along with their means, variances and standard-deviations. The user has to specify the number of 

iterations, the consumers per country, the firms per country, consumers preferences, the level of 

stock persistence. It is absolutely necessary that the folders .settings, bin and src along with the 

files .classpath and .project are in the same folder as this package. 

5. analsol.m

This package calls the general equilibrium model and solve for the equilibrium conditions. 

Again, the user has to specify the the consumers per country, the firms per country, consumers 

preferences, the level of stock persistence. 

6. upd.m

This package synchronizes the different coding conventions across the java code, agentpack2.m 

and analsol.m.

7. graphpack1.m

This package contains the processes required to generate the Stolper-Samuelson Graphs. It 

calls agentpack2.m, analsol.m and upd.m these packages must thus be in the same folder as 

graphpack1.m. SSGraphs shows the necessary variables to be specified to run this package.

8. graphpack2.m

This package contains the processes required to generate the histogram and stats. It requires 

agentpack2.m, analsol.m and upd.m.

9. graphpack3.m

This package contains the processes required to generate the dynamics graphs. It requires 

agentpack2.m, analsol.m and upd.m.

10. Appendix 1

Appendix 1 generates the FOC’s of the consumers’ problem.

11. Appendix 2

Appendix 2 generates the FOC’s of the firms’ problem.