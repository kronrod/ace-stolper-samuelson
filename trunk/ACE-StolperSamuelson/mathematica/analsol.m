(* ::Package:: *)

Clear[labi, labs,xps,xfs,xpi,xfi,eq1,eq2,resLip,resLsf,yp,yf,cp,cf,wi,ws,pf,pp,xp,xf,lsf,lip,li,ls]

pp = 1; (** Set pizza price to 1 **)

labi =citizensi*li;
labs=citizenss*ls;
(** Consumer demand functions for log utility as in (4) and (5)**)
li = 24*(preffi+prefpi)/(prefwi+(preffi+prefpi));
ls = 24*(preffs+prefps)/(prefws+(preffs+prefps)); 
xps =(ls prefps ws)/(pp (preffs+prefps)); (** ws: Swiss wage **)
xfs =(ls preffs ws)/(pf (preffs+prefps));(** pf: fondue price **)
xpi =(li prefpi wi)/(pp (preffi+prefpi));(** wi: Italian wage **)
xfi = (li preffi wi)/(pf (preffi+prefpi)); 
(** Aggregate consumer demand **)
xp = citizensi*xpi+citizenss*xps;
xf = citizenss*xfs +citizensi* xfi;

(** Labour demand asin (8) and using the aggregate resource constraints from (9) (10) and (11)**)
eq1 =(wi/ws)== (iprodp* (labs-lsf)) / (sprodp*lip)
eq2 =(ws/wi)== (sprodf* (labi-lip))/(iprodf* lsf)

resLip = Solve[eq1, lip][[1]][[1]]
resLsf = Solve[eq1, lsf][[1]][[1]]
lsf = lsf/.Solve[eq2/. resLip, lsf][[1]]
lip = lip/.Solve[eq2/. resLsf, lip][[1]]

(** Production from (6), (9), (10) and (11)**)
(** Note that yp=firmsp*y as in (9) **) 
yp = firmsp * (iprodp * Log[lip/firmsp] + sprodp * Log[(labs - lsf)/firmsp]);
yf = firmsf * (sprodf * Log[lsf/firmsf] + iprodf * Log[(labi - lip)/firmsf]);

(** Production costs **)
(** Note that cp=firmsp*c as in (9) **)
cp = lip * wi + (labs - lsf) * ws;
cf = lsf * ws + (labi - lip) * wi;

(** Solving the Equilibrium Outcome**)
(** The first conditions, xp==yp and xf==yf, are the equations characterising equilibrium in the goods markets **)
(** pf*yf=cf is the zero profit condition. As we only solve for one price, the second zero profit condition is not required **) 
(** FindRoot is similair as Solve, with the advantage of using Newton Methods. Unlike Mathematica's Solve, FindRoot gives solutions in a reasonable amount of time **)
resw = FindRoot[{xp==yp, xf==yf, pf*yf == cf}, {ws, 0.5}, {wi, 0.5}, {pf, 0.5},WorkingPrecision->500];

(** This determines the wages and prices at equilibrium **)
wi = wi/.resw;
ws = ws/.resw;
pf = pf/.resw;

(** Verification **)
(** The functions below ensure that the Equilibrium Conditions are Satisfied **)

V1=xp==yp;
V2=xf==yf;
V3=pp * yp==cp;
V4=pf * yf==cf;
(**Key: GE(n or g)v 
GE-  General Equilibrium Model,
n - Country: Sw-Swiss, It- Italian,
g - good: Pi-Pizza, Fn- Fondue
v - variable: U-Utility, Prod-Production, Cons-consumption, Hp= **)
GEItU=util[xpi, xfi,li,prefpi,preffi,prefwi]; 
GESwU=util[xps, xfs,ls,prefps,preffs,prefws];
GEPiProd=yp;
GEPiCons=xp;
GEPiWaste=yp-xp;
GEPiPrice=pp;
GEFnProd=yf;
GEFnCons=xf;
GEFnWaste=yf-xf;
GEFnPrice=pf;
GESwPiHc=labs-lsf;
GESwFnHc=lsf;
GEItPiHc=lip
GEItFnHc=labs-lip
GESwWage=ws;
GEItWage=wi;

GERelPrice=GEPiPrice/GEFnPrice;

GERelUtill=GEItU/GESwU;
GERelWage=GEItWage/GESwWage;
GERelProd=GEPiProd/GEFnProd;
GERelCons=GEPiCons/GEFnCons;
GEPiWstProp=GEPiWaste/GEPiProd;
GEFnWstProp=GEFnWaste/GEFnProd;
GEIWaPi=GEPiPrice/GEItWage;
GEIWaFn=GEFnPrice/GEItWage;
GESWaPi=GEPiPrice/GESwWage;
GESWaFn=GEFnPrice/GESwWage;
util[pizza_, fondue_,worked_,prefp_,preff_,prefw_] := prefp * Log[pizza] + preff * Log[fondue] + prefw * Log[24-worked];

result  = {{"Italian Citizens",citizensi},
{"Swiss Citizens",citizenss},
{"Pizza Firms",firmsp},
{"Fondue Firms",firmsf},
{"Italian Productivity in Fondue",iprodf},
{"Swiss Productivity in Fondue",sprodf},
{"Italian Productivity in Pizza",iprodp},
{"Swiss Productivity in Pizza",sprodp},
{"Swiss Fondue Preference",preffs},
{"Swiss Pizza Preference",prefps},
{"Italian Fondue Preference",preffi},
{"Italian Pizza Preference",prefpi},
{"Italian util" ,  util[xpi, xfi,li,prefpi,preffi,prefwi]},
{"Swiss utility", util[xps, xfs,ls,prefps,preffs,prefws]},
{"Italian To Swiss Util Ratio",N[util[xpi, xfi,li,prefpi,preffi,prefwi]/util[xps, xfs,ls,prefps,preffs,prefws]]},
{"Pizza/Fondue price", pp/pf},
{"Italian/Swiss wage", wi/ws},
{"Pitzzas/Fondue Prod",yp/yf},
{"Pizzas produced", yp},
{"Fondue produced",yf},
{"Pizzas consumed", xp},
{ "Fondue consumed",xf},
{ "Italian Total hours worked", labi},
{ "Swiss Total hours worked", labs},
{"Italian Pizza hours worked", lip},
{"Italian Fondue hours worked", labi-lip},
{"Swiss Pizza hours worked", labs-lsf},
{"Swiss Fondue hours worked",lsf}};

GEBTable=If[V1==V2==V3==V4,TableForm[result],"Equilibrium Conditions Not Met"];

RResult = {{"Relative Country Size", N[citizensi/citizenss]},
{"Relative Firm size",N[firmsp/firmsf]},
{"Itallian Productivity Pizza/Fondue",N[iprodp/iprodf]},
{"Swiss Productivity Pizza/Fondue",N[sprodp/sprodf]},
{"Italian/Swiss Productivity in Pizza",N[iprodp/sprodp]},
{"Italian/Swiss Productivity in Fondue",N[iprodf/sprodf]},
{"Italian Pizza/Fondue Preference",N[prefpi/preffi]},
{"Swiss Pizza/Fondue Preference",N[prefps/preffs]},
{"Itallian/Swiss Pizza Preference",N[prefpi/prefps]},
{"Itallian/Swiss Fondue Preference",N[preffi/preffs]},
{"Italian To Swiss Util Ratio",N[util[xpi, xfi,li,prefpi,preffi,prefwi]/util[xps, xfs,ls,prefps,preffs,prefws]]},
{"Pizza/Fondue price", N[pp/pf]},
{"Italian/Swiss wage", N[wi/ws]},
{"Pitzzas/Fondue Prod",N[yp/yf]},
{"Italian/Swiss Total hours worked", N[labi/labs]},
{"Relative Italian Pizza/Fondue hours", N[lip/(labi-lip)]},
{"Relative Swiss Pizza/Fondue Hours", N[(labs-lsf)/lsf]},
{"Italian/Swiss Pizza Hours",N[lip/(labs-lsf)]},
{"Italian/Swiss Fondue Hours", N[(labi-lip)/lsf]}};

GERelTable=If[V1==V2==V3==V4,TableForm[RResult],"Equilibrium Conditions Not Met"];


