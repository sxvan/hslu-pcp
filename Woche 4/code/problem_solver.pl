:- use_module(library(http/http_client)).
:- use_module(library(http/http_json)).
:- use_module(library(clpfd)).

% relationship -------------------------------------------------------------------------
female(mary). female(liz). female(mia). female(tina). female(ann). female(sue). % all females 
male(mike). male(jack). male(fred). male(tom). male(joe). male(jim).            % all males 
parent(mary, mia). parent(mary, fred). parent(mary, tina).                      % all childern of mary 
parent(mike, mia). parent(mike, fred). parent(mike, tina).                      % all children of mike 
parent(liz, tom). parent(liz, joe).                                             % all childern of liz 
parent(jack, tom). parent(jack, joe).                                           % all childern of jack 
parent(mia, ann).                                                               % all childern of mia 
parent(tina, sue). parent(tina, jim).                                           % all childern of tina 
parent(tom, sue). parent(tom, jim).                                             % all childern of tom


mother(X, Y) :- female(X), parent(X, Y).
father(X, Y) :- male(X), parent(X, Y).

sibling(X, Y) :- X \= Y, parent(Z, X), parent(Z, Y).

grandparent(X, Y) :- parent(X, Z), parent(Z, Y).
grandfather(X, Y) :- male(X), grandparent(X, Y).
grandmother(X, Y) :- female(X), grandparent(X, Y).

offspring(X, Y) :- parent(Y, X).
offspring(X, Y) :- grandparent(Y, X).
% --------------------------------------------------------------------------------------



% sudoku -------------------------------------------------------------------------------
replace_0([], []).
replace_0([0|T], [_|R]) :- replace_0(T, R).
replace_0([H|T], [H|R]) :- H \= 0, replace_0(T, R).

solve_sudoku(Rows) :-
  append(Rows, Vs), Vs ins 1..9, 
  maplist(all_distinct, Rows),
  transpose(Rows, Columns),
  maplist(all_distinct, Columns),
  Rows = [A, B, C, D, E, F, G, H, I],
  blocks(A, B, C), blocks(D, E, F), blocks(G, H, I),
  maplist(label, Rows).

blocks([], [], []).
blocks([A, B, C|Bs1], [D, E, F|Bs2], [G, H, I|Bs3]) :-
  all_distinct([A, B, C, D, E, F, G, H, I]),
  blocks(Bs1, Bs2, Bs3).
% --------------------------------------------------------------------------------------


solve(Problem, Id) :-
  format(string(SolutionUrl), 'http://localhost:16316/problem/~w/', [Problem]),
  string_concat(SolutionUrl, Id, ProblemUrl),
  
  http_get(ProblemUrl, Reply, [json_object(dict)]),
  call(Problem, Reply, Solution),
  http_post(SolutionUrl, json(json([solution=Solution, id=Id])), _, []).

relationship(Data, Solution) :-
  atom_string(FirstPerson, Data.firstPerson),
  atom_string(SecondPerson, Data.secondPerson),
  atom_string(Relationship, Data.relationship),
  call(Relationship, FirstPerson, SecondPerson) -> Solution = "true" ; Solution = "false".

sudoku(Data, Solution) :-
  maplist(replace_0, Data.sudoku, Solution),
  solve_sudoku(Solution), 
  !. % prevent backtracking


