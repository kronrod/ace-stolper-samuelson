(* ::Package:: *)

Needs["JLink`"];

ReinstallJava[ClassPath->  path <> "\\bin"];
pizzaPreferencei = firstpref;
pizzaPreferences= pizzaPreferencei ;
sv=Rationalize[switchat];
svv=Rationalize[1-sv];
country = JavaNew["com.abs.World", 24, consumerEach, firmsPerGood, stockPersistence, smartPriceAdaption];
"Italians then swiss in Pizza preference";
country@updatePreferences[firstpref, firstpref, highProductivity];
country@step[iterations*sv];
country@updatePreferences[secondpref, secondpref, highProductivity]; (** Preference shock after 60% of time **)
country@step[iterations*svv];
legend = country@getData[]@getLabels[];
legend={"Avg Italian Utility","Avg Swiss Utility","pizza production","pizza consumption","Pizza Price","fondue production","fondue consumption","Fondue Price","Swiss hour production","Swiss hour consumption","Swiss Wage per Hour","Italian hour production","Italian hour consumption","Italian Wage per Hour"}
data = country@getData[]@getAllData[];
dataT = Transpose[data];

ReleaseJavaObject[country];

selectColumns[data_] := {data[[5]], data[[8]], data[[11]], data[[14]]};
G1=ListLinePlot[selectColumns[dataT],PlotLegends-> Placed[selectColumns[legend],{0.5,0}], graphstyle];
selectColumns[data_] := {data[[1]], data[[2]]};
G2=ListLinePlot[selectColumns[dataT],graphstyle];
selectColumns[data_] := {data[[4]], data[[7]], data[[10]], data[[13]]};
G3=ListLinePlot[selectColumns[dataT],PlotLegends-> Placed[selectColumns[legend],{0.5,0}],graphstyle];
selectColumns[data_] := 
{data[[3]], data[[4]], data[[6]], data[[7]]};
