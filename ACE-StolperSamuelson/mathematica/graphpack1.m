(* ::Package:: *)

(** Stolper-Samualson Package **)
steps=((max-1)/changesize)+1;
For[j=0,j<=steps,j++,pizzaPreference=1+(j-1)*0.1;Subscript[app, j]=pizzaPreference;<<upd.m;<<agentpack2.m;<<analsol.m;Subscript[ARW, j]=MARW;Subscript[AVW, j]=VARP;Subscript[ARP, j]=MARP;Subscript[AVP, j]=VARP;Subscript[GRP, j]=GERelPrice;Subscript[GRW, j]=GERelWage]
SSPiPref=Table[Subscript[app, tt],{tt,1,81}];
SSARW=Table[Subscript[ARW, tt],{tt,1,81}];
SSAVW=Table[Subscript[AVW, tt],{tt,1,81}];
SSARP=Table[Subscript[ARP, tt],{tt,1,81}];
SSAVP=Table[Subscript[AVP, tt],{tt,1,81}];
SSGRP=Table[Subscript[GRP, tt],{tt,1,81}];
SSGRW=Table[Subscript[GRW, tt],{tt,1,81}];

