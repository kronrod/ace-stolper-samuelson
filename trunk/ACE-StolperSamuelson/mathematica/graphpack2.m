(* ::Package:: *)

(** Histogram and Stats Pack **)
(** This Package generates the histograms and plots for the results section **)
citizensi=consumerEach;
citizenss=consumerEach;
firmsf=firmsPerGood;
firmsp=firmsPerGood;
sprodf=highProductivity; 
sprodp=10-sprodf;
iprodp=highProductivity;
iprodf=10-iprodp;
prefpi=pizzaPreferencei
prefps=pizzaPreferences
preffs=10-prefps;
preffi=10-prefpi;
<<agentpack2.m
<<analsol.m
If[V1==V2==V3==V4,"","Warning! Equilibrium Conditions Not Met"]
AS={SmoothHistogram[ARelPrice,GridLines->{{{GERelPrice,Red}},None},PlotLegends->Placed[{"Distribution of Relative Prices", "GE Relative Prices"},{0.5,0}],graphstyle],
SmoothHistogram[ARelWage,GridLines->{{{GERelWage,Red}},None},PlotLegends->Placed[{"Distribution of Relative Wages", "GE Relative Wages"},{0.5,0}],graphstyle],
SmoothHistogram[ARelProd,GridLines->{{{GERelProd,Red}},None},PlotLegends->Placed[{"Distribution of Relative Production", "GE Relative Production"},{0.5,0}],graphstyle],
SmoothHistogram[ARelCons,GridLines->{{{GERelCons,Red}},None},PlotLegends->Placed[{"Distribution of Relative Consumption", "GE Relative Consumption"},{0.5,0}],graphstyle],
SmoothHistogram[ARelUtill,GridLines->{{{GERelUtill,Red}},None},PlotLegends->Placed[{"Distribution of Relative Utility", "GE Relative Utility"},{0.5,0}],graphstyle],
SmoothHistogram[N[AIWaPi],GridLines->{{{GEIWaPi,Red}},None},PlotLegends->Placed[{"Distribution of Pizza Price to Italian Wage"},{0.5,0}],graphstyle]};

HS=Do[{Print["Figure",i],Print[AS[[i]]]},{i,1,6}];
ACETAB=TableForm[{{"Mean of ACE","Variance"},
{N[MARU,dg],VARU},
{N[MARPr,dg],VARPr},
{N[MARC,dg],VARC},
{N[MAPW,dg],VAPW},
{N[MAFW,dg],VAFW},
{N[MARWa,dg],VARWa},
{N[MARP,dg],VARP},
{N[MARW,dg],VARW},
{N[MAIWP,dg],VAIWP},
{N[MASWP,dg],VASWP}}];

GET=TableForm[{{"Variable","GE Result"},
{"Relative Utility",N[GERelUtill,dg]},
{"Relative Production",N[GERelProd,dg]},
{"Relative Consumption",N[GERelCons,dg]},
{"Pizza Waste as Prop of Prod",Rationalize[GEPiWaste]},
{"Fondue Waste as Prop of Prod",Rationalize[GEFnWaste]},
{"Relative Waste", 1},
{"Relative Price",N[GERelPrice,dg]},
{"Relative Wage",N[GERelWage,dg]},
{"Pizza Price to Italian Wage",N[GEIWaPi]},
{"Pizza Price to Swiss Wage",N[GESWaPi]}}];





