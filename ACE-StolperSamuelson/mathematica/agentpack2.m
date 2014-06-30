(* ::Package:: *)

(** This calls the Agent Based Model **)
start=2;
Needs["JLink`"];
ReinstallJava[ClassPath-> path <>"\\bin"];

country = JavaNew["com.abs.World", 24, consumerEach, firmsPerGood, stockPersistence, smartPriceAdaption];
"Italians then swiss in Pizza preference";
country@updatePreferences[pizzaPreferencei, pizzaPreferences, highProductivity];
country@step[iterations];
legend = country@getData[]@getLabels[];
data = country@getData[]@getAllData[];
dataT = Transpose[data];
(**Key: A(n or g)v 
A- Agent based model,
n - Country: Sw-Swiss, It- Italian,
g - good: Pi-Pizza, Fn- Fondue
v - variable: U-Utility, Prod-Production, Cons-consumption, Hp= **)
AItU=Table[dataT[[1]][[i]],{i,start,iterations}]; 
ASwU=Table[dataT[[2]][[i]],{i,start,iterations}];
APiProd=Table[dataT[[3]][[i]],{i,start,iterations}];
APiCons=Table[dataT[[4]][[i]],{i,start,iterations}];
APiWaste=APiProd-APiCons;
APiPrice=Table[dataT[[5]][[i]],{i,start,iterations}];
AFnProd=Table[dataT[[6]][[i]],{i,start,iterations}];
AFnCons=Table[dataT[[7]][[i]],{i,start,iterations}];
AFnWaste=AFnProd-AFnCons;
AFnPrice=Table[dataT[[8]][[i]],{i,start,iterations}];
ASwHp=Table[dataT[[9]][[i]],{i,start,iterations}];
ASwHc=Table[dataT[[10]][[i]],{i,start,iterations}];
ASwWage=Table[dataT[[11]][[i]],{i,start,iterations}];
AItHp= Table[dataT[[12]][[i]],{i,start,iterations}];
AItHc=Table[dataT[[13]][[i]],{i,start,iterations}];
AItWage=Table[dataT[[14]][[i]],{i,start,iterations}];
(** All Relaitive Values have Either Pizza or Fondue as the numerator **)
ARelPrice=APiPrice/AFnPrice;
ARelUtill=AItU/ASwU;
ARelWage=AItWage/ASwWage;
ARelProd=APiProd/AFnProd;
ARelCons=APiCons/AFnCons;
APiWstProp=APiWaste/APiProd;
AFnWstProp=AFnWaste/AFnProd;
ARelWst=APiWaste/AFnWaste;
AIWaPi=APiPrice/AItWage;
AIWaFn=AFnPrice/AItWage;
ASWaPi=APiPrice/ASwWage;
ASWaFn=AFnPrice/ASwWage;
(** Means and Variances of Variables **)
MARP=Mean[ARelPrice];
MARU=Mean[ARelUtill];
MARW=Mean[ARelWage];
MARPr=Mean[ARelProd];
MARC=Mean[ARelCons];
MAPW=Mean[APiWstProp];
MAFW=Mean[AFnWstProp];
MARWa=Mean[ARelWst];
MAIWP=Mean[AIWaPi];
MAIWF=Mean[AIWaFn];
MASWP=Mean[ASWaPi];
MASWF=Mean[ASWaFn];

VARP=Variance[ARelPrice];
VARU=Variance[ARelUtill];
VARW=Variance[ARelWage];
VARPr=Variance[ARelProd];
VARC=Variance[ARelCons];
VAPW=Variance[APiWstProp];
VAFW=Variance[AFnWstProp];
VARWa=Variance[ARelWst];
VAIWP=Variance[AIWaPi];
VAIWF=Variance[AIWaFn];
VASWP=Variance[ASWaPi];
VASWF=Variance[ASWaFn]

SDARP=StandardDeviation[ARelPrice];
SDARU=StandardDeviation[ARelUtill];
SDARW=StandardDeviation[ARelWage];
SDARPr=StandardDeviation[ARelProd];
SDARC=StandardDeviation[ARelCons];
SDAPW=StandardDeviation[APiWstProp];
SDAFW=StandardDeviation[AFnWstProp];
SDARWa=StandardDeviation[ARelWst];



